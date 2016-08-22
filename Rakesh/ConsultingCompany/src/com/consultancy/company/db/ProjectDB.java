package com.consultancy.company.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.consultancy.company.model.Project;

public class ProjectDB extends BaseDB {

	public Project get(Integer cnumber) {

		Project Project = null;

		try {
			Project = (Project) em.find(Project.class, cnumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Project;
	}

	public void insert(Project Project) {

		try {

			System.out.println("Adding Project" + Project.getPnumber());
			em.getTransaction().begin();
			em.persist(Project);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Project Project) {

		try {
			em.getTransaction().begin();
			em.merge(Project);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer cnumber) {

		try {

			em.getTransaction().begin();
			Project emp = (Project) em.find(Project.class, cnumber);
			em.remove(emp);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Project> allProjects() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Project> cq = cb.createQuery(Project.class);
		Root<Project> rootEntry = cq.from(Project.class);
		CriteriaQuery<Project> all = cq.select(rootEntry);
		TypedQuery<Project> allQuery = em.createQuery(all);

		return allQuery.getResultList();
	}

	public List<String> getprojectNames() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Project> cq = cb.createQuery(Project.class);
		Root<Project> rootEntry = cq.from(Project.class);
		CriteriaQuery<Project> all = cq.select(rootEntry);
		TypedQuery<Project> allQuery = em.createQuery(all);
		List<Project> projects = allQuery.getResultList();

		List<String> projectNames = new ArrayList<>();
		for (Project project : projects) {
			projectNames.add(project.getName());
		}

		return projectNames;
	}
	
	

	
	
}
