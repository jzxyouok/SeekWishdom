package cn.edu.ctbu.sw.dao.impl;

import cn.edu.ctbu.sw.dao.CommentDao;
import cn.edu.ctbu.sw.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ys on 2015/11/17.
 */
@Repository("commentDao")
public class CommentDaoImpl extends SBaseDaoImpl<Comment> implements CommentDao {


    /**
     * 根据aid以及分页查询评论
     *
     * @param aid
     * @param page
     * @param size @return
     */
    @Override
    public List<Comment> queryByAId(String aid, int page, int size) {
        String hql = "FROM Comment c WHERE  c.answer.id=:aid";
        return getSession().createQuery(hql)
                .setString("aid", aid)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size).list();
    }

    /**
     * 根据qid 以及分页查询
     *
     * @param qid
     * @param page
     * @param size @return
     */
    @Override
    public List<Comment> queryByQId(String qid, int page, int size) {
        String hql = "FROM Comment c WHERE  c.question.id=:qid";
        return getSession().createQuery(hql)
                .setString("qid", qid)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size).list();
    }

    /**
     * 根据aid查询其对应的查询评论数量
     *
     * @param aid@return
     */
    @Override
    public int getACount(String aid) {
        String hql = "SELECT count(c) FROM Comment  c WHERE c.answer.id=:aid";
        return Integer.parseInt(getSession().createQuery(hql)
                .setString("aid", aid).uniqueResult().toString());
    }


    /**
     * 根据qid查询其对应的查询评论数量
     *
     * @param qid
     * @return
     */
    @Override
    public int getQCount(String qid) {
        String hql = "SELECT count(c) FROM Comment  c WHERE c.question.id=:qid";
        return Integer.parseInt(getSession().createQuery(hql)
                .setString("qid", qid).uniqueResult().toString());
    }
}
