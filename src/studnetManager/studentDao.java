package studnetManager;

import java.util.List;

import mysqlFunction.mysqlUtil;

import entity.Student;

import studentUtil.StudentFunction;

public class studentDao {
	
	mysqlUtil sqlUtil = new mysqlUtil();
	static StudentFunction stuFunction = new StudentFunction();

	public boolean add(Student student,int classnum) {
		int a = sqlUtil.add(student,classnum);
		if (a > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Student> select() {
		return sqlUtil.select();
	}
	
	public void delete(int id) {
		sqlUtil.delete(id);
	}
	
	public void update(Student student,int id) {
		sqlUtil.update(student,id);
	}
	
	public Student findById(int id) {
		return sqlUtil.FindById(id);
	}
	
	public List<Student> findList(Student student) {
		return sqlUtil.findList(student);
	}
}
