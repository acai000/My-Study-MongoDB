package com.example.mongodb.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userEntity")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6673352880808207456L;
	
	@Id
	private String objectId;
	private String name;
	private String age;
	private String work;
		
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	@Override
	public String toString() {
		return "UserEntity [objectId=" + objectId + ", name=" + name + ", age=" + age + ", work=" + work + "]";
	}
	
	

}
