package com.example.dao;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.example.model.CafeHistory;
import com.example.model.CafeOccupancyIntermediate;
import com.example.model.Cafeteria;
import com.example.model.Capacity;
import com.example.schedular.CafeOccupancySchedular;
import com.example.schedular.CafeSchedularJob;
import com.example.utils.CafeHistoryUtils;
import com.example.utils.CapacityUtilBase;
import com.example.utils.CapacityUtilIntermediate;
import com.example.utils.RandomUtil;


@Repository
@Transactional
public class CafeteriaRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	final static Logger LOGGER = Logger.getLogger(CafeOccupancySchedular.class);
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Transactional
	public Cafeteria save(Cafeteria cafeteria){
		 Session session = this.sessionFactory.getCurrentSession();
		 session.save(cafeteria);
		 return cafeteria;
	}
	
	
	public List<Cafeteria> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Cafeteria>  cafeteriaList = session.createQuery("from Cafeteria",Cafeteria.class).getResultList();
		return cafeteriaList;
	}
	
	public Cafeteria getDetails(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Cafeteria cafeteria = (Cafeteria) session.get(Cafeteria.class, id);
		return cafeteria;
	}
	
	
	public JSONArray getDiffTimeCountByServiceArea() throws ParseException {
		LOGGER.info("Data For Legends...");
		Session session = this.sessionFactory.getCurrentSession();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Cafeteria> results=null;
		JSONObject jsonObj1 = new JSONObject();
        JSONArray responseserviceArray = new JSONArray();
        JSONArray responsediningArray = new JSONArray();
        
		Integer pplCount;
		String find;
		//Color Color;
		String Color;
		Date dt=new Date();
		JSONArray responseArray = new JSONArray();
		try 
		{
		 Calendar c = Calendar.getInstance();
	     c.setTime(dt);
	     c.add(Calendar.MINUTE, -5);
	     Date dtime = c.getTime();
	     for(int i=0; i<2; i++)
	     {
			String spaceType = "";
			if(i==0){
				spaceType = "Service Area";
				}
			else{
				spaceType = "Dining Area";
				}
			System.out.println(spaceType);
		    CriteriaBuilder cb = session.getCriteriaBuilder();
			javax.persistence.criteria.CriteriaQuery<Cafeteria> query = cb.createQuery(Cafeteria.class);
			javax.persistence.criteria.CriteriaQuery<Integer> query1 = cb.createQuery(Integer.class);
			Root<Cafeteria> root = query.from(Cafeteria.class);
			Root<Cafeteria> root1 = query1.from(Cafeteria.class);
			Predicate[] predicates = new Predicate[3];
			predicates[0] = cb.lessThanOrEqualTo(root.get("inserted_datetime"),dt );
			predicates[1] = cb.greaterThanOrEqualTo(root.get("inserted_datetime"), dtime);
			predicates[2] = cb.equal(root.get("spacetype"), spaceType);
			query.select(root.get("peoplecount")).where(predicates);
			query1.select(root1.get("peoplecount")).where(predicates);
			Query<Cafeteria> q = session.createQuery(query);
			results = q.getResultList();
			pplCount = session.createQuery(query1).getSingleResult();
			if(i==0) {
			find=CapacityUtilBase.FindTypeService(pplCount);
			Color=CapacityUtilBase.FindColorSevice(pplCount);}
			else {
				find=CapacityUtilBase.FindTypeDining(pplCount);
				Color=CapacityUtilBase.FindColorDining(pplCount);
			}
			
			for (int j = 0; j < results.size(); j++) 
			{
				System.out.println(results);
				 JSONObject jsonObj = new JSONObject();
				 if(i==0){
				 jsonObj.put("Service Area", results.get(j));
				 jsonObj.put("Type Service", find);
				 jsonObj.put("Color Service", Color);
				 responseserviceArray.put(jsonObj);
				 }else{
				 jsonObj.put("Dining Area", results.get(j));
				 jsonObj.put("Type Dining", find);
				 jsonObj.put("Color Dining", Color);
				 responsediningArray.put(jsonObj);
				 }
				 //responseArray.put(jsonObj);
			}
			jsonObj1.put("servicedata", responseserviceArray);
            jsonObj1.put("diningdata", responsediningArray);
		}
	     responseArray.put(jsonObj1);
	    }
		catch(Exception e){
			e.printStackTrace();
			
		} 
		return responseArray;
		
	}
	public void saveOccupancyIntermediate1(CafeOccupancyIntermediate ci) {
		Session session ;
		session= this.sessionFactory.getCurrentSession();
		System.out.println("in repo post");
		System.out.println(ci);
		session.save(ci);
	}
	public void saveOccupancyHistory(CafeHistory ci) {
		// TODO Auto-generated method stub
		Session session ;
		session= this.sessionFactory.getCurrentSession();
		System.out.println("in repo post");
		System.out.println(ci);
		session.save(ci);
	}

	public JSONArray TrendGraph() throws ParseException {
		LOGGER.info("Data For Live Occupancy Trend Graph...");
		Session session = this.sessionFactory.getCurrentSession();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<CafeOccupancyIntermediate> results=null;
		List<CafeOccupancyIntermediate> results1=null;
		JSONObject jsonObj1 = new JSONObject();
        JSONArray responseserviceArray = new JSONArray();
        JSONArray responsediningArray = new JSONArray();
		Date dt=new Date();
		List<Double> pplCount=null;
		String find;
		//Color Color;
		String Color;
//		String d= ("2020-12-19 15:00:00");  
//	    Date dt=dateFormat.parse(d);
		JSONArray responseArray = new JSONArray();
		//JSONArray responseArray1 = new JSONArray();
		//JSONArray responseArray2 = new JSONArray();
		try 
		{
		 Calendar c = Calendar.getInstance();
	     c.setTime(dt);
	     c.add(Calendar.HOUR, -4);
	     Date dtime = c.getTime();
	     for(int i=0; i<2; i++)
	     {
			String spaceType = "";
			if(i==0){
				spaceType = "Service Area";
				}
			else{
				spaceType = "Dining Area";
				}
			System.out.println(spaceType);
		    CriteriaBuilder cb = session.getCriteriaBuilder();
			javax.persistence.criteria.CriteriaQuery<CafeOccupancyIntermediate> query = cb.createQuery(CafeOccupancyIntermediate.class);
			javax.persistence.criteria.CriteriaQuery<CafeOccupancyIntermediate> query1 = cb.createQuery(CafeOccupancyIntermediate.class);
			javax.persistence.criteria.CriteriaQuery<Double> query2 = cb.createQuery(Double.class);
			Root<CafeOccupancyIntermediate> root = query.from(CafeOccupancyIntermediate.class);
			Root<CafeOccupancyIntermediate> root1 = query1.from(CafeOccupancyIntermediate.class);
			Root<CafeOccupancyIntermediate> root2 = query2.from(CafeOccupancyIntermediate.class);
			Predicate[] predicates = new Predicate[3];
			predicates[0] = cb.lessThanOrEqualTo(root.get("created_date"),dt );
			predicates[1] = cb.greaterThanOrEqualTo(root.get("created_date"), dtime);
			predicates[2] = cb.equal(root.get("spacetype"), spaceType);
//			Path<Double> pc = root.get("peoplecount");
//			Path<String> ts = root.get( "timeslot");
//			query.multiselect(cb.array(pc,ts));
//			query.where(predicates);
			query.select(root.get("peoplecount")).where(predicates);
			query1.select(root1.get("timeslot")).where(predicates);
			query2.select(root2.get("peoplecount")).where(predicates);
			//query.multiselect(root.get("peoplecount"),root.get("timeslot")).where(predicates);
			Query<CafeOccupancyIntermediate> q = session.createQuery(query);
			Query<CafeOccupancyIntermediate> q1 = session.createQuery(query1);
			results = q.getResultList();
			results1 = q1.getResultList();
			pplCount = session.createQuery(query2).getResultList();
			System.out.println(results.size());
			System.out.println(results1.size());
			System.out.println(pplCount);
			for (int j = 0; j < results.size(); j++) 
			{
				 JSONObject jsonObj = new JSONObject();
				 //JSONObject jsonObj1 = new JSONObject();
				 if(i==0){
					jsonObj.put("timeslot", results1.get(j));
					jsonObj.put("Service Area", results.get(j));
					find=CapacityUtilIntermediate.FindTypeService(pplCount.get(j));
					Color=CapacityUtilIntermediate.FindColorSevice(pplCount.get(j));
					jsonObj.put("Type Service", find);
					jsonObj.put("Color Service", Color);
					responseserviceArray.put(jsonObj);
				
				 }else{
					 jsonObj.put("Dining Area", results.get(j));
					 jsonObj.put("timeslot", results1.get(j));
					 find=CapacityUtilIntermediate.FindTypeDining(pplCount.get(j));
					 Color=CapacityUtilIntermediate.FindColorDining(pplCount.get(j));
					 jsonObj.put("Type Dining", find);
					 jsonObj.put("Color Dining", Color);
					responsediningArray.put(jsonObj);
				 } 
			}
			jsonObj1.put("servicedata", responseserviceArray);
            jsonObj1.put("diningdata", responsediningArray);
		}
	     responseArray.put(jsonObj1);
	    }
		catch(Exception e){
			e.printStackTrace();
			
		} 
		return responseArray;
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public JSONArray findAllByDatetimeBetween(Date startdate, Date enddate) {
		LOGGER.info("Data For Historical Occupancy Trend Graph...");
		Session session = this.sessionFactory.getCurrentSession();
		JSONArray responseArray = new JSONArray();
		List<CafeHistory> results=null;
		List<CafeHistory> results1=null;
		List<Double> pplCount=null;
		
		String find;
		//Color Color;
		
		String Color;
		JSONObject jsonObj1 = new JSONObject();
        JSONArray responseserviceArray = new JSONArray();
        JSONArray responsediningArray = new JSONArray();
//		List<Cafeteria> results=session.createQuery("SELECT peoplecount FROM Cafeteria AS c WHERE (c.inserted_datetime BETWEEN :startdate AND :enddate) ")
//		.setParameter("startdate", startdate)
//		.setParameter("enddate", enddate)
//		.list();
		try {
		for(int i=0; i<2; i++)
	     {
			Calendar c = Calendar.getInstance();
		     c.setTime(enddate);
		     c.add(Calendar.DATE, 1);
		  //   enddate = c.getTime();
		     System.out.println("helooo"+enddate);
			String spaceType = "";
			if(i==0){
				spaceType = "Service Area";
				}
			else{
				spaceType = "Dining Area";
				}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		javax.persistence.criteria.CriteriaQuery<CafeHistory> query = cb.createQuery(CafeHistory.class);
		javax.persistence.criteria.CriteriaQuery<CafeHistory> query1 = cb.createQuery(CafeHistory.class);
		javax.persistence.criteria.CriteriaQuery<Double> query2 = cb.createQuery(Double.class);
		//javax.persistence.criteria.CriteriaQuery<Date> query3 = cb.createQuery(Date.class);
		Root<CafeHistory> root = query.from(CafeHistory.class);
		Root<CafeHistory> root1 = query1.from(CafeHistory.class);
		Root<CafeHistory> root2 = query2.from(CafeHistory.class);
		//Root<CafeHistory> root3 = query3.from(CafeHistory.class);
		Predicate[] predicates = new Predicate[2];
		predicates[0]=cb.between(root.get("inserted_date"), startdate,enddate);
		//predicates[1]=cb.equal(root.get("inserted_date"), enddate);
		//predicates[0] = cb.greaterThanOrEqualTo(root.get("inserted_date"), startdate);
		//predicates[1] = cb.lessThanOrEqualTo(root.get("inserted_date"), enddate);
		predicates[1] = cb.equal(root.get("spacetype"), spaceType);
		//predicates[2] = cb.orderBy(cb.asc(root.get("inserted_date")));
		
        System.out.println("helllooooooo"+enddate);
		query.select(root.get("peoplecount")).where(predicates);
		query1.select(root1.get("inserted_date")).where(predicates);
		query2.select(root2.get("peoplecount")).where(predicates);
		query.orderBy(cb.asc(root.get("inserted_date")));
		query1.orderBy(cb.asc(root1.get("inserted_date")));
		query2.orderBy(cb.asc(root1.get("inserted_date")));
		//query3.select(root.get("inserted_date")).where(predicates);
		Query<CafeHistory> q = session.createQuery(query);
		results = q.getResultList();
		Query<CafeHistory> q1 = session.createQuery(query1);
		results1 = q1.getResultList();
		pplCount = session.createQuery(query2).getResultList();
		//test= session.createQuery(query3).getResultList();
		for (int j = 0; j < results.size(); j++) 
		{
			System.out.println(results.size());
			JSONObject jsonObj = new JSONObject();
			if(i==0) {
				//System.out.println(test.get(j));
			System.out.println(results.size());
				//System.out.println("hhhhhhiiiiii"+results1.get(j));
			jsonObj.put("Service Area", results.get(j));
			jsonObj.put("Timeslot", results1.get(j));
			System.out.println(pplCount.get(j)+"pppppppppppppp");
			find=CafeHistoryUtils.FindTypeService(pplCount.get(j));
			Color=CafeHistoryUtils.FindColorSevice(pplCount.get(j));
			jsonObj.put("Type Service", find);
			jsonObj.put("Color Service", Color);
				responseserviceArray.put(jsonObj);
			}else {
				//System.out.println(test.get(j));
				//System.out.println(results.size());
				//System.out.println("bbbbbbbbiiiiii"+results1.get(j));
			 jsonObj.put("Dining Area", results.get(j));
			 jsonObj.put("Timeslot", results1.get(j));
			 find=CafeHistoryUtils.FindTypeDining(pplCount.get(j));
			 Color=CafeHistoryUtils.FindColorDining(pplCount.get(j));
			 jsonObj.put("Type Dining", find);
			 jsonObj.put("Color Dining", Color);
			 responsediningArray.put(jsonObj);
			}
			// responseArray.put(jsonObj);
		}
		jsonObj1.put("servicedata", responseserviceArray);
		
        jsonObj1.put("diningdata", responsediningArray);
		}
		
		 responseArray.put(jsonObj1);
		
//		
//		
//		}
//		LOGGER.info("Data For Historical Occupancy Trend Graph...");
//		Session session = this.sessionFactory.getCurrentSession();
//		JSONArray responseArray = new JSONArray();
//		List<CafeHistory> results=null;
//		List<CafeHistory> results1=null;
//		List<Double> pplCount=null;
//		List<Date> test=null;
//		String find;
//		//Color Color;
//		
//		String Color;
//		JSONObject jsonObj1 = new JSONObject();
//        JSONArray responseserviceArray = new JSONArray();
//        JSONArray responsediningArray = new JSONArray();
//		try {
//		for(int i=0; i<2; i++)
//	     {
//			Calendar c = Calendar.getInstance();
//		     c.setTime(enddate);
//		     c.add(Calendar.DATE, 1);
//		    enddate = c.getTime();
//		     System.out.println("helooo"+enddate);
//			String spaceType = "";
//			if(i==0){
//				spaceType = "Service Area";
//				}
//			else{
//				spaceType = "Dining Area";
//				}
//		CriteriaBuilder cb = session.getCriteriaBuilder();
//		javax.persistence.criteria.CriteriaQuery<CafeHistory> query = cb.createQuery(CafeHistory.class);
//		
//
//			Root<CafeHistory> root = query.from(CafeHistory.class);
////			
//		    Criteria crit = sessionFactory.getCurrentSession().createCriteria(CafeHistory.class);
//		    crit.add(Restrictions.ge("inserted_date", startdate));
//		    crit.add(Restrictions.le("inserted_date", enddate));
//		    //crit.select(root.get("peoplecount"));
//		    List list = crit.list();
//		    
//		for (int j = 0; j < list.size(); j++) 
//			{
//				//System.out.println(results.size());
//				JSONObject jsonObj = new JSONObject();
//				if(i==0) {
//			//System.out.println(results.size());
//					//System.out.println("hhhhhhiiiiii"+results1.get(j));
//			jsonObj.put("Service Area", list.get(j));
//				//jsonObj.put("Timeslot", results11.get(j));
//				//
//					responseserviceArray.put(jsonObj);
//				}else {
//					//System.out.println(test.get(j));
//					//System.out.println(results.size());
//					System.out.println("bbbbbbbbiiiiii"+list.get(j));
//				 jsonObj.put("Dining Area", list.get(j));
//				 
//				 responsediningArray.put(jsonObj);
//				}
//				// responseArray.put(jsonObj);
//		}
//		jsonObj1.put("servicedata", responseserviceArray);
//        jsonObj1.put("diningdata", responsediningArray);
//		}
//		 responseArray.put(jsonObj1);
//		
//		
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return responseArray;
		
	}
	
	

	@Transactional
	public void saveOccupancyBase(Cafeteria cafe) {
		Session session ;
		session= this.sessionFactory.getCurrentSession();
		System.out.println("in repo post");
		System.out.println(cafe);
		session.save(cafe);
	}

	

	public void saveOccupancyIntermediate(CafeOccupancyIntermediate ci) {
		Session session ;
		session= this.sessionFactory.getCurrentSession();
		//System.out.println("in repo post");
		System.out.println(ci);
		session.save(ci);
	}

	
	public Double getFromBaseDining() throws ParseException {
		Session session = sessionFactory.getCurrentSession();
		List<Cafeteria> results=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Object avg = null;
		Double avg=null;
		//System.out.println("done");
		//System.out.println("repo insert");
		Date dt = new Date();
//		String d= ("2020-12-01 12:05:00");  
//	    Date date1=dateFormat.parse(d);
		try {
			 Calendar c = Calendar.getInstance();
		     c.setTime(dt);
		     c.add(Calendar.HOUR, -1);
		     Date dtime = c.getTime();
		     System.out.println(dt);
		     System.out.println(dtime);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		//javax.persistence.criteria.CriteriaQuery<Object> query = cb.createQuery(Object.class);
//		javax.persistence.criteria.CriteriaQuery<Cafeteria> query = cb.createQuery(Cafeteria.class);
//		Root<Cafeteria> root = query.from(Cafeteria.class);
//		Predicate[] predicates = new Predicate[3];
//		predicates[0] = cb.lessThanOrEqualTo(root.get("inserted_datetime"),date1 );
//		predicates[1] = cb.greaterThanOrEqualTo(root.get("inserted_datetime"), dtime);
//		predicates[2] = cb.equal(root.get("spacetype"),"Dining Area");
//		query.multiselect((cb.avg(root.get("peoplecount")))).where(predicates);
		
		javax.persistence.criteria.CriteriaQuery<Double> query = cb.createQuery(Double.class);
		Root<Cafeteria> root = query.from(Cafeteria.class);
		Predicate[] predicates = new Predicate[3];
		predicates[0] = cb.lessThanOrEqualTo(root.get("inserted_datetime"),dt );
		predicates[1] = cb.greaterThanOrEqualTo(root.get("inserted_datetime"), dtime);
		predicates[2] = cb.equal(root.get("spacetype"),"Dining Area");
		query.multiselect((cb.avg(root.get("peoplecount")))).where(predicates);
		avg=session.createQuery(query).getSingleResult();
			
		//List<Cafeteria> results =  (List<Cafeteria>) session.createQuery("Select avg(peoplecount) from Cafeteria where ").list();
		//Query q = session.createQuery(query);
		//results=(List) q;
		//results=(Integer) q.uniqueResult();
		//results= (List<Cafeteria>) q;
		//results = q.getResultList();
		//avg = (Double) ((Criteria) results).list().get(0);
		
		//avg = q.getSingleResult();
		//System.out.println(avg+"inn");
		//Integer k=avg.intValue();
		
		//System.out.println(k+"inn");
		
		}
//		for(Cafeteria peoplecount:results) {
//			a=peoplecount.getPeoplecount();
//			//System.out.println(results);
//			System.out.println(a.intValue());
//		}
//		//int avg;
////	
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return avg;
		
	}
	
	
	
	public Double getFromBaseService() throws ParseException {
		Session session = sessionFactory.getCurrentSession();
		List<Cafeteria> results=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Double avg=null;
		System.out.println("done");
		System.out.println("repo insert");
		Date dt = new Date();
//		String d= ("2020-12-01 12:05:00");  
//	    Date date1=dateFormat.parse(d);
		try {
			 Calendar c = Calendar.getInstance();
		     c.setTime(dt);
		     c.add(Calendar.HOUR, -1);
		     Date dtime = c.getTime();
		     System.out.println(dt);
		     System.out.println(dtime);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		javax.persistence.criteria.CriteriaQuery<Double> query = cb.createQuery(Double.class);
		Root<Cafeteria> root = query.from(Cafeteria.class);
		Predicate[] predicates = new Predicate[3];
		predicates[0] = cb.lessThanOrEqualTo(root.get("inserted_datetime"),dt );
		predicates[1] = cb.greaterThanOrEqualTo(root.get("inserted_datetime"), dtime);
		predicates[2] = cb.equal(root.get("spacetype"),"Service Area");
		query.multiselect((cb.avg(root.get("peoplecount")))).where(predicates);
		avg=session.createQuery(query).getSingleResult();
		System.out.println(avg+"inn");
		}

		catch(Exception e) {
			e.printStackTrace();
		}
		
		return avg;
		
	}
	
	
	

	public void saveCapacity(Capacity cp) {
	
		Session session= this.sessionFactory.getCurrentSession();
		System.out.println("in repo post");
		System.out.println(cp);
		session.save(cp);
		
	}
	
	
	public Integer getDiningCapacityFromBase() {
		Session session = sessionFactory.getCurrentSession();
		List<Cafeteria> results=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer pplCount=null;
		System.out.println("done");
		System.out.println("repo hiiiiiiiiiiiiiiii");
		Date dt = new Date();
//		String d= ("2020-12-01 12:05:00");  
//	    Date date1=dateFormat.parse(d);
		try {
			 Calendar c = Calendar.getInstance();
		     c.setTime(dt);
		     c.add(Calendar.MINUTE, -6);
		     Date dtime = c.getTime();
		     System.out.println(dt);
		     System.out.println(dtime);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		javax.persistence.criteria.CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
		Root<Cafeteria> root = query.from(Cafeteria.class);
		Predicate[] predicates = new Predicate[3];
		predicates[0] = cb.lessThanOrEqualTo(root.get("inserted_datetime"),dt );
		predicates[1] = cb.greaterThanOrEqualTo(root.get("inserted_datetime"), dtime);
		predicates[2] = cb.equal(root.get("spacetype"),"Dining Area");
		query.select((root.get("peoplecount"))).where(predicates);
		pplCount=session.createQuery(query).getSingleResult();
		System.out.println("ppppppp");
		System.out.println(pplCount+"inn");
		}

		catch(NoResultException e) {
			e.printStackTrace();
		}
		return pplCount;
		
	}
	
	
	

	public Integer getServiceCapacityFromBase() {
		
		Session session = sessionFactory.getCurrentSession();
		List<Cafeteria> results=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer pplCount=null;
		System.out.println("done");
		System.out.println("repo hiiiiiiiiiiiiiiii");
		Date dt = new Date();
//		String d= ("2020-12-01 12:05:00");  
//	    Date date1=dateFormat.parse(d);
		try {
			 Calendar c = Calendar.getInstance();
		     c.setTime(dt);
		     c.add(Calendar.MINUTE, -6);
		     Date dtime = c.getTime();
		     System.out.println(dt);
		     System.out.println(dtime);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		javax.persistence.criteria.CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
		Root<Cafeteria> root = query.from(Cafeteria.class);
		Predicate[] predicates = new Predicate[3];
		predicates[0] = cb.lessThanOrEqualTo(root.get("inserted_datetime"),dt );
		predicates[1] = cb.greaterThanOrEqualTo(root.get("inserted_datetime"), dtime);
		predicates[2] = cb.equal(root.get("spacetype"),"Service Area");
		query.select((root.get("peoplecount"))).where(predicates);
		pplCount=session.createQuery(query).getSingleResult();
		System.out.println("ppppppp");
		System.out.println(pplCount+"inn");
		}

		catch(Exception e) {
			e.printStackTrace();
		}
		return pplCount;
	}
	
	

	public double getFromIntermediateDining() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Cafeteria> results=null;
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Object avg = null;
		Double avg=null;
		System.out.println("done");
		System.out.println("repo insert");
		Date dt = new Date();
//		String d= ("2020-12-01 12:05:00");  
//	    Date date1=dateFormat.parse(d);
		try {
			 Calendar c = Calendar.getInstance();
		     c.setTime(dt);
		     c.add(Calendar.DATE, -1);
		     Date dtime = c.getTime();
		     System.out.println(dt);
		     System.out.println(dtime);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		
		javax.persistence.criteria.CriteriaQuery<Double> query = cb.createQuery(Double.class);
		Root<CafeOccupancyIntermediate> root = query.from(CafeOccupancyIntermediate.class);
		Predicate[] predicates = new Predicate[3];
		predicates[0] = cb.lessThanOrEqualTo(root.get("inserted_date"),dt );
		predicates[1] = cb.greaterThanOrEqualTo(root.get("inserted_date"), dtime);
		predicates[2] = cb.equal(root.get("spacetype"),"Dining Area");
		query.multiselect((cb.avg(root.get("peoplecount")))).where(predicates);
		avg=session.createQuery(query).getSingleResult();
		
		System.out.println(avg+"inn");
	
		
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return avg;
		
	}
	public double getFromIntermediateService() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Cafeteria> results=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Object avg = null;
		Double avg=null;
		System.out.println("done");
		System.out.println("repo insert");
		Date dt = new Date();
//		String d= ("2020-12-01 12:05:00");  
//	    Date date1=dateFormat.parse(d);
		try {
			 Calendar c = Calendar.getInstance();
		     c.setTime(dt);
		     c.add(Calendar.DATE, -1);
		     Date dtime = c.getTime();
		     System.out.println(dt);
		     System.out.println(dtime);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		
		javax.persistence.criteria.CriteriaQuery<Double> query = cb.createQuery(Double.class);
		Root<CafeOccupancyIntermediate> root = query.from(CafeOccupancyIntermediate.class);
		Predicate[] predicates = new Predicate[3];
		predicates[0] = cb.lessThanOrEqualTo(root.get("inserted_date"),dt );
		predicates[1] = cb.greaterThanOrEqualTo(root.get("inserted_date"), dtime);
		predicates[2] = cb.equal(root.get("spacetype"),"Service Area");
		query.multiselect((cb.avg(root.get("peoplecount")))).where(predicates);
		avg=session.createQuery(query).getSingleResult();
		
		System.out.println(avg+"inn");
	
		
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return avg;
		
	}

	public Date getFromIntermediateinsertedTime() throws ParseException {
		// TODO Auto-generated method stub
		//Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.getCurrentSession();
		List<Cafeteria> results=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Object avg = null;
		Double avg=null;
		Date dtime=null;
		System.out.println("done");
		System.out.println("repo insert");
		Date dt = new Date();
		//String d= ("2020-12-20");  
	    //Date date1=dateFormat.parse(d);
		
		
		try {
			 Calendar c = Calendar.getInstance();
		     c.setTime(dt);
		     c.add(Calendar.DATE, -1);
		      dtime = c.getTime();
		     System.out.println(dt);
		     System.out.println(dtime);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		
		javax.persistence.criteria.CriteriaQuery<Date> query = cb.createQuery(Date.class);
		Root<CafeOccupancyIntermediate> root = query.from(CafeOccupancyIntermediate.class);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtime;
		
		
	}


	


	
	
	@SuppressWarnings("unchecked")
	public List<Cafeteria> findPeopleCountForDateRange(Date startdate, Date enddate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Cafeteria> results=session.createQuery("SELECT peoplecount FROM Cafeteria AS c WHERE c.datetime BETWEEN :startdate AND :enddate ")
		.setParameter("startdate", startdate)
		.setParameter("enddate", enddate)
		.list();
		return results;
		
	}





	


}
