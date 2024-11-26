package com.example.demo.books.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.books.vo.BooksVO;

@Repository
public class BooksDAO {
	  public final JdbcTemplate jdbcTemplate;


	    public BooksDAO(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }

	public List<BooksVO> bookList(){
		String sql = "select * from books";
		return jdbcTemplate.query(sql, new BooksRowMapper());
				
	};
	
	private static class BooksRowMapper implements RowMapper<BooksVO> {
	    @Override
	        public BooksVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	            return new BooksVO(
	                rs.getInt("bookID"),
	                rs.getString("title"),
	                rs.getString("author"),
	                rs.getString("publisher"),
	                rs.getString("publishYear"),
	                rs.getString("genre"),
	                rs.getInt("pages")
	            );
	        }
	    }
}
