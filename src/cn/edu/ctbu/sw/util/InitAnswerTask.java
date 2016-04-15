package cn.edu.ctbu.sw.util;

import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.Question;
import cn.edu.ctbu.sw.model.User;
import cn.edu.ctbu.sw.service.AgreeService;
import cn.edu.ctbu.sw.service.AnswerService;
import cn.edu.ctbu.sw.service.CommentService;
import cn.edu.ctbu.sw.service.QuestionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by ShuangYang on 2015/11/28.
 */
@Component("initAnswerTask")
public class InitAnswerTask extends TimerTask {
    private ServletContext application = null;
    @Resource
    private AnswerService answerService = null;
    @Resource
    private CommentService commentService = null;
    @Resource
    private QuestionService questionService = null;

    public void setApplication(ServletContext application) {
        this.application = application;
    }

    @Override
    public void run() {
        Date currentDate = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(currentDate);
        rightNow.add(Calendar.HOUR, -8);
        Date date = rightNow.getTime();
        //获取当前用户的uid;
        List<Answer> answerList = answerService.queryList(date);
        //获取回答的点赞数量和评论数量
        List<Integer> commentNumList = new ArrayList<>();
        List<Integer> isAgreeList = new ArrayList<>();
        int commentNum = 0;
        boolean agree = false;
        Answer answer = new Answer();
        for (int i = 0; i < answerList.size(); i++) {
            answer = answerList.get(i);
            commentNum = commentService.getACount(answer.getId());
            //判断当前用户是否点赞

            commentNumList.add(commentNum);
        }
        //获取没有回答的问题
        List<Question> sidebarList = questionService.queryQuestionListWithNoAns();
        application.setAttribute("answerList", answerList);
        application.setAttribute("commentNumList", commentNumList);
        application.setAttribute("sidebarList", sidebarList);

    }
}
