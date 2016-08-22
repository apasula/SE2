package com.consultancy.company.db;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.consultancy.company.model.ProjectPerson;

public class ProjectPersonDB extends BaseDB {

	public ProjectPerson get(Integer cnumber) {

		ProjectPerson ProjectPerson = null;

		try {
			ProjectPerson = (ProjectPerson) em.find(ProjectPerson.class,
					cnumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProjectPerson;
	}

	public void insert(ProjectPerson ProjectPerson) {

		try {

			em.getTransaction().begin();
			em.persist(ProjectPerson);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(ProjectPerson ProjectPerson) {

		try {
			em.getTransaction().begin();
			em.merge(ProjectPerson);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer cnumber) {

		try {

			em.getTransaction().begin();
			ProjectPerson emp = (ProjectPerson) em.find(ProjectPerson.class,
					cnumber);
			em.remove(emp);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ProjectPerson> allProjectPersons() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProjectPerson> cq = cb.createQuery(ProjectPerson.class);
		Root<ProjectPerson> rootEntry = cq.from(ProjectPerson.class);
		CriteriaQuery<ProjectPerson> all = cq.select(rootEntry);
		TypedQuery<ProjectPerson> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}
	

	
	public static void main(String[] args) {
		ProjectPersonDB ProjectPersonDB = new ProjectPersonDB();
		ProjectPersonDB.allProjectPersons();
	}
}
