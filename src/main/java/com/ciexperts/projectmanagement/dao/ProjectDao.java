package com.ciexperts.projectmanagement.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ciexperts.projectmanagement.entity.Project;
import com.ciexperts.projectmanagement.entity.ProjectCost;
import com.ciexperts.projectmanagement.entity.ProjectHistory;
import com.ciexperts.projectmanagement.entity.ProjectInfra;
import com.ciexperts.projectmanagement.entity.ProjectPeriod;
import com.ciexperts.projectmanagement.entity.ProjectStatus;
import com.ciexperts.projectmanagement.tools.HibernateUtil;

public class ProjectDao {

	private Session session;
	
	@SuppressWarnings("unchecked")
	public List<Project> listProjects(){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Project> projects = null;
		try{
			tx = session.beginTransaction();
			projects = session.createCriteria(Project.class).list();
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projects;
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> listApprvProjects(){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Project> projects = null;
		try{
			tx = session.beginTransaction();
			projects = session.createCriteria(Project.class).add(Restrictions.eq("approved", 1)).list();
			System.out.println(">>>>>>DAO Approved");
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projects;
	}
	
	public Project getProjInfoByNo(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Project project = new Project();
		try{
			tx = session.beginTransaction();
			project = (Project) session.get(Project.class, projNo);
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return project;
	}
	
	public ProjectPeriod getProjPeriodByProjNo(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ProjectPeriod projPeriod = new ProjectPeriod();
		try{
			tx = session.beginTransaction();
			projPeriod = (ProjectPeriod) session.createCriteria(ProjectPeriod.class).add(Restrictions.eq("projNo", projNo)).uniqueResult();
			
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projPeriod;
	}
	
	public ProjectInfra getProjInfraByProjNo(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ProjectInfra projInfra = new ProjectInfra();
		try{
			tx = session.beginTransaction();
			projInfra = (ProjectInfra) session.createCriteria(ProjectInfra.class).add(Restrictions.eq("projNo", projNo)).uniqueResult();
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projInfra;
	}
	
	public ProjectCost getProjCostByProjNo(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ProjectCost projCost = new ProjectCost();
		try{
			tx = session.beginTransaction();
			projCost = (ProjectCost) session.createCriteria(ProjectCost.class).add(Restrictions.eq("projNo", projNo)).uniqueResult();
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projCost;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectPeriod> listProjPeriod(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<ProjectPeriod> projPeriods = null;
		try{
			tx = session.beginTransaction();
			if("0".equals(projNo)){
				projPeriods = session.createCriteria(ProjectPeriod.class).list();
			}else{
				projPeriods = session.createCriteria(ProjectPeriod.class).add(Restrictions.eq("projNo", projNo)).list();
			}
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projPeriods;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectInfra> listProjInfra(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<ProjectInfra> projInfra = null;
		try{
			tx = session.beginTransaction();
			if("0".equals(projNo)){
				projInfra = session.createCriteria(ProjectInfra.class).list();
			}else{
				projInfra = session.createCriteria(ProjectInfra.class).add(Restrictions.eq("projNo", projNo)).list();
			}
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projInfra;
	}
	
	public Integer insertProject(Project project){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer projNo = 0;
		try{
			tx = session.beginTransaction();
			projNo = (Integer) session.save(project);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projNo;
	}
	
	public Integer insertProjectPeriod(ProjectPeriod projectPeriod){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer ppNo = 0;
		try{
			tx = session.beginTransaction();
			ppNo = (Integer) session.save(projectPeriod);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return ppNo;
	}
	
	public Integer insertProjectInfra(ProjectInfra projectInfra){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer piNo = 0;
		try{
			tx = session.beginTransaction();
			piNo = (Integer) session.save(projectInfra);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return piNo;
	}
	
	public Integer insertProjectCost(ProjectCost projectCost){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer pcNo = 0;
		try{
			tx = session.beginTransaction();
			pcNo = (Integer) session.save(projectCost);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return pcNo;
	}
	
	public void updateProject(Project project){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(project);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	
	
	public void updateProjectPeriod(ProjectPeriod projectPeriod){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(projectPeriod);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void updateProjectInfra(ProjectInfra projectInfra){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(projectInfra);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void updateProjectCost(ProjectCost projectCost){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(projectCost);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/*	ROCHELLE VILLARUZ	*/
	
	@SuppressWarnings("unchecked")
	public List<ProjectHistory> listProjHist(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<ProjectHistory> projHists = null;
		try{
			tx = session.beginTransaction();
			if("0".equals(projNo)){
				projHists = session.createCriteria(ProjectHistory.class).list();
			}else{
				projHists = session.createCriteria(ProjectHistory.class).add(Restrictions.eq("projNo", projNo)).list();
			}
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projHists;
	}
	
	public ProjectHistory getProjHistByNo(Integer phNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ProjectHistory projHist = new ProjectHistory();
		try{
			tx = session.beginTransaction();
			projHist = (ProjectHistory) session.get(ProjectHistory.class, phNo);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projHist;
	}
	
	public Integer insertProjHistory(ProjectHistory projHist){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer phNo = 0;
		try{
			tx = session.beginTransaction();
			phNo = (Integer) session.save(projHist);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return phNo;
	}
	
	public void updateProjHist(ProjectHistory projHist){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(projHist);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public String getProjStatus(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		String projStatus = "";
		String sql = "SELECT status_name " +
					   "FROM Project_Status " +
					   "WHERE ps_no = (SELECT MAX(ps_no) ps_no FROM Project_History " +
					   				   "WHERE proj_no = :projNo)";
		try{
			tx = session.beginTransaction();
			SQLQuery  query = session.createSQLQuery(sql);
			query.setParameter("reqNo", projNo);
			projStatus = (String) query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projStatus;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectStatus> projStatus(){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<ProjectStatus> projStatus = null;
		try{
			tx = session.beginTransaction();
			projStatus = session.createCriteria(ProjectStatus.class).addOrder(Order.asc("seqNo")).list();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projStatus;
	}
	
	public void updateProjectSubmit(Project project){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
//			session.update(project);
			String hqlUpdate = "UPDATE Project p SET p.submitted = :submitted, p.approved = :approved where p.projNo = :projNo";
			// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
			session.createQuery( hqlUpdate )
				   .setInteger("approved", 0)
			       .setInteger( "submitted", project.getSubmitted() )
			       .setInteger( "projNo", project.getProjNo() )
			       .executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void updateProjectApprove(Project project){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
//			session.update(arg0, arg1);
			String hqlUpdate = "UPDATE Project p SET  p.submitted = :submitted, p.approved = :approved where p.projNo = :projNo";
			// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
			session.createQuery( hqlUpdate )
					.setInteger("submitted", 1)
					.setInteger( "approved", project.getApproved() )
					.setInteger( "projNo", project.getProjNo() )
			       .executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
