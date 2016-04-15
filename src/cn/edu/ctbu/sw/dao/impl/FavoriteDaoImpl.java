package cn.edu.ctbu.sw.dao.impl;

import cn.edu.ctbu.sw.dao.FavoriteDao;
import cn.edu.ctbu.sw.model.Favorite;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ys on 2015/11/19.
 */
@Repository("favoriteDao")
public class FavoriteDaoImpl extends SBaseDaoImpl<Favorite> implements FavoriteDao {

    /**
     * 通过 aid和uid判断当前问题是否被当前用户关注
     *
     * @param qid
     * @param uid
     * @return
     */
    @Override
    public Favorite isExistq(String qid, String uid) {
        String hql = "from Favorite f  where  f.question.id=:qid and  f.user.id=:uid";
        List<Favorite> favoriteList = getSession().createQuery(hql)
                .setString("qid", qid)
                .setString("uid", uid).list();
        if (favoriteList.size() > 0) {
            return favoriteList.get(0);
        }
        return null;

    }

    @Override
    public Favorite isExista(String aid, String uid) {
        String hql = "from Favorite f  where  f.answer.id=:aid and  f.user.id=:uid";
        List<Favorite> favoriteList = getSession().createQuery(hql)
                .setString("aid", aid)
                .setString("uid", uid).list();
        if (favoriteList.size() > 0) {
            return favoriteList.get(0);
        }
        return null;
    }

    /**
     * 通过 cid和uid判断当前分类是否被当前用户关注
     *
     * @param cid
     * @param uid
     * @return
     */
    @Override
    public Favorite isExistc(String cid, String uid) {
        String hql = "from Favorite f  where  f.category.id=:cid and  f.user.id=:uid";
        return (Favorite) getSession().createQuery(hql)
                .setString("cid", cid)
                .setString("uid", uid).uniqueResult();
    }

    /**
     * 根据qid查询其对应的问题被多少人关注
     *
     * @param qid
     * @return
     */
    @Override
    public Long getCountq(String qid) {
        String hql = "SELECT count(f) FROM Favorite  f WHERE f.question.id=:qid";
        return (long) getSession().createQuery(hql)
                .setString("qid", qid).uniqueResult();
    }

    /**
     * 根据cid查询其对应的分类下关注数量
     *
     * @param cid
     * @return
     */
    @Override
    public Long getCountc(String cid) {
        String hql = "SELECT count(f) FROM Favorite  f WHERE f.category.id=:cid";
        return (long) getSession().createQuery(hql)
                .setString("cid", cid).uniqueResult();
    }

    /**
     * 通过uid查询用户点赞
     *
     * @param uid
     * @return
     */
    @Override
    public List<Favorite> getFavoriteList(String uid) {
        String hql = "from Favorite f  where    f.user.id=:uid";
        return getSession().createQuery(hql)
                .setString("uid", uid).list();
    }

    /**
     * 根据aid查询改回答的点赞数量
     *
     * @param aid
     * @return
     */
    @Override
    public int getCounta(String aid) {
        String hql = "SELECT count(f) FROM Favorite  f WHERE f.answer.id=:aid";
        return (int) getSession().createQuery(hql)
                .setString("aid", aid).uniqueResult();
    }
}
