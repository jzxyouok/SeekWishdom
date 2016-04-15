package cn.edu.ctbu.sw.dao.impl;


import cn.edu.ctbu.sw.dao.UserDao;
import cn.edu.ctbu.sw.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ys
 * @version v1.0
 * @Title:UserDaoImpl.java
 * @Package cn.edu.qiuzhi.dao.impl
 * @Description: TODO(用户管理)
 */
@Repository("userDao")
public class UserDaoImpl extends SBaseDaoImpl<User> implements UserDao {

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        String hql = "FROM User u WHERE u.email=:email AND u.password=:password";
        List<User> l = getSession().createQuery(hql)//
                .setString("email", user.getEmail())//
                .setString("password", user.getPassword()).list();
        if (l != null && l.size() != 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean register(User user) {
        String hql = "FROM User WHERE email=:email";
        List l = getSession().createQuery(hql).setString("email", user.getEmail()).list();
        if (l != null && l.size() != 0) {
            return false;
        } else {
            save(user);
            return true;

        }
    }
}
