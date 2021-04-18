package com.cg.blog.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(generator = "adminSeq")
	@SequenceGenerator(name = "adminSeq", sequenceName = "admin_seq", allocationSize = 1)
	private int id;
	@NotEmpty
	@Size(min = 2, message = "Username should have at least 2 characters")
	@Column(name = "name", nullable = false)
	private String name;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@Size(min = 8, message = "Password should have at least 8 characters")
	private String password;
	@Column
	private String contact;

	// Constructors

	public Admin() {
		super();
	}

	public Admin(int id, @NotEmpty @Size(min = 2, message = "user name should have at least 2 characters") String name,
			@NotEmpty @Email String email,
			@NotEmpty @Size(min = 8, message = "password should have at least 8 characters") String password,
			String contact) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
	}

	public Admin(@NotEmpty @Size(min = 2, message = "user name should have at least 2 characters") String name,
			@NotEmpty @Email String email,
			@NotEmpty @Size(min = 8, message = "password should have at least 8 characters") String password,
			String contact) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		Admin admin = (Admin) obj;

		return this.getId() == admin.getId();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
