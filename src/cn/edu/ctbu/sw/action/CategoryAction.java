package cn.edu.ctbu.sw.action;

import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.Category;
import cn.edu.ctbu.sw.model.Question;
import cn.edu.ctbu.sw.model.User;
import net.sf.json.JSONArray;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * CategoryAction
 * Created by Pomodoro on 2015/11/15.
 */

@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace(value = "/category")
@Results({@Result(location = "/category/answer.jsp", name = "answer")
})

public class CategoryAction extends SBaseAction<Category> {

    @Action("save")
    public String save() {
        categoryService.save(model);
        return SUCCESS;
    }

    @Value("#{prop.questionPageSize}")
    private int questionListPageSize;//话题显示的时候的分页大小.00

    /**
     * 获取前台话题cid: 每一页 条数 当前第几页   这个具有分页的性质的
     */
    @Action("category")
    public String queryQuestionListByCid() {
        String cid = requestParam.getParameter("cid");
        pageMap = new HashMap<String, Object>();
        if (getUser() == null) {
            inputStream = new ByteArrayInputStream("false".getBytes());
            return "stream";
        }
        String uid = getUser().getId();//当前用户的id
        //1获取对应的问题集合，
        List<Question> questionList = new ArrayList<>();
        questionList = questionService.queryQuestionListByCid(cid, page, questionListPageSize);
        List<Integer> favoritFlagList = new ArrayList<>();
        //2.获取对应分类集合前三个回答。以及他们的点赞数量。评论数量  以及回答的人
        List<List<Answer>> bigAnswerList = new ArrayList<>();
        List<List<Integer>> bigAgreeList = new ArrayList<>();
        List<List<Integer>> bigComentNumList = new ArrayList<>();
        List<List<Integer>> bigCurrentUser = new ArrayList<>();
        List<Answer> answeList = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            //2.1每一个问题获取前三个回答 按时间顺序排列 以及对应的回答者
            answeList = answerService.queryByAIdOrderByDate(questionList.get(i).getId());
            bigAnswerList.add(answeList);
            //2.2遍历回答的点赞数量和评论数量 和回答的人
            List<Integer> agreeList = new ArrayList<>();
            List<Integer> commentNumList = new ArrayList<>();
            List<Integer> currentUser = new ArrayList<>();
            Answer answer = new Answer();
            for (int j = 0; j < answeList.size(); j++) {

                answer = answeList.get(j);
                //获取点赞数量
                int agCount = agreeService.getACount(answer.getId());
                agreeList.add(agCount);
                //获取评论数量
                int cCount = commentService.getACount(answer.getId());
                commentNumList.add(cCount);
                //获取当前回答的人
                User aUser = userService.get(answer.getUser().getId());
                aUser.setPassword("");
                //判断是不是当前用户
                String auid = answer.getUser().getId();
                if (uid.equals(auid)) {
                    currentUser.add(1);
                } else
                    currentUser.add(0);

            }
            bigAgreeList.add(agreeList);
            bigComentNumList.add(commentNumList);
            bigCurrentUser.add(currentUser);
        }
        request.put("rows", questionList);
        request.put("total", questionService.getCount(cid));
        request.put("bigAnswerList", bigAnswerList);
        request.put("bigAgreeList", bigAgreeList);
        request.put("bigCurrentUser", bigCurrentUser);
        request.put("bigComentNumList", bigComentNumList);
        return "answer";

    }

    /**
     * 通过cid 查询当前话题下面有多少提问
     *
     * @return
     */
    @Action("count")
    public String getCount() {
        String cid = requestParam.getParameter("cid");//传递的话题分类id
        int pages = (int) Math.ceil(questionService.getCount(cid) * 1.0 / questionListPageSize);
        inputStream = new ByteArrayInputStream(String.valueOf(pages).getBytes());
        return "stream";
    }


}
