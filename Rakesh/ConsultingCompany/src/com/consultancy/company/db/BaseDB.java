package com.consultancy.company.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDB {

	public EntityManager em;

	public BaseDB() {
		try {
			
			System.out.println("BA");
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("ConsultingService");

			System.out.println("BAS");

			em = emf.createEntityManager();

		} catch (Exception e) {
			
			System.out.println("BAS");

			e.printStackTrace();
		}
		finally{
			System.out.println("FINALLY");
		}
		
		


	}
	
	

}
