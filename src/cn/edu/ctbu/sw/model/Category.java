package cn.edu.ctbu.sw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "category_tb")
public class Category implements java.io.Serializable {

    // Fields

    private String id;

    private String category;


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

    @Column(length = 200)
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}