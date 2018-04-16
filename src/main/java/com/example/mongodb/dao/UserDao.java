package com.example.mongodb.dao;

import com.example.mongodb.entity.UserEntity;

public interface UserDao {
	
	public void saveUser(UserEntity user);
	
	public UserEntity findUserById(String objectId);
	
	public UserEntity findUserByName(String name);
	
	public int updateUser(UserEntity user);
	
	public void deleteUserById(String objectId);
}
