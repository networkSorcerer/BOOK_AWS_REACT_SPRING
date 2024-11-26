package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
	  public static void main(String[] args) {
	        String jdbcUrl = "jdbc:mysql://localhost:3306/Board";
	        String username = "admin";
	        String password = "agape99^^";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
	            System.out.println("MySQL 연결 성공!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
