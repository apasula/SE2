package com.consultancy.company.db;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.consultancy.company.model.People;

public class PeopleDB extends BaseDB {

	public People get(Integer cnumber) {

		People People = null;

		try {
			People = (People) em.find(People.class, cnumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return People;
	}

	public void insert(People People) {

		try {

			em.getTransaction().begin();
			em.persist(People);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(People People) {

		try {
			em.getTransaction().begin();
			em.merge(People);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer cnumber) {

		try {

			em.getTransaction().begin();
			People emp = (People) em.find(People.class, cnumber);
			em.remove(emp);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<People> allPeoples() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<People> cq = cb.createQuery(People.class);
		Root<People> rootEntry = cq.from(People.class);
		CriteriaQuery<People> all = cq.select(rootEntry);
		TypedQuery<People> allQuery = em.createQuery(all);

		System.out.println("QUERYA");
		return allQuery.getResultList();
	}

	public static void main(String[] args) {
		PeopleDB PeopleDB = new PeopleDB();
		PeopleDB.allPeoples();
	}
}
