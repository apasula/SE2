package com.consultancy.company.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.consultancy.company.model.Hours;
import com.consultancy.company.model.People;

public class HoursDB extends BaseDB {

	public Hours get(Integer cnumber) {

		Hours Hours = null;

		try {
			Hours = (Hours) em.find(Hours.class, cnumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Hours;
	}

	public void insert(Hours Hours) {

		try {
			em.getTransaction().begin();
			em.persist(Hours);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Hours Hours) {

		try {
			em.getTransaction().begin();
			em.merge(Hours);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer cnumber) {

		try {

			em.getTransaction().begin();
			Hours emp = (Hours) em.find(Hours.class, cnumber);
			em.remove(emp);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Hours> allHourss() {

		Query query = em.createQuery("Select h from Hours h");
		return query.getResultList();
	}

	public List<Hours> getHoursbypnumber(int projectnumber) {

		Query query = em
				.createQuery("Select h from Hours h where h.projectnumber=:arg1");
		query.setParameter("arg1", projectnumber);
		return query.getResultList();
	}

	public List<Object[]> getProjectHours(int projectnumber) {

		return em
				.createQuery(
						"SELECT sum(hours) as hours,empid,projectnumber FROM Hours h group by h.empid having h.projectnumber = :projectnumber")
				.setParameter("projectnumber", projectnumber).getResultList();
		
	}
	
	public List<Object[]> getHoursbypnumber(int projectnumber,String startdate,String enddate) throws Exception{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		
		
		return em
				.createQuery(
						"SELECT sum(hours) as hours,empid,projectnumber FROM Hours h where DATE( STR_TO_DATE( h.date,  '%m/%d/%Y' )) >= :startdate and DATE( STR_TO_DATE( h.date,  '%m/%d/%Y' )) <= :enddate group by h.empid having h.projectnumber = :projectnumber")
				.setParameter("startdate", sdf.parse(startdate)).setParameter("enddate", sdf.parse(enddate)).setParameter("projectnumber", projectnumber).getResultList();
		
	}

	public List getWorkedHours() {

		Query query = null;
		try {
			query = em
					.createQuery("SELECT sum(hours) as hours,empid,projectnumber,approved FROM Hours h group by h.empid having h.approved=:arg1");
			query.setParameter("arg1", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.getResultList();
	}

	public List<Hours> allnonapprovedhours() {

		Query query = em
				.createQuery("Select h from Hours h where h.approved=:arg1");
		query.setParameter("arg1", 0);
		return query.getResultList();
	}

	public double usedbudget(int projectnumber) {

		List<Object[]> data = getProjectHours(projectnumber);
		double projectbudget = 0;

		for (Object[] object : data) {
			PeopleDB peopleDB = new PeopleDB();
			People people = peopleDB.get((Integer) object[1]);
			if(people==null)
				continue;
			projectbudget += people.getBillrate() * (Long) object[0] ;
		}
		return projectbudget;
	}

	public static void main(String[] args) {
		HoursDB hoursDB = new HoursDB();
		hoursDB.getProjectHours(1002);
	}

}