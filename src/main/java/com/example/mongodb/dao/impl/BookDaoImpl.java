package com.example.mongodb.dao.impl;

import org.springframework.stereotype.Component;

import com.example.mongodb.dao.BookDao;
import com.example.mongodb.entity.BookEntity;

@Component
public class BookDaoImpl extends BaseMongoDaoImpl<BookEntity> implements BookDao {
	
}
