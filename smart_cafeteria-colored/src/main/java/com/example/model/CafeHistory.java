package com.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "newhistory")
@Component
public class CafeHistory implements Serializable{
	public CafeHistory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CafeHistory(long id, Date inserted_date, double peoplecount) {
		super();
		this.id = id;
		this.inserted_date = inserted_date;
		this.peoplecount = peoplecount;
	}


	@Override
	public String toString() {
		return "CafeHistory [id=" + id + ", inserted_date=" + inserted_date + ", peoplecount=" + peoplecount
				+ "]";
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getInserted_date() {
		return inserted_date;
	}


	public void setInserted_date(Date inserted_date) {
		this.inserted_date = inserted_date;
	}


	public double getPeoplecount() {
		return peoplecount;
	}


	public void setPeoplecount(double peoplecount) {
		this.peoplecount = peoplecount;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getSpacetype() {
		return spacetype;
	}


	public void setSpacetype(String spacetype) {
		this.spacetype = spacetype;
	}


	private static final long serialVersionUID = 1L;
	@TableGenerator(initialValue=1, table = "cafeteria", name = "id")
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	private long id;

	@Column
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date inserted_date;
	

	@Column
	private double peoplecount;
	@Column
	private String spacetype;
	

}
