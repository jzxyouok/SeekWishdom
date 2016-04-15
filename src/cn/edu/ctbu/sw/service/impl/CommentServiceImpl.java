package cn.edu.ctbu.sw.service.impl;

import cn.edu.ctbu.sw.model.Comment;
import cn.edu.ctbu.sw.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ys on 2015/11/17.
 */
@Service("commentService")
public class CommentServiceImpl extends SBaseServiceImpl<Comment> implements CommentService {


    /**
     * 根据aid查询评论
     *
     * @param aid
     * @param page
     * @param size @return
     */
    @Override
    public List<Comment> queryByAId(String aid, int page, int size) {
        return commentDao.queryByAId(aid, page, size);
    }

    /**
     * 根据qid查询评论
     *
     * @param qid
     * @param page
     * @param size @return
     */
    @Override
    public List<Comment> queryByQId(String qid, int page, int size) {
        return commentDao.queryByAId(qid, page, size);
    }

    /**
     * 根据aid查询其对应的查询评论数量
     *
     * @param aid@return
     */
    @Override
    public int getACount(String aid) {
        return commentDao.getACount(aid);
    }

    /**
     * 根据qid查询其对应的查询评论数量
     *
     * @param qid
     * @return
     */
    @Override
    public int getQCount(String qid) {
        return commentDao.getQCount(qid);
    }
}
