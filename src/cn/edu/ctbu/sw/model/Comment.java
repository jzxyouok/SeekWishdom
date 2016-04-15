package cn.edu.ctbu.sw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment_tb")
public class Comment implements java.io.Serializable {

    // Fields

    private String id;
    private User user;
    private String content;
    private Date createTime;
    private Integer agree;
    private Question question;
    private Answer answer;
    private int status;//字段就是以屏蔽已删除正常这些状态


    @Id
    @GenericGenerator(name = "id", strategy = "uuid")
    @GeneratedValue(generator = "id")
    @Column(name = "id", unique = true, nullable = false, length = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "usid")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(length = 2000)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
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
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    @JoinColumn(name = "aid")
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Column
    public Integer getAgree() {
        return this.agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    @Column
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}