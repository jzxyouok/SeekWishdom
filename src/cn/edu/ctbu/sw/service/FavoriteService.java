package cn.edu.ctbu.sw.service;

import cn.edu.ctbu.sw.model.Favorite;

import java.util.List;

/**
 * Created by ys on 2015/11/19.
 */
public interface FavoriteService extends SBaseService<Favorite> {

    /**
     * 通过 qid和uid判断当前问题是否被当前用户点赞
     *
     * @param qid
     * @param uid
     * @return
     */
    Favorite isExistq(String qid, String uid);

    /**
     * 通过 aid和uid判断当前问题是否被当前用户关注
     *
     * @param aid
     * @param uid
     * @return
     */
    Favorite isExista(String aid, String uid);

    /**
     * 通过 cid和uid判断当前评论是否被当前用户点赞
     *
     * @param cid
     * @param uid
     * @return
     */
    Favorite isExistc(String cid, String uid);


    /**
     * 根据qid查询其对应的问题被多少人关注
     *
     * @param qid
     * @return
     */
    Long getCountq(String qid);

    /**
     * 根据cid查询其对应的分类下关注数量
     *
     * @param cid
     * @return
     */
    Long getCountc(String cid);

    /**
     * 通过uid查询用户关注的类型
     *
     * @param uid
     * @return
     */
    List<Favorite> getFavoriteList(String uid);

    /**
     * 根据aid查询改回答的点赞数量
     *
     * @param aid
     * @return
     */
    int getCounta(String aid);
}
