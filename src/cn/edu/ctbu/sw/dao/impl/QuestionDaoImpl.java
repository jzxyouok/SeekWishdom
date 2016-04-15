package cn.edu.ctbu.sw.dao.impl;

import cn.edu.ctbu.sw.dao.QuestionDao;
import cn.edu.ctbu.sw.model.Question;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ys
 * @version v1.0
 * @Title:QuestionDaoImpl.java
 * @Package cn.edu.qiuzhi.dao.impl
 * @Description:
 */
@Repository("questionDao")
public class QuestionDaoImpl extends SBaseDaoImpl<Question> implements QuestionDao {

    // 通过类型id查询当前的热点问题
    public List queryByCid(String id) {
        String hql = "FROM Question  q WHERE q.category.id=:id";
        return (List) getSession().createQuery(hql)// ;
                .setString("id", id)//
                .list();

    }

    @Override
    public List<Question> queryQuestionListByCid(String cid, int page, int size) {
        String hql = "FROM Question q WHERE q.category.id=:cid";
        List<Question> questionList = getSession().createQuery(hql)
                .setString("cid", cid)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size).list();
        return questionList;

    }

    /**
     * 根据cid查询对应分类下面的 question条数
     *
     * @param cid
     * @return
     */
    @Override
    public int getCount(String cid) {
        String hql = "SELECT count(q) FROM Question q WHERE  q.category.id=:cid";
        int count = Integer.parseInt(getSession().createQuery(hql).setString("cid", cid).uniqueResult().toString());
        return count;
    }

    /**
     * 根据用户的uid查看用户的提问
     *
     * @param uid
     * @param page
     * @param size @return
     */
    @Override
    public List<Question> queryQuestionListByUid(String uid, int page, int size, boolean isCurrentUser) {
        String hql = "FROM Question q WHERE q.user.id=:uid ";
        if (isCurrentUser == false) {
            hql += " and q.anonymous=false ";
        }
        hql += "order by  q.editTime desc";
        return getSession().createQuery(hql)
                .setString("uid", uid)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size).list();
    }

    /**
     * 通过uid获取当前的提问数量
     *
     * @param uid
     * @return
     */
    @Override
    public int getCountu(String uid) {
        String hql = "SELECT count(q) FROM Question q WHERE  q.user.id=:uid";
        int count = Integer.parseInt(getSession().createQuery(hql).setString("uid", uid).uniqueResult().toString());
        return count;
    }

    /**
     * 随机访问的没有回答的问题数量
     *
     * @param
     * @return
     */
    @Override
    public List<Question> queryQuestionListWithNoAns() {
        String sql = " SELECT * FROM QUESTION_TB WHERE  ID NOT IN (SELECT QID FROM ANSWER_TB)";
        List<Question> questionList = getSession().createSQLQuery(sql).addEntity(Question.class).list();
        return questionList;
    }

    @Override
    public List<Question> searchByTitle(String title) {
        String hql = "FROM Question q WHERE q.title LIKE :title order by q.editTime desc ";
        return (List<Question>) getSession().createQuery(hql)
                .setString("title", "%" + title + "%").list();
    }
}
