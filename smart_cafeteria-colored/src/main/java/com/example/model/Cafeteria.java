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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Base")
@Component
public class Cafeteria implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@TableGenerator(initialValue=1, table = "cafeteria", name = "id")
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	private long id;
	
	@Column
	private String spacetype;
	
	@Column
	private Integer peoplecount;
	
	@Column
	private String Type;
	
	@Column
	private String Color;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date inserted_datetime;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpacetype() {
		return spacetype;
	}

	public void setSpacetype(String spacetype) {
		this.spacetype = spacetype;
	}

	public Integer getPeoplecount() {
		return peoplecount;
	}

	public void setPeoplecount(Integer peoplecount) {
		this.peoplecount = peoplecount;
	}

	public Date getInserted_datetime() {
		return inserted_datetime;
	}

	public void setInserted_datetime(Date inserted_datetime) {
		this.inserted_datetime = inserted_datetime;
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

//	public Cafeteria(long id, String spacetype, Integer peoplecount, Date inserted_datetime, Date created_date,
//			String created_by, Date modified_date, String modified_by) {
//		super();
//		this.id = id;
//		this.spacetype = spacetype;
//		this.peoplecount = peoplecount;
//		this.inserted_datetime = inserted_datetime;
//		this.created_date = created_date;
//		this.created_by = created_by;
//		this.modified_date = modified_date;
//		this.modified_by = modified_by;
//	}

	public Cafeteria() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cafeteria [id=" + id + ", spacetype=" + spacetype + ", peoplecount=" + peoplecount + ", Type=" + Type
				+ ", Color=" + Color + ", inserted_datetime=" + inserted_datetime + ", created_date=" + created_date
				+ ", created_by=" + created_by + ", modified_date=" + modified_date + ", modified_by=" + modified_by
				+ "]";
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public Cafeteria(long id, String spacetype, Integer peoplecount, String type, String color, Date inserted_datetime,
			Date created_date, String created_by, Date modified_date, String modified_by) {
		super();
		this.id = id;
		this.spacetype = spacetype;
		this.peoplecount = peoplecount;
		Type = type;
		Color = color;
		this.inserted_datetime = inserted_datetime;
		this.created_date = created_date;
		this.created_by = created_by;
		this.modified_date = modified_date;
		this.modified_by = modified_by;
	}
	
	
	
}

	