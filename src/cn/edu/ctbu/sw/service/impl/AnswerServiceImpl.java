package cn.edu.ctbu.sw.service.impl;

import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.Question;
import cn.edu.ctbu.sw.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ys on 2015/11/17.
 */
@Service("answerService")
public class AnswerServiceImpl extends SBaseServiceImpl<Answer> implements AnswerService {

    /**
     * 根据aid查询回答
     *
     * @param aid
     * @return
     */
    @Override
    public List<Answer> queryByAId(String aid) {
        return answerDao.queryByAId(aid);
    }

    /**
     * 根据qid查询对应问题下面的 回答条数
     *
     * @param qid
     * @return
     */
    @Override
    public int getCount(String qid) {
        return answerDao.getCount(qid);
    }

    /**
     * 根据aid查询回答 查寻按时间排列的前三个
     *
     * @param aid
     * @return
     */
    @Override
    public List<Answer> queryByAIdOrderByDate(String aid) {
        return answerDao.queryByAIdOrderByDate(aid);
    }

    /**
     * 有时间限制的查询分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Answer> queryByTimeLimit(int page, int size) {
        return answerDao.queryByTimeLimit(page, size);
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
        return answerDao.queryByQid(qid, page, size, date);
    }

    /**
     * 获取当前回答所在行数
     *
     * @param aid
     * @return
     */
    @Override
    public int LocalNum(String aid, String qid, Date date) {
        return answerDao.LocalNum(aid, qid, date);
    }

    /**
     * 根据设定的时间定时刷新首页的信息
     *
     * @param date
     * @return
     */
    @Override
    public List<Answer> queryList(Date date) {
        return answerDao.queryList(date);
    }

    /**
     * 通过uid获取当前的提问数量
     *
     * @param uid
     * @return
     */
    @Override
    public int getCountu(String uid) {
        return answerDao.getCountu(uid);
    }

    /**
     * 根据用户的uid查看用户的提问
     *
     * @param uid
     * @param page
     * @param size @return
     */
    @Override
    public List<Answer> queryAnswerListByUid(String uid, int page, int size, boolean isCurrentUser) {
        return answerDao.queryAnswerListByUid(uid, page, size, isCurrentUser);
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
        return answerDao.queryAnswerListByUidag(uid, page, size);
    }
}
