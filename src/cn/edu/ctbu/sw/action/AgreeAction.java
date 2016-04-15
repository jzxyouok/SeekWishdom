package cn.edu.ctbu.sw.action;

import cn.edu.ctbu.sw.model.Agree;
import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.User;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayInputStream;
import java.util.Date;

/**
 * Created by ys on 2015/11/18.
 */
@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace(value = "/agree")
@Results({@Result(location = "http://www.baidu.com", type = "redirect"),
})
public class AgreeAction extends SBaseAction<Agree> {

    /**
     * 回答的点赞或者取消
     * 第一次点为点赞(save)，第二次问取消(delete)
     */
    @Action("saveOrDeletea")
    public String saveOrDeletea() {
        if (getUser() == null) {
            return "login";
        }
        //1查询当前是否有该条记录
        //传递aid
        String aid = requestParam.getParameter("aid");
        //获取当前的angswer
        Answer answer = answerService.get(aid);
        Agree agree = agreeService.IsExist(aid, getUser().getId());
        if (agree == null) {//添加点赞
            agree = model;
            agree.setUser(getUser());
            agree.setAnswer(answer);
            agree.setCreateTime(new Date());
            agree.setPoint(true);//点赞
            agreeService.save(agree);
            inputStream = new ByteArrayInputStream("1".getBytes());
        } else {
            //取消点赞 删除该条记录
            agreeService.delete(agree.getId());
            inputStream = new ByteArrayInputStream("0".getBytes());
        }
        return "stream";
    }

    /**
     * 问题的点赞或者取消
     * 第一次点为点赞(save)，第二次问取消(delete)
     */
    @Action("saveOrDeletec")
    public String saveOrDeletec() {
        //1查询当前是否有该条记录
        User user = (User) session.get("user");
        Agree agree = agreeService.IsExistc(model.getComment().getId(), user.getId());
        if (agree == null) {//点赞
            agree = model;
            agree.setUser(user);
            agree.setPoint(true);
            agreeService.save(agree);
            inputStream = new ByteArrayInputStream("true".getBytes());
        } else {
            agreeService.delete(agree.getId());
            inputStream = new ByteArrayInputStream("true".getBytes());
        }
        return "stream";
    }
}
