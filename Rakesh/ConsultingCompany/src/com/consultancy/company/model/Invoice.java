package com.consultancy.company.model;

public class Invoice {

	private int totalhours=0;
	private String personaname;
	private double billrate=0;

	public int getTotalhours() {
		return totalhours;
	}

	public void setTotalhours(int totalhours) {
		this.totalhours = totalhours;
	}

	public String getPersonaname() {
		return personaname;
	}

	public void setPersonaname(String personaname) {
		this.personaname = personaname;
	}

	public double getBillrate() {
		return billrate;
	}

	public void setBillrate(double billrate) {
		this.billrate = billrate;
	}

}
