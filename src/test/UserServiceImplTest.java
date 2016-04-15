package test;

import cn.edu.ctbu.sw.model.User;
import cn.edu.ctbu.sw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by ys on 2015/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext-*.xml")
public class UserServiceImplTest {
  @Resource
  private UserService userService;

    @Test
    public void save() {
        User user = new User();
		/* user.setId(id); */
        user.setEmail("247677857@gmail.com");
        user.setPassword("123456");
        user.setNickname("木易爽");
        userService.save(user);

    }

}
