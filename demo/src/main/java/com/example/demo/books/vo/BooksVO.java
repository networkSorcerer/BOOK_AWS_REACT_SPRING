package com.example.demo.books.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksVO {
	public BooksVO(int int1, String string, String string2, String string3, String string4, String string5, int int2) {
		// TODO Auto-generated constructor stub
	}
	private int bookID;
	private String  title;
	private String author;
	private String publisher;
	private int publishYear;
	private String genre;
	private int pages;
	
	
}
