package cn.edu.ctbu.sw.action;

import cn.edu.ctbu.sw.model.Answer;
import cn.edu.ctbu.sw.model.Question;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pomodoro on 2015/11/23.
 */
@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace(value = "/")
@Results({@Result(location = "/index.jsp", type = "redirect", name = "reindex")


})
public class IndexAction extends SBaseAction<Answer> {

    List<Answer> list = new ArrayList<>();
    List<Question> list2 = new ArrayList<>();

    @Action("index")
    public String init() {
        boolean isLogin = false;
        //判断当前是否有用户登录
        if (getUser() == null) {//当前用户未登录
        } else {
            isLogin = true;
            session.put("user", getUser());
        }
        list = answerService.query();
        list2 = questionService.query();
        List list3 = new ArrayList<>();
        out:
        for (Question question : list2) {
            for (Answer answer : list) {
                if (answer.getQuestion().getId().equals(question.getId())) {
                    continue out;
                }
            }
            list3.add(question);
        }
        session.put("isLogin", isLogin);
        session.put("indexList", list);
        session.put("indexList2", list3);
        return "reindex";
    }


}
