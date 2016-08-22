package com.consultancy.company.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projectperson")
public class ProjectPerson {

	@Id
	private int id;
	private int projectnumber;
	private String person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectnumber() {
		return projectnumber;
	}

	public void setProjectnumber(int projectnumber) {
		this.projectnumber = projectnumber;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

}
