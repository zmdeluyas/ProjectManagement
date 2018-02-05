package com.ciexperts.projectmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ciexperts.projectmanagement.entity.Request;
import com.ciexperts.projectmanagement.entity.RequestHistory;
import com.ciexperts.projectmanagement.entity.RequestStatus;
import com.ciexperts.projectmanagement.entity.User;
import com.ciexperts.projectmanagement.tools.HibernateUtil;

public class RequestDao {

	private Session session;

	@SuppressWarnings("unchecked")
	public List<Request> listRequest() {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Request> requests = null;
		try {
			tx = session.beginTransaction();
			requests = session.createCriteria(Request.class).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return requests;
	}

	public Integer insertRequest(Request request) {
		session = HibernateUtil.getSessionFactory().openSession();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>req_TYPE_DAO" + request.getRequestType());
		Transaction tx = null;
		Integer requestNo = 0;
		try {
			tx = session.beginTransaction();
			requestNo = (Integer) session.save(request);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return requestNo;
	}

	public void updateRequest(Request request) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(request);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Request getReqInfoByNo(Integer reqNo) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Request request = new Request();
		try {
			tx = session.beginTransaction();
			request = (Request) session.get(Request.class, reqNo);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println();
		return request;
	}

	@SuppressWarnings("unchecked")
	public List<RequestHistory> listReqHist(Integer reqNo) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<RequestHistory> reqHists = null;
		try {
			tx = session.beginTransaction();
			if ("0".equals(reqNo)) {
				reqHists = session.createCriteria(RequestHistory.class).list();
			} else {
				reqHists = session.createCriteria(RequestHistory.class).add(Restrictions.eq("reqNo", reqNo)).list();
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return reqHists;
	}

	public RequestHistory getReqHistByNo(Integer rhNo) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RequestHistory reqHist = new RequestHistory();
		try {
			tx = session.beginTransaction();
			reqHist = (RequestHistory) session.get(RequestHistory.class, rhNo);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return reqHist;
	}

	public Integer insertRequestHistory(RequestHistory reqHist) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer rhNo = 0;
		try {
			tx = session.beginTransaction();
			rhNo = (Integer) session.save(reqHist);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rhNo;
	}

	public void updateReqHist(RequestHistory reqHist) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(reqHist);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/* added by SHARIE MANIPON 11.21.2017 */
	public String getReqEmail(Integer reqNo) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		String reqEmail = "";
		String sql = "SELECT b.email " + "FROM Request a, Users b " + "WHERE a.req_no = :reqNo AND a.requestor = b.first_name || ' ' || b.last_name"; 
		try {
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("reqNo", reqNo);
			reqEmail = (String) query.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return reqEmail;
	}

	public String getReqStatus(Integer reqNo) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		String reqStatus = "";
		String sql = "SELECT status_name " + "FROM Request_Status "
				+ "WHERE rs_no = (SELECT MAX(rs_no) rs_no FROM Request_History " + "WHERE req_no = :reqNo)";
		try {
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("reqNo", reqNo);
			reqStatus = (String) query.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return reqStatus;
	}

	@SuppressWarnings("unchecked")
	public List<RequestStatus> reqStatus() {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<RequestStatus> reqStatus = null;
		try {
			tx = session.beginTransaction();
			reqStatus = session.createCriteria(RequestStatus.class).addOrder(Order.asc("seqNo")).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return reqStatus;
	}

	@SuppressWarnings("unchecked")
	public List<User> listDevManager() {

		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> devManagerList = new ArrayList<User>();
		try {
			System.out.println(">>>>>>>>>>> DAO DevManager");
			tx = session.beginTransaction();
			//devManagerList = session.createQuery("FROM User").list();
			devManagerList = session.createQuery("FROM User WHERE userRole = :userRole").setParameter("userRole", 4).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return devManagerList;
	}

	@SuppressWarnings("unchecked")
	public List<User> listBusinessAnalyst() {

		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> businessAnalystList = new ArrayList<User>();
		try {
			System.out.println(">>>>>>>>>>> DAO Business Analyst");
			tx = session.beginTransaction();
			/* devManagerList = session.createQuery("FROM User").list(); */
			businessAnalystList = session.createCriteria(User.class).add(Restrictions.eq("userRole", 3)).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return businessAnalystList;
	}

	@SuppressWarnings("unchecked")
	public List<User> listQAManager() {

		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> qaManagerList = new ArrayList<User>();
		try {
			System.out.println(">>>>>>>>>>> DAO QA Manager");
			tx = session.beginTransaction();
			/* devManagerList = session.createQuery("FROM User").list(); */
			qaManagerList = session.createCriteria(User.class).add(Restrictions.eq("userRole", 5)).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return qaManagerList;
	}
	
	/*added by SHARIE MANIPON 11.20.2017*/
	@SuppressWarnings("unchecked")
	public List<User> listOPManager() {

		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> OPManagerList = new ArrayList<User>();
		try {
			System.out.println(">>>>>>>>>>> DAO OPManager");
			tx = session.beginTransaction();
			OPManagerList = session.createQuery("FROM User WHERE userRole = :userRole").setParameter("userRole", 7).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return OPManagerList;
	}

	@SuppressWarnings("unchecked")
	public List<User> listUserById(String assignedDev, String assignedBA, String assignedQA, String assignedOPs) {

		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> userList = null;
		System.err.println("User list dao >>>");
		try {
			
			tx = session.beginTransaction();
			userList = session.createQuery("From User where userId IN (:assignedDev,:assignedBA,:assignedQA,:assignedOPs)")
					.setParameter("assignedDev", assignedDev)
					.setParameter("assignedBA", assignedBA)
					.setParameter("assignedQA", assignedQA)
					.setParameter("assignedOPs", assignedOPs).list(); /* added by SHARIE MANIPON 11.21.2017 */
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return userList;
	}
	
	public Request getReqInfoByProjNo(Integer projNo){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Request reqInfo = new Request();
		try{
			tx = session.beginTransaction();
			reqInfo = (Request) session.createCriteria(Request.class).add(Restrictions.eq("requestType", 1)).add(Restrictions.eq("projNo", projNo)).uniqueResult();
			
			tx.commit();
		}catch (HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return reqInfo;
	}
}