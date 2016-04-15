package cn.edu.ctbu.sw.dao.impl;

import cn.edu.ctbu.sw.dao.CategoryDao;
import cn.edu.ctbu.sw.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pomodoro on 2015/11/14.
 */
@Repository("categoryDao")
public class CategoryDaoImpl extends SBaseDaoImpl<Category> implements CategoryDao {

    /**
     * 根据uid查询用户关注的分类
     *
     * @param uid
     * @return
     */
    @Override
    public List<Category> queryByUid(String uid) {
        String hql = "select f.category FROM  Favorite  f where  f.user.id=:uid";
        return getSession().createQuery(hql)
                .setString("uid", uid)
                .list();
    }
}
