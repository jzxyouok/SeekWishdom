package cn.edu.ctbu.sw.listener;

import cn.edu.ctbu.sw.util.InitCategoryTask;
import cn.edu.ctbu.sw.util.InitAnswerTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * Created by ShuangYang on 2015/11/23.
 */
public class InitDataListener implements ServletContextListener {

    @Value("#{prop.questionRefreshTime}")
    private int refreshTime;//首页问题刷新时间设定
    private ApplicationContext context = null;
    private InitCategoryTask initCategoryTask = null;//初始化分类
    private InitAnswerTask initAnswerTask = null;//初始化页面数据

    @Override
    public void contextInitialized(ServletContextEvent event) {
        initCategoryList(event);
        initQuestionList(event);
    }

    /**
     * 初始化分类
     *
     * @param event
     */
    public void initCategoryList(ServletContextEvent event) {
        context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        initCategoryTask = (InitCategoryTask) context
                .getBean("initCategoryTask");
        initCategoryTask.setApplication(event.getServletContext());
        new Timer(true).schedule(initCategoryTask, 0, 1000 * 15);
    }

    /**
     * 初始化首页话题
     *
     * @param event
     */
    private void initQuestionList(ServletContextEvent event) {
        context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        initAnswerTask = (InitAnswerTask) context.getBean("initAnswerTask");
        initAnswerTask.setApplication(event.getServletContext());
        new Timer(true).schedule(initAnswerTask, 0, 1000 * 15);

    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
