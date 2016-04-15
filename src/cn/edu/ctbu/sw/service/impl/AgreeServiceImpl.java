package cn.edu.ctbu.sw.service.impl;


import cn.edu.ctbu.sw.model.Agree;
import cn.edu.ctbu.sw.service.AgreeService;
import org.springframework.stereotype.Service;

/**
 * Created by ys on 2015/11/18.
 */
@Service("agreeService")
public class AgreeServiceImpl extends SBaseServiceImpl<Agree> implements AgreeService {

    /**
     * 根据aid查询其对应的点赞数量
     *
     * @param aid
     * @return
     */
    @Override
    public int getACount(String aid) {
        return agreeDao.getACount(aid);
    }

    /**
     * 根据cid查询其对应评论的点赞数量
     *
     * @param cid
     * @return
     */
    @Override
    public int getCountc(String cid) {
        return agreeDao.getCountc(cid);
    }

    /**
     * 通过 aid和uid判断当前回答是否被当前用户点赞
     *
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public Agree IsExist(String aid, String uid) {
        return agreeDao.IsExist(aid, uid);
    }

    /**
     * 通过 cid和uid判断当前评论是否被当前用户点赞
     *
     * @param cid
     * @param uid
     * @return
     */
    @Override
    public Agree IsExistc(String cid, String uid) {
        return agreeDao.IsExistc(cid, uid);
    }

    /**
     * 通过 aid和uid判断当前回答是否被当前用户点赞
     *
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public boolean IsAgreeExista(String aid, String uid) {
        return agreeDao.IsAgreeExista(aid, uid);
    }

    /**
     * 通过uid获取当前的点赞数量
     *
     * @param uid
     * @return
     */
    @Override
    public int getCountu(String uid) {
        return agreeDao.getCountu(uid);
    }
}
