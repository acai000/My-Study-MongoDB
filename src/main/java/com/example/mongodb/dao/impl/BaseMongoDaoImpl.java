package com.example.mongodb.dao.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.example.mongodb.core.Page;
import com.example.mongodb.core.ReflectionUtils;
import com.example.mongodb.dao.BaseMongoDao;
import com.mongodb.client.result.UpdateResult;

@Component
public class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * 获得泛型类
	 * @return
	 */
	private Class<T> getEntityClass() {
		return ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	@Override
	public T save(T t) {
		mongoTemplate.save(t);			
		return t;
	}
	
	@Override
	public void save(List<T> t) {
		mongoTemplate.insert(t, this.getEntityClass());		
	}
	
	@Override
	public T findById(String id) {			
		return mongoTemplate.findById(id, this.getEntityClass());
	}

	@Override
	public T findById(String id, String collectionName) {		
		return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
	}

	@Override
	public List<T> findAll() {		
		return mongoTemplate.findAll(this.getEntityClass());
	}

	@Override
	public List<T> findAll(String collectionName) {		
		return mongoTemplate.findAll(this.getEntityClass(), collectionName);
	}

	@Override
	public List<T> find(Query query) {		
		return mongoTemplate.find(query,this.getEntityClass());
	}

	@Override
	public T findOne(Query query) {
		return mongoTemplate.findOne(query, this.getEntityClass());
	}

	@Override
	public Page<T> findPage(Page<T> page, Query query) {
		//如果没有条件，则查询全部    
        query=query==null?new Query(Criteria.where("_id").exists(true)):query;    
        long count = this.count(query);    
        // 总数    
        page.setTotalCount((int) count);    
        int currentPage = page.getCurrentPage();    
        int pageSize = page.getPageSize();    
        query.skip((currentPage - 1) * pageSize).limit(pageSize);    
        List<T> rows = this.find(query);    
        page.build(rows);    
        return page;		
	}

	@Override
	public long count(Query query) {
		return mongoTemplate.count(query, this.getEntityClass());
	}

	@Override
	public UpdateResult update(Query query, Update update) {
		if(null==update) {
			return null;
		}
		return mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}

	@Override
	public T updateOne(Query query, Update update) {
		if(null==update) {
			return null;
		}
		return mongoTemplate.findAndModify(query, update, this.getEntityClass());
	}

	@Override
	public UpdateResult update(T t) {
		Field[] fields = this.getEntityClass().getDeclaredFields();    
        if (fields == null || fields.length <= 0) {    
            return null;    
        }    
        Field idField = null;    
        // 查找ID的field    
        for (Field field : fields) {    
            if (field.getName() != null    
                    && "id".equals(field.getName().toLowerCase())) {    
                idField = field;    
                break;    
            }    
        }    
        if (idField == null) {    
            return null;    
        }    
        idField.setAccessible(true);    
        String id=null;    
        try {    
            id = (String) idField.get(t);    
        } catch (IllegalArgumentException e) {    
            e.printStackTrace();    
        } catch (IllegalAccessException e) {    
            e.printStackTrace();    
        }    
        if (id == null || "".equals(id.trim()))    
            return null;    
        // 根据ID更新    
        Query query = new Query(Criteria.where("_id").is(id)); 
        Update update = ReflectionUtils.getUpdateObj(t);    
        if (update == null) {    
            return null;    
        }    
        return mongoTemplate.updateFirst(query, update, getEntityClass()); 
	}

	@Override
	public void remove(Query query) {		
		mongoTemplate.remove(query, this.getEntityClass());
	}	

}
