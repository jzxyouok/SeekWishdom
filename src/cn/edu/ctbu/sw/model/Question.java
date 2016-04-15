package cn.edu.ctbu.sw.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * Question entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "question_tb")
public class Question implements java.io.Serializable {

    // Fields

    private String id;
    private Category category;
    private User user;
    private String title;
    private String content;
    private Date createTime;
    private Date editTime;
    private Short status;
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
    @JoinColumn(name = "cid")
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "usid")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(length = 200)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "CLOB")
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

    @Column(name = "edit_time")
    public Date getEditTime() {
        return this.editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Column
    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Column
    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}