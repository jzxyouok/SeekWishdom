package cn.edu.ctbu.sw.action;

import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.Favorite;
import cn.edu.ctbu.sw.model.Question;
import cn.edu.ctbu.sw.model.User;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayInputStream;
import java.util.Date;

/**
 * Created by ys on 2015/11/19.
 */
@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace(value = "/favorite")
@Results({


})

public class FavoriteAction extends SBaseAction<Favorite> {


    /**
     * 问题的关注或者取消  注意只写明  int或者会出现错误
     * 第一次点为关注(save)，第二次问取消(delete)  后面要做的 在取消关注后 重新刷新关注与否，同时该问题的总关注数减少
     */
    @Action("saveOrDeleteq")
    public String saveOrDeleteq() {
        User user = (User) session.get("user");//添加用户
        String qid = requestParam.getParameter("qid");
        Favorite favorite = favoriteService.isExistq(qid, user.getId());
        if (favorite == null) {//加关注
            favorite = model;
            favorite.setUser(user);
            favorite.setCreateTime(new Date());
            Question question = new Question();
            question.setId(qid);
            favorite.setQuestion(question);
            favoriteService.save(favorite);
            inputStream = new ByteArrayInputStream("true".getBytes());
            // return 1;
        } else {  //取消关注
            favoriteService.delete(favorite.getId());
            inputStream = new ByteArrayInputStream("false".getBytes());
        }
        return "stream";
    }

    /**
     * 分类的关注或者取消
     * 第一次点为关注(save)，第二次问取消(delete)
     */
    @Action("saveOrDeletec")
    public String saveOrDeletec() {
        User user = (User) session.get("user");//添加用户
        Favorite favorite = favoriteService.isExistc(model.getCategory().getId(), user.getId());
        if (favorite == null) {//加关注
            favorite = model;
            favorite.setUser(user);
            favorite.setCreateTime(new Date());
            favoriteService.save(favorite);
            inputStream = new ByteArrayInputStream("true".getBytes());
        } else {  //取消关注
            favoriteService.delete(favorite.getId());
            inputStream = new ByteArrayInputStream("false".getBytes());
        }
        return "stream";
    }

    @Action("saveOrDeletea")
    public String saveOrDeletea() {
        User user = (User) session.get("user");//添加用户
        String aid = requestParam.getParameter("aid");
        Favorite favorite = favoriteService.isExista(aid, user.getId());
        if (favorite == null) {//加关注
            favorite = model;
            favorite.setUser(user);
            favorite.setCreateTime(new Date());
            Answer answer = new Answer();
            answer.setId(aid);
            favorite.setAnswer(answer);
            favoriteService.save(favorite);
            inputStream = new ByteArrayInputStream("true".getBytes());
            // return 1;
        } else {  //取消关注
            favoriteService.delete(favorite.getId());
            inputStream = new ByteArrayInputStream("false".getBytes());
        }
        return "stream";
    }

    @Action("isFavoritea")
    public String isFavoritea() {
        User user = (User) session.get("user");//添加用户
        String aid = requestParam.getParameter("aid");
        Favorite favorite = favoriteService.isExista(aid, user.getId());
        if (favorite == null) {//加关注
            inputStream = new ByteArrayInputStream("true".getBytes());
            // return 1;
        } else {  //取消关注
            inputStream = new ByteArrayInputStream("false".getBytes());
        }
        return "stream";
    }
}
