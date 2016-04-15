package cn.edu.ctbu.sw.dao;

import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.Question;

import java.util.Date;
import java.util.List;

/**
 * Created by ys on 2015/11/17.
 */
public interface AnswerDao extends SBaseDao<Answer> {
    /**
     * 根据aid查询回答
     *
     * @param aid
     * @return
     */
    List<Answer> queryByAId(String aid);

    /**
     * 根据aid查询回答 查寻按时间排列的前三个
     *
     * @param aid
     * @return
     */
    List<Answer> queryByAIdOrderByDate(String aid);

    /**
     * 根据qid查询对应问题下面的 回答条数条数
     *
     * @param qid
     * @return
     */
    int getCount(String qid);


    // 查询cid下面的 ques

    /**
     * 有时间限制的查询分页查询
     *
     * @param page
     * @param size
     * @return
     */
    List<Answer> queryByTimeLimit(int page, int size);

    /**
     * 根据qid 以及分页查询  回答
     *
     * @param qid
     * @param page
     * @param size
     * @return
     */
    List<Answer> queryByQid(String qid, int page, int size, Date date);

    /**
     * 获取当前问题所在问题行数
     *
     * @param aid
     * @return
     */
    int LocalNum(String aid, String qid, Date date);

    /**
     * 根据设定的时间定时刷新首页的信息
     *
     * @param date
     * @return
     */
    List<Answer> queryList(Date date);

    /**
     * 根据用户的uid查看用户的提问
     *
     * @param uid
     * @param page
     * @param size @return
     */
    List<Answer> queryAnswerListByUid(String uid, int page, int size,boolean isCurrentUser);


    /**
     * 通过uid获取当前的回答数量
     *
     * @param uid
     * @return
     */
    int getCountu(String uid);


    /**
     * 根据是点赞获取用户赞同过的回答
     *
     * @param uid
     * @param page
     * @param size
     * @return
     */
    List<Answer> queryAnswerListByUidag(String uid, int page, int size);
}
