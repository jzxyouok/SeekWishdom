package test;

import cn.edu.ctbu.sw.model.Category;
import cn.edu.ctbu.sw.model.Question;
import cn.edu.ctbu.sw.model.User;
import cn.edu.ctbu.sw.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ys on 2015/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext-*.xml")
public class QuestionServiceImplTest {
    @Resource
    private QuestionService questionService;

    @Test
    public void testQuery() {
        for (Question temp : questionService.query()) {
            System.out.println("提问标题:" + temp.getTitle() + ",提问选项" + temp.getCategory().getCategory());
        }
    }

    @Test
    public void TestSave() {
        Question question = new Question();
        question.setTitle("mndahdgggggggggggggggggge");
        Category category = new Category();
        category.setId("8a94c353510f00d901510f00e0580000");
        question.setCategory(category);
     /*   String cid=requestParam.getParameter("cid");
        User user = (User) session.get("user");*/
        User user = new User();
        user.setId("8a94c353510ae4b201510ae4b8790000");
        question.setUser(user);

        question.setCreateTime(new Date());
        questionService.save(question);
    }

    @Test
    public void testQueryListByCid() {
        for (Question temp : questionService.queryQuestionListByCid("8a94c353510f00d901510f00e0580000", 1, 20)) {
            System.out.println("提问标题:" + temp.getTitle() + ",提问选项" + temp.getCategory().getCategory());
        }
    }

    @Test
    public void testQueryCountByCid() {
        System.out.println(questionService.getCount("8a94c353510f00d901510f00e0580000"));
    }

    @Test
    public void TtestFavory() {
        int total = 9;
        int current = 9 / 2;
        int cu=9%2;

    }
}
