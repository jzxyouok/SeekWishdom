package cn.edu.ctbu.sw.action;

import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.Question;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ys on 2015/11/18.
 */
@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace(value = "/answer")
@Results({@Result(location = "/question/answer.jsp", name = "answer"),
        @Result(location = "/user/answer.jsp", name = "answerList"),
        @Result(location = "/question/detail.jsp", name = "detail")})
public class AnswerAction extends SBaseAction<Answer> {
    @Value("#{prop.questionDetailAnswerRow}")//会回答的分页大小
    private int questionDetailAnswerRow;

    @Value("#{prop.agreebeforetime}")//多少时间前的点赞
    private int agreeTimeBefore;
    @Value("#{prop.answerRow}")
    private int answerRow;//话题显示的时候的分页大小.00

    @Value("#{prop.agreeRow}")
    private int agreeRow;//话题显示的时候的分页大小.0


    /**
     * 添加回答
     */
    @Action("save")
    public String addAnswer() {
        if (getUser() == null) {
            return "login";
        }
        model.setCreateTime(new Date());
        model.setEditTime(new Date());
        //添加回答时间
        model.setUser(getUser());
        try {
            answerService.save(model);
            String aid = model.getId();
            inputStream = new ByteArrayInputStream(aid.getBytes());
        } catch (Exception e) {
            inputStream = new ByteArrayInputStream("false".getBytes());
        }
        //查询出当的问题所在位置
        return "stream";
    }


    /**
     * 详情页获取回答总页数
     *
     * @return
     */
    @Action("count")
    public String getPageCount() {
        String qid = requestParam.getParameter("qid");
        int pages = (int) Math.ceil(answerService.getCount(qid) * 1.0 / questionDetailAnswerRow);
        inputStream = new ByteArrayInputStream(String.valueOf(pages).getBytes());
        return "stream";
    }

    /**
     * 通过qid 以及相应的分页获取回答以及相应的点赞数量，评论数量
     *
     * @return
     */
    @Action("answerList")
    public String queryAnswerListByQid() {
        String qid = requestParam.getParameter("qid");//获取选中的qid
        int rows = questionDetailAnswerRow;
        //时间处理
        Date currentDate = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(currentDate);
        rightNow.add(Calendar.MINUTE, -agreeTimeBefore);
        Date date = rightNow.getTime();
        //1获取answer集合 以及相应的评论数量，和点赞数量 当前用户是否点赞 获取该问题总的回答数量  判断当前回答者是否是自己
        List<Integer> isCurrentUser = new ArrayList<>();
        List<Answer> answerList = answerService.queryByQid(qid, page, questionDetailAnswerRow, date);
        List<Integer> commentNumList = new ArrayList<>();
        List<Integer> agreeNumList = new ArrayList<>();
        List<Integer> isAgreeList = new ArrayList<>();
        Answer answer = new Answer();
        boolean agree = false;
        int commentNum = 0;
        int agreeNum = 0;
        for (int i = 0; i < answerList.size(); i++) {
            answer = answerList.get(i);
            commentNum = commentService.getACount(answer.getId());
            agreeNum = agreeService.getACount(answer.getId());
            agree = agreeService.IsAgreeExista(answer.getId(), getUser().getId());
            if (agree == false) {
                isAgreeList.add(0);//未点赞
            } else {
                isAgreeList.add(1);
            }
            //判断当前用户是否为回答者
            String uid = getUser().getId();
            String auid = answer.getUser().getId();
            if (uid.equals(auid)) {
                isCurrentUser.add(1);
            } else {
                isCurrentUser.add(0);
            }
            commentNumList.add(commentNum);
            agreeNumList.add(agreeNum);
        }
        //判断是否已经点赞

        request.put("answerList", answerList);
        request.put("commentNumList", commentNumList);
        request.put("agreeNumList", agreeNumList);
        request.put("isAgreeList", isAgreeList);
        request.put("isCurrentUser", isCurrentUser);
        return "answer";

    }

    @Action("update")
    public String update() {
        String aid = requestParam.getParameter("aid");//获取选中的qid
        String content = requestParam.getParameter("content");
        String anonymous = requestParam.getParameter("anonymous");
        //查询当前的回答anonymous
        boolean isAnonymous = Boolean.valueOf(anonymous);
        Answer answer = answerService.get(aid);
        answer.setEditTime(new Date());
        answer.setContent(content);
        answer.setAnonymous(isAnonymous);
        //更新
        try {
            answerService.update(answer);
            inputStream = new ByteArrayInputStream("true".getBytes());
        } catch (Exception e) {
            inputStream = new ByteArrayInputStream("false".getBytes());
        }
        return "stream";
    }

    @Action("answer")
    public String getAnswerByaid() {
        //当用户未登录的时候转跳登录页面
        if (getUser() == null) {
            return "login";
        }
        String aid = requestParam.getParameter("aid");//获取选中的aid
        //判断当前所在的位置
        Answer answer = answerService.get(aid);
        //获取当前问题
        Question question = questionService.get(answer.getQuestion().getId());
        int isCurrentUser = 0;//判断是否为
        String quid = question.getUser().getId();//问题的编辑者
        int qCommentNum = commentService.getQCount(question.getId());
        String uid = getUser().getId();//当前用户

        if (quid.equals(uid)) {
            isCurrentUser = 1;
        }
        //时间处理
        Date currentDate = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(currentDate);
        rightNow.add(Calendar.MINUTE, -agreeTimeBefore);
        Date date = rightNow.getTime();
        int localNum = answerService.LocalNum(aid, question.getId(), date);

        //返回当前所在页数以及第几页//
        int answerNum = answerService.getCount(question.getId());
        //所在第几页
        int currentPage = localNum / questionDetailAnswerRow;
        int pageMark = localNum % questionDetailAnswerRow;
        if (pageMark != 0) {
            currentPage++;
        }
        request.put("isCurrentUser", isCurrentUser);
        request.put("answerNum", answerNum);
        request.put("question", question);
        request.put("currentPage", currentPage);
        request.put("qCommentNum", qCommentNum);
        request.put("pageMark", pageMark);
        return "detail";

    }

    /**
     * 通过qid 以及相应的分页获取回答以及相应的点赞数量，评论数量
     *
     * @return
     */
    @Action("answerListu")
    public String queryAnswerListByUid() {
        String uid = requestParam.getParameter("uid");//获取选中的qid
        //时间处理
        boolean isCurrentUser = false;
        String currentUid = getUser().getId();//获取当前登录用户的id
        if (currentUid.equals(uid)) {
            isCurrentUser = true;
        }
        //1获取answer集合 以及相应的评论数量，和点赞数量 当前用户是否点赞 获取该问题总的回答数量  判断当前回答者是否是自己
        List<Answer> answerList = answerService.queryAnswerListByUid(uid, page, answerRow, isCurrentUser);
        List<Integer> commentNumList = new ArrayList<>();
        List<Integer> agreeNumList = new ArrayList<>();

        Answer answer = new Answer();

        int commentNum = 0;
        int agreeNum = 0;
        for (int i = 0; i < answerList.size(); i++) {
            answer = answerList.get(i);
            //不做任何操作
            commentNum = commentService.getACount(answer.getId());
            agreeNum = agreeService.getACount(answer.getId());
            //判断当前用户是否为回答者


            commentNumList.add(commentNum);
            agreeNumList.add(agreeNum);


        }
        //判断是否已经点赞

        request.put("answerList", answerList);
        request.put("commentNumList", commentNumList);
        request.put("agreeNumList", agreeNumList);
        return "answerList";

    }

    /**
     * 通过uid 获取当前用户过过赞的回答
     *
     * @return
     */
    @Action("answerListag")
    public String queryAnswerListByUidag() {
        String uid = requestParam.getParameter("uid");//获取选中的qid
        //时间处理

        //1获取answer集合 以及相应的评论数量，和点赞数量 当前用户是否点赞 获取该问题总的回答数量  判断当前回答者是否是自己
        List<Answer> answerList = answerService.queryAnswerListByUidag(uid, page, agreeRow);
        List<Integer> commentNumList = new ArrayList<>();
        List<Integer> agreeNumList = new ArrayList<>();
        Answer answer = new Answer();
        int commentNum = 0;
        int agreeNum = 0;
        for (int i = 0; i < answerList.size(); i++) {
            answer = answerList.get(i);
            commentNum = commentService.getACount(answer.getId());
            agreeNum = agreeService.getACount(answer.getId());
            //判断当前用户是否为回答者


            commentNumList.add(commentNum);
            agreeNumList.add(agreeNum);
        }
        //判断是否已经点赞

        request.put("answerList", answerList);
        request.put("commentNumList", commentNumList);
        request.put("agreeNumList", agreeNumList);
      /*  request.put("isAgreeList", isAgreeList);
        request.put("isCurrentUser", isCurrentUser);*/
        return "answerList";
    }

}
