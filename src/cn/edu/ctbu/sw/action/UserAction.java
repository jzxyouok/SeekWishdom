package cn.edu.ctbu.sw.action;

import cn.edu.ctbu.sw.model.User;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayInputStream;

/**
 * @author ys
 * @version v1.0
 * @Title:UserAction.java
 * @Package cn.edu.qiuzhi.action
 * @Description: (处理用户诸如：登录等相关问题)
 */
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Namespace(value = "/user")
@Results({
        @Result(location = "/umain/uindex.jsp", name = "uindex"),
        @Result(location = "/user/user.jsp", name = "user")
       /* @Result(name = "jsonMap", type = "json", params = {"root", "pageMap"})*/
})
public class UserAction extends SBaseAction<User> {
    @Value("#{prop.questionRow}")
    private int questionRow;//话题显示的时候的分页大小
    @Value("#{prop.answerRow}")
    private int answerRow;//话题显示的时候的分页大小.00
    @Value("#{prop.agreeRow}")
    private int agreeRow;//话题显示的时候的分页大小.00

    /**
     * 用户登录
     *
     * @return
     */
    @Action("login")
    public String login() {
        String tips;
        // 1进行登陆的判断
        model = userService.login(model);
        if (model == null) {
            tips = "false";
            inputStream = new ByteArrayInputStream(tips.getBytes());
            return "stream";
        } else {
            // 3.页面显示用户昵称
            session.put("user", model);
            // 调到用户的操作界面
            tips = "true";
            inputStream = new ByteArrayInputStream(tips.getBytes());
            return "stream";
        }
    }

    @Action("logout")
    public String logout() {
        // 1注销用户的session 内容
        if (session.get("user") != null) {
            session.remove("user");
            session.remove("sidebarList");
        }
        // 2.转跳起始页面
        return "index";

    }


    /**
     * 注册用户
     */
    @Action("register")
    public String register() {
        String avatar = "/image/avatar/default.jpg";//默认头像路径
        model.setAvatar(avatar);
        inputStream = new ByteArrayInputStream(String.valueOf(userService.register(model)).getBytes());
        return "stream";
    }

    @Action("user")
    public String user() {
        //其他相关操作
        String uid = requestParam.getParameter("uid");
        User user = userService.get(uid);
        int qCount = questionService.getCountu(uid);
        int qPages = (int) Math.ceil(questionService.getCountu(uid) * 1.0 / questionRow);
        //总的回答数量
        int aCount = answerService.getCountu(uid);
        int aPages = (int) Math.ceil(answerService.getCountu(uid) * 1.0 / answerRow);
        //总的点赞数量
        int agCount = agreeService.getCountu(uid);
        int agPages = (int) Math.ceil(agreeService.getCountu(uid) * 1.0 / agreeRow);


        request.put("user", user);
        request.put("qCount", qCount);
        request.put("aCount", aCount);
        request.put("agCount", agCount);
        request.put("qPages", qPages);
        request.put("aPages", aPages);
        request.put("agPages", agPages);
        return "user";
    }

    /**
     * 获取用户回答提问等相关信息的数量
     *
     * @return
     */
    @Action("detail")
    public String userDetail() {//dao hql还没写
        String uid = requestParam.getParameter("uid");
        //1获取当前用户的总的提问数量
        //  int qCount = questionService.getCountu(uid);
        int qPages = (int) Math.ceil(questionService.getCountu(uid) * 1.0 / questionRow);
        //总的回答数量
        //int aCount = answerService.getCountu(uid);
        int aPages = (int) Math.ceil(answerService.getCountu(uid) * 1.0 / questionRow);
        //总的点赞数量
        //int agCount = agreeService.getCountu(uid);
        int agPages = (int) Math.ceil(agreeService.getCountu(uid) * 1.0 / questionRow);


        return "jsonMap";
    }
}
