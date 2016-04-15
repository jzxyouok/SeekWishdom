package cn.edu.ctbu.sw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Standpoint entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agree_tb")
public class Agree implements java.io.Serializable {

    // Fields

    private String id;
    private Answer answer;
    private User user;
    private Boolean point;
    //添加一个字段
    private Comment comment;
    private Date createTime;



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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aid")
    public Answer getAnswer() {
        return this.answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @ManyToOne
    @JoinColumn(name = "usid")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "point")
    public Boolean getPoint() {
        return this.point;
    }

    public void setPoint(Boolean point) {
        this.point = point;
    }
    @ManyToOne
    @JoinColumn(name = "cid")
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}