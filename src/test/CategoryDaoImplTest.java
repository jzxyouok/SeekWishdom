package test;

import cn.edu.ctbu.sw.model.Category;
import cn.edu.ctbu.sw.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Pomodoro on 2015/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext-*.xml")
public class CategoryDaoImplTest {

    @Resource
    private CategoryService categoryService;

    @Test
    public void testSsh() {
        Category category = new Category();
        category.setCategory("哲学");
        categoryService.save(category);
    }

    @Test
    public void testQuery(){

        for (Category temp: categoryService.query()  ) {
            System.out.println(temp.getCategory());
        }
    }

}