package test;

import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.User;
import cn.edu.ctbu.sw.service.AnswerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ys on 2015/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext-*.xml")
public class AnswerServiceImplTest {
    @Resource
    private AnswerService answerService;

    @Test
    public void testSave() {
        Answer answer = new Answer();
        User user = new User();
        user.setId("");
        answer.setUser(user);
    }

    @Test
    public void testggg() {
        Date currentDate = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(currentDate);
        rightNow.add(Calendar.MINUTE, -5);
        Date date = rightNow.getTime();

        List<Answer> answerList = answerService.queryList(date);
    }

}
