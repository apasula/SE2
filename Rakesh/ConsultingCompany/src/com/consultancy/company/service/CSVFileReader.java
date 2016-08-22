package com.consultancy.company.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.CompanyDB;
import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.db.ProjectPersonDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.Company;
import com.consultancy.company.model.People;
import com.consultancy.company.model.Project;
import com.consultancy.company.model.ProjectPerson;

public class CSVFileReader {

	public void addProjectPersons(String filepath) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(filepath));
			System.out.println(filepath);
			int temp = 0;
			while ((line = br.readLine()) != null) {

				if (temp == 0) {
					temp++;
					continue;
				}

				String[] projectPersonArray = line.split(cvsSplitBy);
				ProjectPerson people = new ProjectPerson();
				people.setProjectnumber(Integer.parseInt(projectPersonArray[0].replaceAll("[-+.^:,'\"﻿]", "")));
				people.setPerson(projectPersonArray[1].replaceAll("[-+.^:,'\"]", ""));

				ProjectPersonDB projectPersonDB = new ProjectPersonDB();
				projectPersonDB.insert(people);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					System.out.println("CLosing");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void addPeoples(String filepath) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(filepath));
			System.out.println(filepath);
			int temp = 0;
			while ((line = br.readLine()) != null) {

				if (temp == 0) {
					temp++;
					continue;
				}

				String[] peopleArray = line.split(cvsSplitBy);
				People people = new People();
				people.setName(peopleArray[0].replaceAll("[-+.^:,'\"﻿]", ""));
				people.setTitle(peopleArray[1].replaceAll("[-+.^:,'\"﻿]", ""));
				people.setBillrate(Integer.parseInt(peopleArray[2].replaceAll(
						" ", "").replaceAll("[-+.^:,'\"]", "")));
				people.setRole(peopleArray[3].replaceAll("[-+.^:,'\"]", ""));

				PeopleDB peopleDB = new PeopleDB();
				peopleDB.insert(people);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					System.out.println("CLosing");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void addProjects(String filepath) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(filepath));
			System.out.println(filepath);
			int temp = 0;
			while ((line = br.readLine()) != null) {

				if (temp == 0) {
					temp++;
					continue;
				}

				String[] projectArray = line.split(cvsSplitBy);
				Project project = new Project();
				project.setPnumber(Integer.parseInt(projectArray[1].replaceAll(
						"[-+.^:,'\"﻿]", "")));
				project.setClient(Integer.parseInt(projectArray[0].replaceAll(
						"[-+.^:,'\"﻿]", "")));
				project.setName(projectArray[2].replaceAll(" ", "").replaceAll(
						"[-+.^:,'\"]", ""));
				project.setStartdate(projectArray[3].replaceAll("[-+.^:,'\"]",
						""));
				project.setEnddate(projectArray[4]
						.replaceAll("[-+.^:,'\"]", ""));
				project.setStatus(projectArray[5].replaceAll("[-+.^:,'\"]", ""));
				project.setManager(projectArray[6]
						.replaceAll("[-+.^:,'\"]", ""));
				project.setContact(projectArray[7]
						.replaceAll("[-+.^:,'\"]", ""));
				project.setBudget(Integer.parseInt(projectArray[8].replaceAll(
						"[-+.^:,'\"]", "")));

				ProjectDB projectDB = new ProjectDB();
				projectDB.insert(project);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					System.out.println("CLosing");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public void addCompany(String filepath) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(filepath));
			System.out.println(filepath);
			int temp = 0;
			while ((line = br.readLine()) != null) {

				if (temp == 0) {
					temp++;
					continue;
				}

				String[] companyArray = line.split(cvsSplitBy);
				Company company = new Company();
				company.setName(companyArray[0].replaceAll("[-+.^:,'\"﻿]", ""));
				company.setAddressline1(companyArray[1].replaceAll(" ", "")
						.replaceAll("[-+.^:,'\"]", ""));
				company.setAddressline2(companyArray[2].replaceAll("[-+.^:,'\"]",
						""));
				company.setCity(companyArray[3].replaceAll("[-+.^:,'\"]", ""));
				company.setState(companyArray[4].replaceAll("[-+.^:,'\"]", ""));
				company.setZip(companyArray[5].replaceAll("[-+.^:,'\"]", ""));
				CompanyDB companyDB = new CompanyDB();
				companyDB.insert(company);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					System.out.println("CLosing");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void addClients(String filepath) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(filepath));
			System.out.println(filepath);
			int temp = 0;
			while ((line = br.readLine()) != null) {

				if (temp == 0) {
					temp++;
					continue;
				}

				String[] clientArray = line.split(cvsSplitBy);
				Client client = new Client();
				client.setCnumber(Integer.parseInt(clientArray[0].replaceAll(
						"[-+.^:,'\"﻿]", "")));
				client.setName(clientArray[1].replaceAll("[-+.^:,'\"﻿]", ""));
				client.setAddressline1(clientArray[2].replaceAll(" ", "")
						.replaceAll("[-+.^:,'\"]", ""));
				client.setAddressline2(clientArray[3].replaceAll("[-+.^:,'\"]",
						""));
				client.setCity(clientArray[4].replaceAll("[-+.^:,'\"]", ""));
				client.setState(clientArray[5].replaceAll("[-+.^:,'\"]", ""));
				client.setZip(clientArray[6].replaceAll("[-+.^:,'\"]", ""));
				client.setEmail(clientArray[7].replaceAll("[-+.^:,'\"]", ""));
				client.setContact(clientArray[8].replaceAll("[-+.^:,'\"]", ""));
				client.setInvoicefreq(clientArray[9].replaceAll("[-+.^:,'\"]",
						""));
				client.setBillingterms(clientArray[10].replaceAll(
						"[-+.^:,'\"]", ""));
				client.setInvoicegrouping(clientArray[11].replaceAll(
						"[-+.^:,'\"]", ""));

				System.out.println("ADD clients" + client.getCnumber());
				ClientDB clientDB = new ClientDB();
				clientDB.insert(client);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					System.out.println("CLosing");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
