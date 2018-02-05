package com.ciexperts.projectmanagement.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ciexperts.projectmanagement.entity.User;
import com.ciexperts.projectmanagement.entity.UserRole;
import com.ciexperts.projectmanagement.tools.HibernateUtil;

public class UserDao {
	
	private Session session;
	
	public User findUser(String userId){
		
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		User user = null;
		
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, userId);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		System.out.println();
		return user;
	}

	public UserRole getUserRole(Integer roleId){
		
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		UserRole role = null;
		
		try {
			tx = session.beginTransaction();
			role = (UserRole) session.get(UserRole.class, roleId);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		System.out.println();
		return role;
	}
}
