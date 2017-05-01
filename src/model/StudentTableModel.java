package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Student;

@SuppressWarnings("serial")
public class StudentTableModel extends AbstractTableModel {
	String[] columnNames = { "学号", "姓名", "性别", "年龄","班级"};

	private List<Student> data;

	public StudentTableModel(List<Student> data) {
		this.data = data;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student stu = data.get(rowIndex);
		if (columnIndex == 0) {
			return stu.getId();
		} else if (columnIndex == 1) {
			return stu.getName();
		} else if (columnIndex == 2) {
			return stu.getSex();
		} else if (columnIndex == 3) {
			return stu.getAge();
		} else if (columnIndex == 4) {
			return stu.getClassid();
		}
		return null;

	}

	public void setData(List<Student> data) {
		if (data == null) {
			throw new IllegalArgumentException("data不能为null");
		}
		this.data = data;

		fireTableDataChanged();
	}
	
	
}