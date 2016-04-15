package cn.edu.ctbu.sw.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.Date;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "answer_tb")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Answer implements java.io.Serializable {

    // Fields

    private String id;
    private Question question;
    private User user;
    private String content;
    private Integer agree;
    private Integer disagree;
    private short status;
    private Date createTime;
    private Date editTime;
    private boolean anonymous;//匿名


    // Property accessors

    @Id
    @GenericGenerator(name = "id", strategy = "uuid")
    @GeneratedValue(generator = "id")
    @Column(unique = true, nullable = false, length = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "qid")
    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    @JoinColumn(name = "usid")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(columnDefinition = "CLOB")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column
    public Integer getAgree() {
        return this.agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    @Column
    public Integer getDisagree() {
        return this.disagree;
    }

    public void setDisagree(Integer disagree) {
        this.disagree = disagree;
    }

    @Column(name = "status")
    public short getStatus() {
        return this.status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column
    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    @Column(name = "edit_time")
    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}