package cn.edu.ctbu.sw.service.impl;

import cn.edu.ctbu.sw.dao.*;
import cn.edu.ctbu.sw.service.SBaseService;
import common.service.impl.BaseServiceImpl;

import javax.annotation.Resource;

/**
 * Created by Pomodoro on 2015/11/14.
 */
public class SBaseServiceImpl<T> extends BaseServiceImpl<T> implements SBaseService<T> {
    // 管理dao文件
    @Resource
    public UserDao userDao;// 用户dao
    @Resource
    public CategoryDao categoryDao;// 分类管理
    @Resource
    public QuestionDao questionDao;// 话题dao
    @Resource
    public CommentDao commentDao;//评论的dao
    @Resource
    public AnswerDao answerDao;//回答的dao
    @Resource
    public AgreeDao agreeDao;//点赞dao
    @Resource
    public FavoriteDao favoriteDao;//点赞dao
}
