package cn.edu.ctbu.sw.service;


import cn.edu.ctbu.sw.model.User;

public interface UserService extends SBaseService<User> {
	User login(User user);

	boolean register(User model);
}
