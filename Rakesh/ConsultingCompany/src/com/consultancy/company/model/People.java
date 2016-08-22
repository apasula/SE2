package com.consultancy.company.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class People {

	@Id
	private int id;
	private String name;
	private String title;
	private int billrate;
	private String role;
	private int active=1;

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBillrate() {
		return billrate;
	}

	public void setBillrate(int billrate) {
		this.billrate = billrate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
