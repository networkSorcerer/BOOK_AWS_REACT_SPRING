package com.example.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.books.dao.BooksDAO;

import lombok.Data;

@Data
@SpringBootApplication
public class BooksApplication implements CommandLineRunner{
	private final BooksDAO booksDAO;
	
	public BooksApplication(BooksDAO booksDAO) {
		this.booksDAO = booksDAO;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class,args);

	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
