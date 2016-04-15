package cn.edu.ctbu.sw.dao;

import cn.edu.ctbu.sw.model.Agree;

/**
 * Created by ys on 2015/11/18.
 */
public interface AgreeDao extends SBaseDao<Agree> {
    /**
     * 根据aid查询其对应的点赞数量
     *
     * @param aid
     * @return
     */
    int getACount(String aid);

    /**
     * 根据cid查询其对应评论的点赞数量
     *
     * @param
     * @return
     */
    int getCountc(String cid);

    /**
     * 通过 aid和uid判断当前回答是否被当前用户点赞
     *
     * @param aid
     * @param uid
     * @return
     */
    Agree IsExist(String aid, String uid);

    /**
     * 通过 cid和uid判断当前评论是否被当前用户点赞
     *
     * @param cid
     * @param uid
     * @return
     */
    Agree IsExistc(String cid, String uid);

    /**
     * 通过 aid和uid判断当前回答是否被当前用户点赞
     *
     * @param aid
     * @param uid
     * @return
     */
    boolean IsAgreeExista(String aid, String uid);

    /**
     * 通过uid获取当前的点赞数量
     *
     * @param uid
     * @return
     */
    int getCountu(String uid);
}
