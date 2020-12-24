package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "capacity")
@Component
public class Capacity {
	
	@Id
	private long id;
	
	@Column
	private String Spacetype;
	
	@Column
	private int peoplecount;
	
	@Column
	private String Type;
	
	@Column
	private String Color;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpacetype() {
		return Spacetype;
	}

	public void setSpacetype(String spacetype) {
		Spacetype = spacetype;
	}

	public int getPeoplecount() {
		return peoplecount;
	}

	public void setPeoplecount(int peoplecount) {
		this.peoplecount = peoplecount;
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

	public Capacity(long id, String spacetype, int peoplecount, String type, String color) {
		super();
		this.id = id;
		Spacetype = spacetype;
		this.peoplecount = peoplecount;
		Type = type;
		Color = color;
	}

	public Capacity() {
		super();
		
	}

	@Override
	public String toString() {
		return "Capacity [id=" + id + ", Spacetype=" + Spacetype + ", peoplecount=" + peoplecount + ", Type=" + Type
				+ ", Color=" + Color + "]";
	}

	
	

}
