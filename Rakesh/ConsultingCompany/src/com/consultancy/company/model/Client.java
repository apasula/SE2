package com.consultancy.company.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	@Id
	private int cnumber;
	private String name;
	private String addressline1;
	private String addressline2;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String contact;
	private String invoicefreq;
	private String billingterms;
	private String invoicegrouping;
	private int active = 1;

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getCnumber() {
		return cnumber;
	}

	public void setCnumber(int cnumber) {
		this.cnumber = cnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getInvoicefreq() {
		return invoicefreq;
	}

	public void setInvoicefreq(String invoicefreq) {
		this.invoicefreq = invoicefreq;
	}

	public String getBillingterms() {
		return billingterms;
	}

	public void setBillingterms(String billingterms) {
		this.billingterms = billingterms;
	}

	public String getInvoicegrouping() {
		return invoicegrouping;
	}

	public void setInvoicegrouping(String invoicegrouping) {
		this.invoicegrouping = invoicegrouping;
	}

}
