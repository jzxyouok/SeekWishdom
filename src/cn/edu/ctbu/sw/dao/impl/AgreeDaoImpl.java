package cn.edu.ctbu.sw.dao.impl;

import cn.edu.ctbu.sw.dao.AgreeDao;
import cn.edu.ctbu.sw.model.Agree;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ys on 2015/11/18.
 */
@Repository("agreeDao")
public class AgreeDaoImpl extends SBaseDaoImpl<Agree> implements AgreeDao {

    /**
     * 根据aid查询其对应的点赞数量
     *
     * @param aid
     * @return
     */
    @Override
    public int getACount(String aid) {
        String hql = "SELECT count(a) FROM Agree  a WHERE a.answer.id=:aid";
        return Integer.parseInt(getSession().createQuery(hql)
                .setString("aid", aid).uniqueResult().toString());
    }

    /**
     * 根据cid查询其对应评论的点赞数量
     *
     * @param cid@return
     */
    @Override
    public int getCountc(String cid) {
        String hql = "SELECT count(a) FROM Agree  a WHERE a.comment.id=:cid";
        return Integer.parseInt(getSession().createQuery(hql)
                .setString("cid", cid).uniqueResult().toString());
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
        String hql = "from Agree a  where  a.answer.id=:aid and  a.user.id=:uid";
        return (Agree) getSession().createQuery(hql)
                .setString("aid", aid)
                .setString("uid", uid).uniqueResult();
    }

    /**
     * 通过 aid和uid判断当前回答是否被当前用户点赞
     *
     * @param aid
     * @param uid
     * @return
     */
    public boolean IsAgreeExista(String aid, String uid) {
        String hql = "from Agree a  where  a.answer.id=:aid and  a.user.id=:uid";
        List l = getSession().createQuery(hql).setString("aid", aid)
                .setString("uid", uid).list();
        if (l.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 通过uid获取当前的点赞数量
     *
     * @param uid
     * @return
     */
    @Override
    public int getCountu(String uid) {
        String hql = "SELECT count(a) FROM Agree a WHERE  a.user.id=:uid";
        int count = Integer.parseInt(getSession().createQuery(hql).setString("uid", uid).uniqueResult().toString());
        return count;
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
        String hql = "from Agree a  where  a.comment.id=:cid and  a.user.id=:uid";
        return (Agree) getSession().createQuery(hql)
                .setString("cid", cid)
                .setString("uid", uid).uniqueResult();
    }


}
