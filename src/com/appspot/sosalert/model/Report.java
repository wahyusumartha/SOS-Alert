package com.appspot.sosalert.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "report")
@PersistenceCapable
public class Report {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String timestamp;

	@Persistent
	private String name;

	@Persistent
	private String description;

	@Persistent
	private String latitude;

	@Persistent
	private String longitude;

	@Persistent
	private String priority;

	
	public Report(){
		
	}
	
	public Report(Long id, String timestamp, String name, String description,
			String latitude, String longitude, String priority) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.priority = priority;
	}

	@XmlElement(name = "id")
	public Long getId() {
		return id;
	}

	@XmlElement(name = "timestamp")
	public String getTimeStamp() {
		return timestamp;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	@XmlElement(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	@XmlElement(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	@XmlElement(name = "priority")
	public String getPriority() {
		return priority;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTimeStamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}
