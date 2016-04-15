package cn.edu.ctbu.sw.dao;

import cn.edu.ctbu.sw.model.Comment;

import java.util.List;

/**
 * Created by ys on 2015/11/17.
 */
public interface CommentDao extends SBaseDao<Comment> {
    /**
     * 根据aid查询评论
     *
     * @param aid
     * @return
     */
    List<Comment> queryByAId(String aid,int page,int size);

    /**
     * 根据qid查询评论
     *
     * @param qid
     * @return
     */
    List<Comment> queryByQId(String qid,int page,int size);

    /**
     * 根据aid查询其对应的查询评论数量
     *
     * @param aid
     * @return
     */
    int getACount(String aid);

    /**
     * 根据qid查询其对应的查询评论数量
     *
     * @param qid
     * @return
     */
    int getQCount(String qid);


}
