package common.service.impl;

import common.dao.BaseDao;
import common.service.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基本service实现类
 *
 * @param <T>泛型
 * @author Pomodoro
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {

    // 泛型信息

    protected BaseDao baseDao;
    private Class clazz;

    // 构造方法获得泛型信息
    public BaseServiceImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    @PostConstruct
    public void init() {
        String clazzName = clazz.getSimpleName();
        String clazzDao = clazzName.substring(0, 1).toLowerCase()
                + clazzName.substring(1) + "Dao";
        try {
            Field clazzField = this.getClass().getSuperclass()
                    .getDeclaredField(clazzDao);
            Field baseField = this.getClass().getSuperclass().getSuperclass()
                    .getDeclaredField("baseDao");
            baseField.set(this, clazzField.get(this));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 保存
    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    // 更新
    @Override
    public void update(T t) {
        baseDao.update(t);

    }

    // 删除
    @Override
    public void delete(String id) {
        baseDao.delete(id);
    }

    // 获取单个对象
    @Override
    public T get(String id) {
        return (T) baseDao.get(id);
    }

    // 获取所有对象
    @Override
    public List<T> query() {
        return baseDao.query();
    }

}
