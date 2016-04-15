package cn.edu.ctbu.sw.service.impl;

import cn.edu.ctbu.sw.model.Category;
import cn.edu.ctbu.sw.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pomodoro on 2015/11/14.
 */
@Service("categoryService")
public class CategoryServiceImpl extends SBaseServiceImpl<Category> implements CategoryService {
    /**
     * 根据uid查询用户关注的分类
     *
     * @param uid
     * @return
     */
    @Override
    public List<Category> queryByUid(String uid) {
        return categoryDao.queryByUid(uid);
    }
}
