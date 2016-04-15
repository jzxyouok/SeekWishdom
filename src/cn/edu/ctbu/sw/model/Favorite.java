package cn.edu.ctbu.sw.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * AttentionOrCollect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "favorite_tb")
public class Favorite implements java.io.Serializable {

    // Fields

    private String id;
    private User user;
    private Date createTime;
    private Question question;
    private Answer answer;
    private Category category;//关注分类


    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(unique = true, nullable = false, length = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "usid")
    @Fetch(FetchMode.JOIN)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "qid")
    @Fetch(FetchMode.JOIN)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    @JoinColumn(name = "aid")
    @Fetch(FetchMode.JOIN)
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @ManyToOne
    @JoinColumn(name = "cid")
    @Fetch(FetchMode.JOIN)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}