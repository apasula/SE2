package com.consultancy.company.service;

import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.model.People;

public class UserLogin {

	public boolean accountantLogin(String username, String password) {

		if (username.equals(Constants.ACCOUNTANT_LOGIN)
				&& password.equals(Constants.ACCOUNTANT_PASSWORD)) {
			
			Constants.empname = "Accountant";

			return true;
		}
		return false;
	}

	public boolean developerLogin(String username, String password) {
		System.out.println("HERE");

		if (username.startsWith(Constants.DEVELOPER_LOGIN)
				&& password.startsWith(Constants.DEVELOPER_PASSWORD)) {

			System.out.println("HERE");
			try {
				Constants.empid = Integer.parseInt(username.replace(
						Constants.DEVELOPER_LOGIN, ""));
				
				PeopleDB peopleDB = new PeopleDB();
				People people =  peopleDB.get(Constants.empid);
				System.out.println(people.getRole());
				if(people ==null || people.getRole().equals("Project Manager")){
					return false;
				}
				else{
					Constants.empname = people.getName()+", "+people.getTitle()+", "+people.getRole();
				}
				
			} catch (Exception e) {
                e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean managerLogin(String username, String password) {

		if (username.startsWith(Constants.MANAGER_LOGIN)
				&& password.startsWith(Constants.MANAGER_PASSWORD)) {
			try {
				
				Constants.empid = Integer.parseInt(username.replace(
						Constants.MANAGER_LOGIN, ""));
				
				PeopleDB peopleDB = new PeopleDB();
				People people =  peopleDB.get(Constants.empid);
				System.out.println("MAN"+people.getRole());

				if(people ==null || people.getRole().equals("Developer")){
					return false;
				}
				else{
					Constants.empname = people.getName()+", "+people.getTitle()+", "+people.getRole();
				}
				
			} catch (Exception e) {
                e.printStackTrace();

				return false;
			}
			return true;
		}
		return false;
	}
}
