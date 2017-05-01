package createTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import studentUtil.StudentFunction;


public class mysqlTest {

	static StudentFunction studentDao = new StudentFunction();

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = studentDao.getConnection();
		String sql = "CREATE TABLE student1(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(10),age int,sex varchar(10));";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
