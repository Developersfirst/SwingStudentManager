package studentUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class StudentFunction {
	
	private static final String url;
	private static final String userName;
	private static final String password;
	private static final String classDriver;
	
	
	static {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
		fileInputStream = new FileInputStream("./src/db.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		url = properties.getProperty("url");
		userName = properties.getProperty("userName");
		password = properties.getProperty("password");
		classDriver = properties.getProperty("classDriver");
		
		try {
			Class.forName(classDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
}
