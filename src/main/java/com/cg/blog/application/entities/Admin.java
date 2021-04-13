package com.cg.blog.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "admin")
public class Admin extends User{

	@Column
	private String contact;
	
	// Constructors

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, String name, String email, String password, String role, long karma, String contact) {
		super(id, name, email, password, role, karma);
		this.contact = contact;
	}
	public Admin(String name, String email, String password, String role, long karma, String contact) {
		super(name, email, password, role, karma);
		this.contact = contact;
	}

	// getters and setters

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Admin [contact=" + contact + ", toString()=" + super.toString() + "]";
	}

}
