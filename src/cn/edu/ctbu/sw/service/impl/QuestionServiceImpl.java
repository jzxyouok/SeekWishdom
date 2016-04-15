package cn.edu.ctbu.sw.service.impl;


import cn.edu.ctbu.sw.model.Question;
import cn.edu.ctbu.sw.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @author ys
 * @version v1.0
 * @Title:QuestionServiceImpl.java
 * @Package cn.edu.qiuzhi.service.impl
 * @Description: TODO(分类)
 */
@Service("questionService")
public class QuestionServiceImpl extends SBaseServiceImpl<Question> implements QuestionService {

    @Override
    public List queryByCid(String id) {
        return questionDao.queryByCid(id);
    }

    @Override
    public List<Question> queryQuestionListByCid(String cid, int page, int size) {
        return questionDao.queryQuestionListByCid(cid, page, size);
    }

    /**
     * 根据cid查询对应分类下面的 question条数
     *
     * @param cid
     * @return
     */
    @Override
    public int getCount(String cid) {
        return questionDao.getCount(cid);
    }

    /**
     * 根据用户的uid查看用户的提问
     *
     * @param uid
     * @param page
     * @param size
     * @param isCurrentUser
     * @return
     */
    @Override
    public List<Question> queryQuestionListByUid(String uid, int page, int size, boolean isCurrentUser) {
        return questionDao.queryQuestionListByUid(uid, page, size, isCurrentUser);
    }

    /**
     * 通过uid获取当前的提问数量
     *
     * @param uid
     * @return
     */
    @Override
    public int getCountu(String uid) {
        return questionDao.getCountu(uid);
    }

    /**
     * 随机访问的没有回答的问题数量
     *
     * @param num
     * @return
     */
    @Override
    public List<Question> queryQuestionListWithNoAns() {
        return questionDao.queryQuestionListWithNoAns();
    }

    @Override
    public List<Question> searchByTitle(String title) {
        return questionDao.searchByTitle(title);
    }
}
