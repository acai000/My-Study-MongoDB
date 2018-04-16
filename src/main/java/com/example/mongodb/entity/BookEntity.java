package com.example.mongodb.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="bookEntity")
public class BookEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1800832707417577669L;
	
	@Id
	private String objectId;//主键
	private String bookName;//书名
	private String author;//作者
	private String price;//价格
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookEntity [objectId=" + objectId + ", bookName=" + bookName + ", author=" + author + ", price=" + price
				+ "]";
	}

}
