package common.dao.impl;

import common.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基本service实现类
 *
 * @param <T>泛型
 * @author Pomodoro
 */
@Repository("baseDao")
@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {

    // 泛型信息

    // sessionFactory注入
    @Resource
    protected SessionFactory sessionFactory;
    private Class clazz;

    // 构造方法获得泛型信息
    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    // 获取session
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // 保存
    @Override
    public void save(T t) {
        getSession().save(t);
    }

    // 更新
    @Override
    public void update(T t) {
        getSession().update(t);

    }

    // 删除
    @Override
    public void delete(String id) {
        String hql = "DELETE " + clazz.getSimpleName() + " WHERE id=:id";
        getSession().createQuery(hql).setString("id", id).executeUpdate();
    }

    // 获取单个对象
    @Override
    public T get(String id) {
        return (T) getSession().get(clazz, id);
    }

    // 获取所有对象
    @Override
    public List<T> query() {
        String sql = "FROM " + clazz.getSimpleName();
        return getSession().createQuery(sql).list();
    }

}
