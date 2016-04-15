package cn.edu.ctbu.sw.util;


import cn.edu.ctbu.sw.model.Category;
import cn.edu.ctbu.sw.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by ShuangYang on 2015/11/23.
 */
@Component("initCategoryTask")
public class InitCategoryTask extends TimerTask {
    @Resource
    private CategoryService categoryService = null;
    private ServletContext application = null;


    public void setApplication(ServletContext application) {
        this.application = application;
    }

    @Override
    public void run() {
        //获取话题分类并放置到application对象
        List<Category> categoryList = categoryService.query();
        application.setAttribute("categoryList", categoryList);

    }
}
