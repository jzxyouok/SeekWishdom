package cn.edu.ctbu.sw.service.impl;
import cn.edu.ctbu.sw.model.User;
import cn.edu.ctbu.sw.service.UserService;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.stereotype.Service;

/**
 * @Title:UserServiceImpl.java
 * @Package cn.edu.qiuzhi.service.impl
 * @Description: TODO(用户相关信息操作)
 * @author ys
 * @version v1.0
 */
@Service("userService")
public class UserServiceImpl extends SBaseServiceImpl<User> implements UserService {

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public boolean register(User model) {
		return userDao.register(model);
	}
}
