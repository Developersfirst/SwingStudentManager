package mysqlFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import studentUtil.StudentFunction;

import entity.Student;

public class mysqlUtil {

	StudentFunction stuFunction = new StudentFunction();
	
	//添加学生
	public int add(Student student,int classid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		connection = stuFunction.getConnection();
		String sql = "insert into student(name,age,sex,class_id) values(?,?,?,?);";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getSex());
			preparedStatement.setInt(4, classid);
			result = preparedStatement.executeUpdate();
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
		return result;
	}
	
	
	//查找全部
	public List<Student> select() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = stuFunction.getConnection();
		String sql = "SELECT student.id,student.name,sex,age,class.name as '班级'FROM student INNER JOIN class ON class_id=class.id;";
		List<Student> list = new ArrayList<Student>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String sex = resultSet.getString("sex");
				String classid = resultSet.getString("班级");
				list.add(new Student(id,name,sex,age,classid));		
			}
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
		return list;
	}
	
	
	//删除学生
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = stuFunction.getConnection();
		String sql = "delete from student where id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
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
	
	
	//修改学生
	public void update(Student student,int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = stuFunction.getConnection();
		String sql = "update student set name=?,sex=?,age=?,class_id=? where id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSex());
			preparedStatement.setInt(3, student.getAge());
			if (student.getClassid().equals("java1701")) {
				preparedStatement.setInt(4, 1);			
			}
			if (student.getClassid().equals("java1703")) {
				preparedStatement.setInt(4, 2);			
			}
			if (student.getClassid().equals("ui1701")) {
				preparedStatement.setInt(4, 3);			
			}
			if (student.getClassid().equals("ui1703")) {
				preparedStatement.setInt(4, 4);			
			}
			preparedStatement.setInt(5, id);
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
	
	
	//根据ID查找学生
	public Student FindById(int id) {
		Student student = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = stuFunction.getConnection();
		String sql = "SELECT student.name,sex,age,class.name as '班级'FROM student INNER JOIN class ON class_id=class.id where student.id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String sex = resultSet.getString("sex");
				String classname = resultSet.getString("班级");
				student = new Student(name,sex,age,classname);
			}
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
		return student;
	}
	
	
	//根据各条件查找学生
	public List<Student> findList(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = stuFunction.getConnection();
		//此处多条sql语句的连接！！！
		String sql = "SELECT student.id,student.name,sex,age,class.name as'班级' from student INNER JOIN class ON class_id=class.id";
		if (!student.getName().equals("")) {
			sql+=" WHERE student.name='"+student.getName()+"'";
		} 
		if (!student.getSex().equals("")) {
			sql+=" and sex='"+student.getSex()+"'";
		} 
		if (student.getAge() > 0) {
			sql+=" and age='"+student.getAge()+"'";
		}
		if (!student.getClassid().equals("")) {
			if (student.getClassid().equals("java1701")) {
				sql+=" and class_id=1;";	
			}
			if (student.getClassid().equals("java1703")) {
				sql+=" and class_id=2;";	
			}
			if (student.getClassid().equals("ui1701")) {
				sql+=" and class_id=3;";	
			}
			if (student.getClassid().equals("ui1703")) {
				sql+=" and class_id=4;";	
			}
		}
		List<Student> list = new ArrayList<Student>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String sex = resultSet.getString("sex");
				String classid = resultSet.getString("班级");
				list.add(new Student(id,name,sex,age,classid));		
			}
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
		return list;
	}
}
