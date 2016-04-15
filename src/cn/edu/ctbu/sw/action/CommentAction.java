package cn.edu.ctbu.sw.action;

import cn.edu.ctbu.sw.model.Comment;
import cn.edu.ctbu.sw.model.User;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

import java.util.Date;

/**
 * Created by ys on 2015/11/18.
 */
@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace(value = "/comment")
@Results({@Result(location = "http://www.baidu.com", type = "redirect"),
        @Result(name = "jsonList", type = "json", params = {"root", "jsonList"}),
        @Result(name = "JCommentList", type = "json", params = {"root", "jsonList"})
})
public class CommentAction extends SBaseAction<Comment> {

    /**
     * 添加问题的评论
     */
    @Action("addQcomment")
    public void addQcomment() {
        //插入评论
        model.setCreateTime(new Date());//添加回答时间
        User user = (User) session.get("user");//添加用户
        model.setUser(user);
        commentService.save(model);
    }

    /**
     * 添加回答的评论
     */
    @Action("addAcomment")
    public void addAcomment() {
        //1.保存评论
        model.setCreateTime(new Date());//添加评论时间
        User user = (User) session.get("user");//评论的用户
        model.setUser(user);
        commentService.save(model);
    }

    /**
     * 根据qid获取 评论的条数 有分页
     *
     * @return
     */

    /**
     * 根据qid获取 评论的条数 有分页
     *
     * @return
     */

    @Action("getCommentListByQid")
    public String getCommentListByQid() {
        jsonList = commentService.queryByQId(model.getQuestion().getId(), page, rows);
        return "jsonList";
    }

    /**
     * 根据aid获取 评论的条数 有分页
     *
     * @return
     */
    @Action("queryCommentListByAid")
    public String getCommentListByAid() {
        String aid = requestParam.getParameter("aid");
        jsonList = commentService.queryByQId(aid, page, rows);
        return "jsonList";
    }
}
