package com.example.demo.books.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.books.dao.BooksDAO;
import com.example.demo.books.vo.BooksVO;
@Controller
@RequestMapping("/books")
public class BooksController {
	private final BooksDAO booksDAO;
	
	public BooksController(BooksDAO booksDAO) {
		this.booksDAO = booksDAO;
	}
	@PostMapping("/list")
	public String bookList(Model model) {
		
		List<BooksVO>bookList = booksDAO.bookList();
		model.addAttribute("bookList",bookList);
		System.out.print(bookList);
		return "books/books";
	}
}
