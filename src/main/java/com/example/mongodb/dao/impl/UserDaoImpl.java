package com.example.mongodb.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.example.mongodb.dao.UserDao;
import com.example.mongodb.entity.UserEntity;
import com.mongodb.client.result.UpdateResult;

@Component
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveUser(UserEntity user) {		
		mongoTemplate.save(user);
	}
	
	@Override
	public UserEntity findUserById(String objectId) {
		Query query = new Query(Criteria.where("_id").is(objectId));
		UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
		return user;
	}

	@Override
	public UserEntity findUserByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
		return user;
	}

	@Override
	public int updateUser(UserEntity user) {
		Query query = new Query(Criteria.where("_id").is(user.getObjectId()));
		Update update = new Update().set("name",user.getName()).set("age", user.getAge()).set("work", user.getWork());
		//更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,UserEntity.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null) {
        	Long i = result.getMatchedCount();         	
            return i.intValue();
        }
        else {
            return 0;
        }		
	}

	@Override
	public void deleteUserById(String objectId) {		
		Query query = new Query(Criteria.where("_id").is(objectId));
		mongoTemplate.remove(query, UserEntity.class);
	}
}
