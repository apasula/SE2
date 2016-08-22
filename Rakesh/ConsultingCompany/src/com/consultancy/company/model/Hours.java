package com.consultancy.company.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hours")
public class Hours {

	@Id
	private int id;
	private int empid;
	private int projectnumber;
	private String date;
	private int hours;
	private int approved = 0;

	
	
	public Hours(int empid, int projectnumber, String date, int hours) {
		super();
		this.empid = empid;
		this.projectnumber = projectnumber;
		this.date = date;
		this.hours = hours;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public int getProjectnumber() {
		return projectnumber;
	}

	public void setProjectnumber(int projectnumber) {
		this.projectnumber = projectnumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

}
