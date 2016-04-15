package cn.edu.ctbu.sw.dao;

import cn.edu.ctbu.sw.model.Category;

import java.util.List;

/**
 * Created by Pomodoro on 2015/11/14.
 */
public interface CategoryDao extends SBaseDao<Category> {
    /**
     * 根据uid查询用户关注的分类
     *
     * @param uid
     * @return
     */
    List<Category> queryByUid(String uid);
}
