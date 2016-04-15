package cn.edu.ctbu.sw.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import cn.edu.ctbu.sw.model.*;

import org.apache.commons.collections.ArrayStack;
import org.apache.commons.lang.SystemUtils;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

/**
 * @author ys
 * @version v1.0
 * @Title:QuestionAction.java
 * @Package cn.edu.qiuzhi.action
 * @Description: TODO(处理问题相关逻辑)
 */
@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace(value = "/question")
@Results({
        @Result(location = "/question/add.jsp", name = "question"),
        @Result(location = "/public/question.jsp", name = "sidebarList"),
        @Result(location = "/question/questionList.jsp", name = "questionList"),
        @Result(location = "/search/questionList.jsp", name = "searchQuestion"),
        @Result(location = "/question/detail.jsp", name = "detail")})
public class QuestionAction extends SBaseAction<Question> {
    @Value("#{prop.questionRow}")
    private int questionRow;//话题显示的时候的分页大小

    /**
     * 保存问题
     *
     * @return
     */
    @Action("save")
    public String save() {
        if (getUser() == null) {
            return "login";
        }
        String cid = requestParam.getParameter("cid");//获取选中的cid
        Category category = new Category();
        category.setId(cid);
        model.setCategory(category);
        model.setUser(getUser());
        if (model.getContent().trim().equals("")) {
            model.setContent("如题所示");
        }
        //初始化设置评论数量和回答数量
        model.setCreateTime(new Date());
        model.setEditTime(new Date());
        questionService.save(model);
        String id = model.getId();
        inputStream = new ByteArrayInputStream(model.getId().getBytes());
        return "stream";
    }


    /**
     * 根据qid跳转到详细页面，可能能够获取的数据 评论的集合， 回答
     *
     * @return
     */
    @Action("question")
    public String getQuestionById() {   //json数据返回
        if (getUser() == null) {
            return "login";
        }
        //1.获取该问题以及获取该问题下的评论数 以及回答的条数 //判断当前问题是否是用户自己的
        String qid = (String) requestParam.getParameter("qid");
        Question question = questionService.get(qid);
        //获取评论数
        int isCurrentUser = 0;//判断是否为
        int qCommentNum = commentService.getQCount(qid);
        String quid = question.getUser().getId();//问题的编辑者
        String uid = getUser().getId();//当前用户
        if (quid.equals(uid)) {
            isCurrentUser = 1;
        }
        int answerNum = answerService.getCount(qid);
        request.put("isCurrentUser", isCurrentUser);
        request.put("answerNum", answerNum);
        request.put("question", question);
        request.put("qCommentNum", qCommentNum);
        return "detail";
    }

    @Action("update")
    public String update() {
        String qid = (String) requestParam.getParameter("qid");
        String content = (String) requestParam.getParameter("content");
        //先从里面取出问题再修改
        String anonymous = requestParam.getParameter("anonymous");
        boolean isanonymous = Boolean.valueOf(anonymous);
        Question question = questionService.get(qid);
        question.setContent(content);
        question.setEditTime(new Date());
        question.setAnonymous(isanonymous);
        //更新
        try {
            questionService.update(question);
            inputStream = new ByteArrayInputStream("true".getBytes());
        } catch (Exception e) {
            inputStream = new ByteArrayInputStream("false".getBytes());
        }
        return "stream";
    }

    //提问
    @Action("questionList")
    public String questionList() {
        String uid = requestParam.getParameter("uid");//获取传递过来的uid
        //获取当前uid下面的questtion
        String currentUid = getUser().getId();
        boolean isCurrentUser = false;
        if (currentUid.equals(uid)) {
            isCurrentUser = true;
        }
        List<Question> questionList = new ArrayList<>();
        List<Integer> answerNumList = new ArrayList<>();//回答数量
        List<Integer> commentNumList = new ArrayList<>();//评论数量
        questionList = questionService.queryQuestionListByUid(uid, page, questionRow, isCurrentUser);
        Question question = new Question();
        int answerNum = 0;
        int commentNum = 0;
        for (int i = 0; i < questionList.size(); i++) {
            question = questionList.get(i);
            answerNum = answerService.getCount(question.getId());
            commentNum = commentService.getQCount(question.getId());
            answerNumList.add(answerNum);
            commentNumList.add(commentNum);

        }
        request.put("questionList", questionList);
        request.put("answerNumList", answerNumList);
        request.put("commentNumList", commentNumList);
        return "questionList";
    }

    /**
     * @return
     */
    @Value("#{prop.sidebarListsize}")
    private int sidebarListsize;//话题显示的时候的分页大小

    @Action("sidebarList")
    public String sidebarList() {
        List<Question> questionList = (List<Question>) application.get("sidebarList");
        List<Question> sidebarList = (List<Question>) session.get("sidebarList");
        if (sidebarList == null || sidebarList.size() == 0) {
            sidebarList = new ArrayList<>();
            List<Integer> randomList = new ArrayList<>();
            int lenth = questionList.size();
            Random random = new Random();
            if (lenth > 0) {
                int s;

                if (questionList.size() <= sidebarListsize) {
                    for (int i = 0; i < questionList.size(); i++) {

                        sidebarList.add(questionList.get(i));
                    }
                } else {
                    do {

                        if (lenth == 1) {
                            s = 0;
                        } else {
                            s = random.nextInt(lenth - 1) % (lenth);
                        }
                        boolean isExist = false;
                        for (Integer integer : randomList) {
                            if (s == integer) {
                                isExist = true;
                            }
                        }
                        if (isExist == false) {
                            randomList.add(s);
                            sidebarList.add(questionList.get(s));
                        }
                    } while (sidebarList.size() < sidebarListsize);

                }
            }
            session.put("sidebarList", sidebarList);
        }
        return "sidebarList";

    }

    @Action("search")
    public String search() {
        String title = model.getTitle();

        try {
            title = URLDecoder.decode(title, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        List<Question> questionList = questionService.searchByTitle(title);

        List<Integer> answerNumList = new ArrayList<>();//回答数量
        List<Integer> commentNumList = new ArrayList<>();//评论数量
        Question question = new Question();
        int answerNum = 0;
        int commentNum = 0;
        for (int i = 0; i < questionList.size(); i++) {
            question = questionList.get(i);
            answerNum = answerService.getCount(question.getId());
            commentNum = commentService.getQCount(question.getId());
            answerNumList.add(answerNum);
            commentNumList.add(commentNum);

        }
        request.put("questionList", questionList);
        request.put("answerNumList", answerNumList);
        request.put("commentNumList", commentNumList);
        return "searchQuestion";
    }

}
