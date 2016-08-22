package com.consultancy.company.db;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.consultancy.company.model.Client;

public class ClientDB extends BaseDB {

	public Client get(Integer cnumber) {

		Client Client = null;

		try {
			Client = (Client) em.find(Client.class, cnumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Client;
	}

	public void insert(Client client) {

		try {

			System.out.println("Adding client"+client.getCnumber());
			em.getTransaction().begin();
			em.persist(client);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Client Client) {

		try {
			em.getTransaction().begin();
			em.merge(Client);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer cnumber) {

		try {

			em.getTransaction().begin();
			Client emp = (Client) em.find(Client.class, cnumber);
			em.remove(emp);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Client> allClients() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> rootEntry = cq.from(Client.class);
		CriteriaQuery<Client> all = cq.select(rootEntry);
		TypedQuery<Client> allQuery = em.createQuery(all);
		
		System.out.println("QUERYA");
		return allQuery.getResultList();
	}
	
	public static void main(String[] args){
		ClientDB clientDB = new ClientDB();
		clientDB.allClients();
	}
}
