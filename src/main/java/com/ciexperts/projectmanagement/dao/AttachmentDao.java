package com.ciexperts.projectmanagement.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.ciexperts.projectmanagement.entity.ProjectAttachment;
import com.ciexperts.projectmanagement.entity.RequestAttachment;
import com.ciexperts.projectmanagement.tools.HibernateUtil;

public class AttachmentDao {
	
	private Session session;

	//method for requests attachments uploading and saving STARTS here...
	public Integer saveReqAttachment(RequestAttachment reqAttachment){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer requestNo = 0;
		try{
			tx = session.beginTransaction();
			requestNo = (Integer) session.save(reqAttachment);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return requestNo;
	}
	
	public RequestAttachment getReqAttachmentByFileName(String fileName){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RequestAttachment requestAttachment = new RequestAttachment();
		try{
			tx = session.beginTransaction();
			requestAttachment = (RequestAttachment)  session.createCriteria(RequestAttachment.class).add(Restrictions.eq("fileName", fileName)).uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return requestAttachment;
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestAttachment> getReqAttachmentListByReqNo(Integer reqNo){
		List<RequestAttachment> reqAttachments = new ArrayList<RequestAttachment>();
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			reqAttachments =  session.createCriteria(RequestAttachment.class).add(Restrictions.eq("reqNo", reqNo)).list();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return reqAttachments;
	}
	//method for requests attachments uploading and saving ENDS here...
	
	//method for projects attachments uploading and saving STARTS here...
	public Integer saveProjAttachment(ProjectAttachment projAttachment){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer projNo = 0;
		try{
			tx = session.beginTransaction();
			projNo = (Integer) session.save(projAttachment);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		System.out.println("projNo: " + projNo);
		return projNo;
	}
	
	public ProjectAttachment getProjAttachmentByFileName(String fileName){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ProjectAttachment projectAttachment = new ProjectAttachment();
		try{
			tx = session.beginTransaction();
			projectAttachment = (ProjectAttachment)  session.createCriteria(ProjectAttachment.class).add(Restrictions.eq("fileName", fileName)).uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projectAttachment;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectAttachment> getProjAttachmentListByReqNo(Integer projNo){
		List<ProjectAttachment> projAttachments = new ArrayList<ProjectAttachment>();
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			projAttachments =  session.createCriteria(ProjectAttachment.class).add(Restrictions.eq("projNo", projNo)).list();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projAttachments;
	}
	//method for requests uploading and saving ENDS here...
	
	

}
