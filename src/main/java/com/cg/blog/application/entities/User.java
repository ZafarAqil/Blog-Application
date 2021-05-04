package com.cg.blog.application.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(	name = "users", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "username"),
	@UniqueConstraint(columnNames = "email") 
})

public class User {

	@Id
	@GeneratedValue(generator = "userSeq")
	@SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1)
	private int id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	@Column(name = "karma")
	private long karma;

//constructor
	public User() {
		super();
	}

public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password, Set<Role> roles, long karma) {
	super();
	this.username = username;
	this.email = email;
	this.password = password;
	this.roles = roles;
	this.karma = karma;
}

public User(int id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password, Set<Role> roles, long karma) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;
	this.roles = roles;
	this.karma = karma;
}

public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password) {
	super();
	this.username = username;
	this.email = email;
	this.password = password;

}

public User(int id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;

}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
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

public Set<Role> getRoles() {
	return roles;
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
}

public long getKarma() {
	return karma;
}

public void setKarma(long karma) {
	this.karma = karma;
}



}
