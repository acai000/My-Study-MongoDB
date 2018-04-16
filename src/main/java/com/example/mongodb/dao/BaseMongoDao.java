package com.example.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.mongodb.core.Page;
import com.mongodb.client.result.UpdateResult;

public interface BaseMongoDao<T> {
	/**
	 * 插入
	 * @param t
	 * @return
	 */
	public T save(T t);
	/**
	 * 批量插入
	 */
	public void save(List<T> t);
	/**
	 * 根据ID查询
	 * @return
	 */
	public T findById(String id);
	/**
	 * 根据ID获取记录，并且指定了集合名
	 * @param id
	 * @param collectionName（集合名）
	 * @return
	 */
	public T findById(String id, String collectionName);
	/**
	 * 获得所有该类型记录
	 * @return
	 */
	public List<T> findAll();
	/**
	 * 获得所有该类型记录，并且指定了集合名
	 * @param collectionName
	 * @return
	 */
	public List<T> findAll(String collectionName);
	/**
	 * 根据条件查询
	 * @param query
	 * @return
	 */
	public List<T> find(Query query);
	/**
	 * 根据条件查询一个
	 * @param query
	 * @return
	 */
	public T findOne(Query query);
	/**
	 * 分页查询
	 * @param page
	 * @param query
	 * @return
	 */
	public Page<T> findPage(Page<T> page, Query query);
	/**
	 * 根据条件获得总数
	 * @param query
	 * @return
	 */
	public long count(Query query);
	/**
	 * 根据条件更新
	 * @param query
	 * @param update
	 * @return
	 */
	public UpdateResult update(Query query, Update update);
	/**
	 * 更新符合条件并sort之后的第一个文档 并返回更新后的文档
	 * @param query
	 * @param update
	 * @return
	 */
	public T updateOne(Query query,Update update);
	/**   
     * 根据传入实体ID更新   
     */    
    public UpdateResult update(T t);    
    
    /**   
     * 根据条件 删除   
     *    
     * @param query   
     */    
    public void remove(Query query); 

}
