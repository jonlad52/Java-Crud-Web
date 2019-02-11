package com.jonathann.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
	private static BasicDataSource dataSource = null;
	
	private static DataSource getDataSource(){
		if(dataSource==null){
			dataSource= new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("12345678");
			dataSource.setUrl("jdbc:mysql://localhost:3306/crud");
			dataSource.setInitialSize(20);
			dataSource.setMaxIdle(15);
			dataSource.setMaxTotal(20);
			dataSource.setMaxWaitMillis(5000);
		}
		return dataSource;
	}
	
	public static Connection getConexion() throws SQLException{
		return getDataSource().getConnection();
	}

}
