package common.service;

import java.util.List;

/**
 * 基本service接口
 *
 * @param <T>泛型信息
 * @author Pomodoro
 */
public interface BaseService<T> {

    void save(T t);

    void update(T t);

    void delete(String id);

    T get(String id);

    List<T> query();
}
