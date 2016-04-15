package cn.edu.ctbu.sw.service.impl;

import cn.edu.ctbu.sw.model.Favorite;
import cn.edu.ctbu.sw.service.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ys on 2015/11/19.
 */
@Service("favoriteService")
public class FavoriteServiceImpl extends SBaseServiceImpl<Favorite> implements FavoriteService {

    /**
     * 通过 qid和uid判断当前问题是否被当前用户点赞
     *
     * @param qid
     * @param uid
     * @return
     */
    @Override
    public Favorite isExistq(String qid, String uid) {
        return favoriteDao.isExistq(qid, uid);
    }

    @Override
    public Favorite isExista(String aid, String uid) {
        return favoriteDao.isExista(aid, uid);
    }

    /**
     * 通过 cid和uid判断当前评论是否被当前用户点赞
     *
     * @param cid
     * @param uid
     * @return
     */
    @Override
    public Favorite isExistc(String cid, String uid) {
        return favoriteDao.isExistc(cid, uid);
    }

    /**
     * 根据qid查询其对应的问题被多少人关注
     *
     * @param qid
     * @return
     */
    @Override
    public Long getCountq(String qid) {
        return favoriteDao.getCountq(qid);
    }

    /**
     * 根据cid查询其对应的分类下关注数量
     *
     * @param cid
     * @return
     */
    @Override
    public Long getCountc(String cid) {
        return favoriteDao.getCountc(cid);
    }

    /**
     * 通过uid查询用户关注的类型
     *
     * @param uid
     * @return
     */
    @Override
    public List<Favorite> getFavoriteList(String uid) {
        return favoriteDao.getFavoriteList(uid);
    }

    /**
     * 根据aid查询改回答的点赞数量
     *
     * @param aid
     * @return
     */
    @Override
    public int getCounta(String aid) {
        return favoriteDao.getCounta(aid);
    }
}
