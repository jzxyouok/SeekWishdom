package cn.edu.ctbu.sw.action;


import cn.edu.ctbu.sw.model.Category;
import cn.edu.ctbu.sw.model.FileImage;
import cn.edu.ctbu.sw.model.User;
import cn.edu.ctbu.sw.service.*;
import cn.edu.ctbu.sw.util.FileUpload;
import common.action.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * SBaseAction
 * Created by Pomodoro on 2015/11/14.
 */
@Results({
        @Result(location = "/user/login.jsp", name = "login"),
        @Result(location = "/index.jsp", name = "index"),
        @Result(location = "/error.jsp", name = "error"),
        @Result(name = "stream", type = "stream", params = {"inputName", "inputStream"})})
@ExceptionMappings({@ExceptionMapping(exception = "java.lang.Exception", result = "error")})
public class SBaseAction<T> extends BaseAction<T> {
    // 普通list
    protected List<T> indexList = null;
    /**
     * 配置基本的Service资源
     */
    @Resource
    public QuestionService questionService;// 话题
    @Resource
    public UserService userService;// 用户
    @Resource
    public CategoryService categoryService;// 用户
    @Resource
    public CommentService commentService;//评论
    @Resource
    public AnswerService answerService;//回答
    @Resource
    public AgreeService agreeService;//点赞
    @Resource
    public FavoriteService favoriteService;//收藏(问题或者分类)
    // 文件
    protected FileImage fileImage;
    // 工具类
    @Resource
    protected FileUpload fileUpload;
    /**
     * 获取request参数
     */
    protected HttpServletRequest requestParam = ServletActionContext.getRequest();

    protected User getUser() {
        return (User) session.get("user");
    }

    protected List<Category> getCategoryList() {
        return (List<Category>) application.get("categoryList");
    }

    public FileImage getFileImage() {
        return fileImage;
    }

    public void setFileImage(FileImage fileImage) {
        this.fileImage = fileImage;
    }
}
