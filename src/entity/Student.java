package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {

	private int id;
	private String name;
	private String sex;
	private int age;
	private String classname;

	public Student() {
		super();
	}

	public Student(int i, String n, int a, String s) {
		id = i;
		name = n;
		sex = s;
		age = a;
	}

	public Student(String name, String sex, int age, String classname) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.classname = classname;
	}

	public Student(int id, String name, String sex, int age, String classname) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.classname = classname;
	}

	public Student(String n, int a, String s) {
		name = n;
		sex = s;
		age = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassid() {
		return classname;
	}

	public void setClassid(String classid) {
		this.classname = classid;
	}

}
