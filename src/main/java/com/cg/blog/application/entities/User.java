package com.cg.blog.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")

public class User {

	@Id
	@GeneratedValue(generator = "userSeq")
	@SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1)
	private int id;


	@Size(min = 2, message = "user name should have at least 2 characters")
	@Column(name = "name", nullable = false)
	private String name;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@Size(min = 8, message = "password should have at least 8 characters")
	private String password;
	@Column(name = "role")
	@JsonIgnore
	private String role;
	@Column(name = "karma")
	@JsonIgnore
	private long karma;

//constructor
	public User() {
		super();
	}

	public User(int id, String name, String email, String password, String role, long karma) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.karma = karma;
	}

	public User(String name, String email, String password, String role, long karma) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.karma = karma;
	}

// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getKarma() {
		return karma;
	}

	public void setKarma(long karma) {
		this.karma = karma;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", karma=" + karma + "]";
	}

}
