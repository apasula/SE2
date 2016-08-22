package com.consultancy.company.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
	@Id
	private int pnumber;
	private int client;
	private String name;
	private String startdate;
	private String enddate;
	private String status;
	private String manager;
	private String contact;
	private int budget;

	public int getPnumber() {
		return pnumber;
	}

	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

}
