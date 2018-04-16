package com.example.mongodb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mongodb.dao.UserDao;
import com.example.mongodb.entity.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserDao {
	
	@Autowired
	private UserDao userDao;

	@Test
	public void testSave() {
		UserEntity user = new UserEntity();		
		user.setName("zqc");
		user.setAge("25");
		user.setWork("mongodb");
		userDao.saveUser(user);
		System.out.println("id " + user.getObjectId());
	}
	
	@Test
	public void testFind() {
		UserEntity user1 = userDao.findUserByName("zqc");
		UserEntity user = userDao.findUserById(user1.getObjectId());
		if(null != user) {
			System.out.println("user is " + user.toString());
		}else {
			System.out.println("user is null");
		}
		
	}
	
	@Test
	public void testUpdate() {
		UserEntity user1 = userDao.findUserByName("zqc");
		UserEntity user = new UserEntity();
		user.setObjectId(user1.getObjectId());
		user.setName("ZQC");
		user.setAge("26");
		user.setWork("JavaWeb");
		int i = userDao.updateUser(user);
		if(1==i)
			System.out.println("更新成功");
		else
			System.out.println("更新失败");
	}
	
	@Test
	public void testDelete() {
		UserEntity user = userDao.findUserByName("ZQC");
		userDao.deleteUserById(user.getObjectId());
	}

}
