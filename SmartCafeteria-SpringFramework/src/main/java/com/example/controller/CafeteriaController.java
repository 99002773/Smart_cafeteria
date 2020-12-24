package com.example.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.CafeteriaRepository;
import com.example.model.CafeOccupancyIntermediate;
import com.example.model.Cafeteria;
import com.example.service.CafeteriaService;

@RestController
public class CafeteriaController {

	
	@Autowired
	private CafeteriaService cafeteriaService;
	
	@Autowired
	private CafeteriaRepository repos;
	
	
	
	public void setUserService(CafeteriaService cafeteriaService) {
		this.cafeteriaService = cafeteriaService;
	}
	
//	@GetMapping("/save")
//	public Cafeteria save(){
//		Cafeteria user =new Cafeteria();
//		user.setId(101);
//		return cafeteriaService.save(user);
//	}
	
	
	@PostMapping("/save")
	public Cafeteria save(@RequestBody Cafeteria user){
	
		return cafeteriaService.save(user);
	}
	
	@GetMapping("/all")
	public List<Cafeteria> getAllUsers() {
		return cafeteriaService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public Cafeteria getDetails(@PathVariable(value="id")long id) {
		return cafeteriaService.getDetails(id);
	}
	
	
	
	
//	
//	@GetMapping("/datetimeDining/{dt}")
//	public List<Cafeteria> getDiffTimeCountByDiningArea(@PathVariable(value="dt")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date dt) throws ParseException {
//		return repos.getDiffTimeCountByDiningArea(dt);
//	}
	
	@CrossOrigin
	@GetMapping("/api/Live-occupancy-data")
	public String getDiffTimeCountByServiceArea() throws ParseException
			 {
		return repos.getDiffTimeCountByServiceArea().toString();
	}
	@CrossOrigin
	@GetMapping("/api/Live-occupancy-trend")
	public String TrendGraph() throws ParseException
			 {
		return repos.TrendGraph().toString();
	}
	@CrossOrigin
	@GetMapping("/api/Historical-Occupancy-trend")
    public String getAllByDatetimeBetween(
        @RequestParam("startdate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startdate,
        @RequestParam("enddate") @DateTimeFormat(pattern="yyyy-MM-dd") Date enddate) {
		Calendar c = Calendar.getInstance();
	     c.setTime(enddate);
	     c.add(Calendar.DATE, 1);
	     enddate = c.getTime();
      return repos.findAllByDatetimeBetween(startdate, enddate).toString();
    }
	
	
	
	
	@GetMapping("/findPeopleCountForDateRange")
    public List<Cafeteria> getfindPeopleCountForDateRange(
        @RequestParam("startdate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startdate,
        @RequestParam("enddate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date enddate) {
      return repos.findPeopleCountForDateRange(startdate, enddate);
    }
	
	/*
	@GetMapping("/peoplecount/{spacetype}")
	public List<Cafeteria> getCount(@PathVariable(value="spacetype")String spacetype) {
		return cafeteriaService.getCount(spacetype);
	}*/
	
//	@GetMapping("/getCountbyService/{currentDate}")
//    public List<Cafeteria> getCountByService(
//    		@PathVariable("currentDate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date datetime){
//    		return repos.getCountByServiceArea(datetime);
//        
//    }
//	@GetMapping("/getCountbyDining/{currentDate}")
//    public String getCountByDining(
//    		@PathVariable("currentDate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date datetime){
//		JSONArray responseArray = new JSONArray();	
//		responseArray = repos.getCountByDiningArea(datetime);
//		//System.out.println("jjjjjjjjjj"+responseArray);
//		return responseArray.toString();
        
    //}
	
//	@GetMapping("/p")
//	public String post(){
//		cafeteriaService.post();
//		return "successcontroller";
//		
//		
//	}
	
	/*@GetMapping("/time/{time}")
	public List<Cafeteria> getTimeForDate(@PathVariable(value="datetime")Date spacetype) {
		return cafeteriaService.getTimeForDate(spacetype);
	}*/
	
	
	
	
}
