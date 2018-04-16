package com.example.mongodb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mongodb.dao.BookDao;
import com.example.mongodb.entity.BookEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGeneralDao {
	
	@Autowired
	private BookDao bookDao;
	
	//测试数据获取（all）
	@Test
	public void test1() {		
		List<BookEntity> list = bookDao.findAll();
		if(null != list && list.size()>0) {
			for(BookEntity book : list) {
				System.out.println("书名："+book.getBookName());
				System.out.println("作者："+book.getAuthor());
				System.out.println("价格："+book.getPrice());
				System.out.println("");
			}
		}else {
			System.out.println("没有数据");
		}
	}
	
	//测试数据插入
	@Test
	public void test2() {
		BookEntity book = new BookEntity();
		book.setBookName("平凡的世界");
		book.setAuthor("路遥");
		book.setPrice("40.00");
		bookDao.save(book);
		System.out.println("数据保存输出："+book.toString());
	}
	

}
