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
@Table(name = "Intermediate")
@Component
public class CafeOccupancyIntermediate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private long ID;
	
	@Column
	private String spacetype;
	
	@Column
	private Double peoplecount;
	
	@Column
	private String timeslot;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="HH:mm:ss", timezone = "Asia/Kolkata")
	private Date inserted_date;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
    private Date created_date;
	
	@Column
	private String created_by;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
    private Date modified_date;
	
	@Column
	private String modified_by;

	

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getSpacetype() {
		return spacetype;
	}

	public void setSpacetype(String spacetype) {
		this.spacetype = spacetype;
	}

	public Double getPeoplecount() {
		return peoplecount;
	}

	public void setPeoplecount(Double double1) {
		this.peoplecount = double1;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public Date getInserted_date() {
		return inserted_date;
	}

	public void setInserted_date(Date inserted_date) {
		this.inserted_date = inserted_date;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public CafeOccupancyIntermediate(long id, String spacetype, Double peoplecount, String timeslot,
			Date inserted_date, Date created_date, String created_by, Date modified_date, String modified_by) {
		super();
		this.ID = id;
		this.spacetype = spacetype;
		this.peoplecount = peoplecount;
		this.timeslot = timeslot;
		this.inserted_date = inserted_date;
		this.created_date = created_date;
		this.created_by = created_by;
		this.modified_date = modified_date;
		this.modified_by = modified_by;
	}

	public CafeOccupancyIntermediate() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CafeOccupancyIntermediate [id=" + ID + ", spacetype=" + spacetype + ", peoplecount=" + peoplecount
				+ ", timeslot=" + timeslot + ", inserted_date=" + inserted_date + ", created_date=" + created_date
				+ ", created_by=" + created_by + ", modified_date=" + modified_date + ", modified_by=" + modified_by
				+ "]";
	}
	
	


}
