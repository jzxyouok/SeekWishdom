package cn.edu.ctbu.sw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user_tb")
public class User implements java.io.Serializable {

	// Fields

	private String id;
	private String email;
	private String password;
	private String nickname;
	private String avatar;
	private Short status;
	private String summary;



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

	@Column(length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 32)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(length = 256)
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(length = 256)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}