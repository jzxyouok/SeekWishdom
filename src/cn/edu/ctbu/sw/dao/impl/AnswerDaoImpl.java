package cn.edu.ctbu.sw.dao.impl;

import cn.edu.ctbu.sw.dao.AnswerDao;
import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.Question;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ys on 2015/11/17.
 */
@Repository("answerDao")
public class AnswerDaoImpl extends SBaseDaoImpl<Answer> implements AnswerDao {
    /**
     * 根据aid查询回答
     *
     * @param aid
     * @return
     */
    @Override
    public List<Answer> queryByAId(String aid) {
        String hql = "FROM Answer a WHERE a.question.id=:aid";
        return getSession().createQuery(hql)
                .setString("aid", aid)
                .list();
    }

    /**
     * 根据aid查询回答 查寻按时间排列的前三个
     *
     * @param aid
     * @return
     */
    @Override
    public List<Answer> queryByAIdOrderByDate(String aid) {
        String hql = "FROM Answer a WHERE a.question.id=:aid order by editTime desc ";
        return getSession().createQuery(hql)
                .setString("aid", aid)
                .setFirstResult(0).setMaxResults(3)
                .list();
    }

    /**
     * 根据qid查询对应问题下面的 回答条数
     *
     * @param qid
     * @return
     */
    @Override
    public int getCount(String qid) {
        String hql = "SELECT count(a) FROM Answer  a WHERE a.question.id=:qid";
        return Integer.parseInt(getSession().createQuery(hql)
                .setString("qid", qid).uniqueResult().toString());
    }

    /**
     * 有时间限制的查询分页查询
     * 需要修改 2015//11/23
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Answer> queryByTimeLimit(int page, int size) {
        String hql = "FROM Answer  WHERE  1=1";
        return getSession().createQuery(hql)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size).list();

    }

    /**
     * 根据qid 以及分页查询  回答
     *
     * @param qid
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Answer> queryByQid(String qid, int page, int size, Date date) {
        String sql = "SELECT * FROM answer_tb s LEFT JOIN (SELECT aid, count(POINT) num FROM agree_tb  WHERE  CREATEtime>:date GROUP BY aid) A ON s.id = A.aid WHERE s.qid = :qid ORDER BY A.num DESC nulls last";
        List<Answer> l = getSession().createSQLQuery(sql).addEntity(Answer.class)
                .setString("qid", qid)
                .setDate("date", date)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .list();
        return l;
    }

    /**
     * 获取当前问题所在行数
     *
     * @param aid
     * @return
     */
    @Override
    public int LocalNum(String aid, String qid, Date date) {
        String sql = "select snum from (SELECT s.id as sid,ROWNUM as snum,s.content FROM answer_tb s LEFT JOIN (SELECT aid, count(POINT) num FROM agree_tb WHERE  CREATEtime>:date GROUP BY aid ) A ON s.id = A.aid WHERE s.qid =:qid ORDER BY a.num DESC NULLS LAST) local_temptb WHERE local_temptb.sid=:aid";
        int localNum = Integer.parseInt(getSession().createSQLQuery(sql)
                .setString("aid", aid)
                .setDate("date", date)
                .setString("qid", qid).uniqueResult().toString());

        return localNum;
    }

    @Override
    public List<Answer> queryList(Date date) {
        String sql = "SELECT * FROM answer_tb s LEFT JOIN (SELECT aid, count(POINT) num FROM agree_tb  WHERE  CREATEtime>:date GROUP BY aid) A ON s.id = A.aid  ORDER BY A.num DESC nulls last";
        List<Answer> answerList = getSession().createSQLQuery(sql).addEntity(Answer.class)
                .setDate("date", date)
                .setFirstResult(0)
                .setMaxResults(10).list();
        return answerList;
    }

    /**
     * 根据用户的uid查看用户的提问
     *
     * @param uid
     * @param page
     * @param size          @return
     * @param isCurrentUser
     */
    @Override
    public List<Answer> queryAnswerListByUid(String uid, int page, int size, boolean isCurrentUser) {
        String hql = "FROM Answer a WHERE a.user.id=:uid ";//order by  a.createTime desc
        if (isCurrentUser == false) {
            hql = hql + " and a.anonymous=false ";
        }
        hql += "order by  a.createTime desc";
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
        String hql = "SELECT count(a) FROM Answer a WHERE  a.user.id=:uid";
        int count = Integer.parseInt(getSession().createQuery(hql).setString("uid", uid).uniqueResult().toString());
        return count;
    }

    /**
     * 根据是点赞获取用户赞同过的回答
     *
     * @param uid
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Answer> queryAnswerListByUidag(String uid, int page, int size) {
        // String hql = "FROM Answer  an LEFT  JOIN fetch Agree ag on ag.answer.id=an.id where ag.user.id=:uid";
        String sql = " SELECT * FROM ANSWER_TB  an LEFT JOIN AGREE_TB ag on ag.AID=an.ID where ag.USID=:uid ORDER BY ag.CREATETIME DESC";

        List<Answer> answerList = getSession().createSQLQuery(sql).addEntity(Answer.class)
                .setString("uid", uid)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size).list();
        return answerList;

    }


}

