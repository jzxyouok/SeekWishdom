package cn.edu.ctbu.sw.dao;


import cn.edu.ctbu.sw.model.Question;

import java.util.Date;
import java.util.List;

public interface QuestionDao extends SBaseDao<Question> {
    List queryByCid(String id);

    // 查询cid下面的 question并按照 page,size分页查询
    List<Question> queryQuestionListByCid(String cid, int page, int size);

    /**
     * 根据cid查询对应分类下面的 question条数
     *
     * @param cid
     * @return
     */
    int getCount(String cid);


    /**
     * 根据用户的uid查看用户的提问
     *
     * @param uid
     * @return
     */
    List<Question> queryQuestionListByUid(String uid, int page, int size, boolean isCurrentUser);


    /**
     * 通过uid获取当前的提问数量
     *
     * @param uid
     * @return
     */
    int getCountu(String uid);

    /**
     * 随机访问的没有回答的问题数量
     * * @param
     *
     * @return
     */
    List<Question> queryQuestionListWithNoAns();


    List<Question> searchByTitle(String title);
}
