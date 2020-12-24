package com.example.service;

import java.awt.Color;
import java.text.ParseException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CafeteriaRepository;
import com.example.model.CafeHistory;
import com.example.model.CafeOccupancyIntermediate;
import com.example.model.Cafeteria;
import com.example.model.Capacity;
import com.example.utils.CapacityUtilBase;
import com.example.utils.RandomUtil;
import com.example.utils.RandomUtilIntermediate;

@Service
public class CafeteriaService {

	
		
	@Autowired
	CafeteriaRepository cafeteriaRepository;
	
	
	public void setUserReposatory(CafeteriaRepository cafeteriaRepository) {
		this.cafeteriaRepository = cafeteriaRepository;
	}

	public Cafeteria save(Cafeteria cafeteria){
		 return cafeteriaRepository.save(cafeteria);
	}
	
	public List<Cafeteria> getAllUsers() {
		return cafeteriaRepository.getAllUsers();
	}
	
	public Cafeteria getDetails(long id) {
		return cafeteriaRepository.getDetails(id);
	}
	
	
	public void saveOccupancyBase() {
		
			
		Cafeteria c = randomcall1();
		Cafeteria d = randomcall2();
		cafeteriaRepository.saveOccupancyBase(c);
		cafeteriaRepository.saveOccupancyBase(d);
		//System.out.println("in service post");
		//System.out.println(c+"in service post");
		
	}

	private Cafeteria randomcall1() {
		Integer k=0;
		String find;
		//Color Color;
		String Color;
		k=RandomUtil.RandomInt(1,150);
		
		find=CapacityUtilBase.FindTypeDining(k);
		Color=CapacityUtilBase.FindColorDining(k);
		Cafeteria cafe=new Cafeteria();
		
		cafe.setSpacetype(RandomUtil.RandomDining());
		cafe.setPeoplecount(k);
		cafe.setInserted_datetime(RandomUtil.NewDate());
		cafe.setCreated_date(RandomUtil.NewDate());
		cafe.setCreated_by(RandomUtil.admin());
		cafe.setModified_date(RandomUtil.NewDate());
		cafe.setModified_by(RandomUtil.admin());
		cafe.setColor(Color);
		cafe.setType(find);
		//System.out.println(cafe);
		return cafe;
		
	}
	
	private Cafeteria randomcall2() {
		Integer k=0;
		String find;
		String  Color;
		//Color Color;
		k=RandomUtil.RandomInt(1,50);
		
		find=CapacityUtilBase.FindTypeService(k);
		Color=CapacityUtilBase.FindColorSevice(k);
		
		Cafeteria cafe1 = new Cafeteria();
		cafe1.setSpacetype(RandomUtil.RandomService());
		cafe1.setPeoplecount(k);
		cafe1.setInserted_datetime(RandomUtil.NewDate());
		cafe1.setCreated_date(RandomUtil.NewDate());
		cafe1.setCreated_by(RandomUtil.admin());
		cafe1.setModified_date(RandomUtil.NewDate());
		cafe1.setModified_by(RandomUtil.admin());
		cafe1.setColor(Color);
		cafe1.setType(find);
		//System.out.println(cafe1);
		return cafe1;
		
	}
	
	public void saveOccupancyIntermediate() throws ParseException {
		CafeOccupancyIntermediate ci = random1();
		CafeOccupancyIntermediate cd = random2();
		cafeteriaRepository.saveOccupancyIntermediate(ci);
		cafeteriaRepository.saveOccupancyIntermediate(cd);
		System.out.println("service inter");
	}

	public CafeOccupancyIntermediate random1() throws ParseException{
		
		CafeOccupancyIntermediate cafeInter = new CafeOccupancyIntermediate();
		//cafeInter.setID(RandomUtilIntermediate.Id());
		cafeInter.setSpacetype(RandomUtil.RandomDining());
		System.out.println("service inter1");
		//cafeInter.setPeoplecount(RandomUtil.RandomInt(1, 50));
		cafeInter.setPeoplecount(cafeteriaRepository.getFromBaseDining());
		cafeInter.setTimeslot(RandomUtilIntermediate.timeslot());
		cafeInter.setInserted_date(RandomUtilIntermediate.NewDate());
		cafeInter.setCreated_date(RandomUtil.NewDate());
		cafeInter.setCreated_by(RandomUtil.admin());
		cafeInter.setModified_date(RandomUtil.NewDate());
		cafeInter.setModified_by(RandomUtil.admin());
		return cafeInter;
	}
	
public CafeOccupancyIntermediate random2() throws ParseException{
		
		CafeOccupancyIntermediate cafeInter = new CafeOccupancyIntermediate();
		//cafeInter.setID(RandomUtilIntermediate.Id());
		cafeInter.setSpacetype(RandomUtil.RandomService());
		System.out.println("service inter2");
		//cafeInter.setPeoplecount(RandomUtil.RandomInt(1, 50));
		cafeInter.setPeoplecount(cafeteriaRepository.getFromBaseService());
		cafeInter.setTimeslot(RandomUtilIntermediate.timeslot());
		cafeInter.setInserted_date(RandomUtilIntermediate.NewDate());
		cafeInter.setCreated_date(RandomUtil.NewDate());
		cafeInter.setCreated_by(RandomUtil.admin());
		cafeInter.setModified_date(RandomUtil.NewDate());
		cafeInter.setModified_by(RandomUtil.admin());
		return cafeInter;
	}
public void saveOccupancyHistory() throws ParseException {
	// TODO Auto-generated method stub
	CafeHistory ci = random31();
	CafeHistory cf = random4();
	cafeteriaRepository.saveOccupancyHistory(ci);
	cafeteriaRepository.saveOccupancyHistory(cf);
	
	System.out.println("service inter");
	
}
public CafeHistory random31() throws ParseException{
	
	CafeHistory cafeInter = new CafeHistory();
	//cafeInter.setInserted_datetime(RandomUtilIntermediate.NewDate());
	cafeInter.setPeoplecount(cafeteriaRepository.getFromIntermediateDining());
	cafeInter.setInserted_date(cafeteriaRepository.getFromIntermediateinsertedTime());
	cafeInter.setSpacetype(RandomUtil.RandomDining());
	return cafeInter;
}
public CafeHistory random4() throws ParseException{
	
	CafeHistory cafeInter = new CafeHistory();
	cafeInter.setPeoplecount(cafeteriaRepository.getFromIntermediateService());
	cafeInter.setInserted_date(cafeteriaRepository.getFromIntermediateinsertedTime());
	cafeInter.setSpacetype(RandomUtil.RandomService());
	return cafeInter;
}

public void saveCapacity() {
	Capacity cp = random1capacity();
	Capacity cp1 = random2capacity();
	cafeteriaRepository.saveCapacity(cp);
	cafeteriaRepository.saveCapacity(cp1);
	
}

private Capacity random1capacity() {
	
	Capacity cp = new Capacity();
	Integer k=0;
	String find;
	String Color;
	
	k=cafeteriaRepository.getDiningCapacityFromBase();
	System.out.println(k);
	find=CapacityUtilBase.FindTypeDining(k);
	Color=CapacityUtilBase.FindColorDining(k);
	
	cp.setSpacetype(RandomUtil.RandomDining());
	cp.setPeoplecount(k);
	cp.setType(find);
	cp.setColor(Color);
	//cp.setCurrent_Time(RandomUtil.NewDate());
	return cp;
}

private Capacity random2capacity() {
	Capacity cp1 = new Capacity();
	Integer k=0;
	String find;
	String Color;
	
	k=cafeteriaRepository.getServiceCapacityFromBase();
	System.out.println(k);
	find=CapacityUtilBase.FindTypeService(k);
	Color=CapacityUtilBase.FindColorSevice(k);
	
	cp1.setSpacetype(RandomUtil.RandomService());
	cp1.setPeoplecount(k);
	cp1.setType(find);
	cp1.setColor(Color);
	//cp1.setCurrent_Time(RandomUtil.NewDate());
	return cp1;
}


	
	/*public List<Cafeteria> getBySpaceType(String spacetype) {
		return cafeteriaRepository.getBySpaceType(spacetype);
	}*/
	
/*	public List<Cafeteria> getCount(String spacetype){
		return cafeteriaRepository.getCount(spacetype);
	}*/
	

	/*public List<Cafeteria> getTimeForDate(Date datetime) {
		
		return cafeteriaRepository.getTimeForDate(datetime);
	}*/

}
