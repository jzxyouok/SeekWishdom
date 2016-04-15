package cn.edu.ctbu.sw.dao;


import cn.edu.ctbu.sw.model.User;

public interface UserDao extends SBaseDao<User> {
    /**
     * 用户登录 认证
     *
     * @param user
     * @return
     */
    User login(User user);

    boolean register(User user);

}
