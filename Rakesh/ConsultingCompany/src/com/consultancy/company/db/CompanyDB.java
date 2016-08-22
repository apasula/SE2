package com.consultancy.company.db;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.consultancy.company.model.Company;

public class CompanyDB extends BaseDB {

	public Company get(Integer cnumber) {

		Company Company = null;

		try {
			Company = (Company) em.find(Company.class, cnumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Company;
	}

	public void insert(Company Company) {

		try {

			em.getTransaction().begin();
			em.persist(Company);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Company Company) {

		try {
			em.getTransaction().begin();
			em.merge(Company);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer cnumber) {

		try {

			em.getTransaction().begin();
			Company emp = (Company) em.find(Company.class, cnumber);
			em.remove(emp);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Company> allCompanys() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Company> cq = cb.createQuery(Company.class);
		Root<Company> rootEntry = cq.from(Company.class);
		CriteriaQuery<Company> all = cq.select(rootEntry);
		TypedQuery<Company> allQuery = em.createQuery(all);

		System.out.println("QUERYA");
		return allQuery.getResultList();
	}

	public static void main(String[] args) {
		CompanyDB CompanyDB = new CompanyDB();
		CompanyDB.allCompanys();
	}
}

