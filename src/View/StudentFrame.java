package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import studnetManager.studentDao;
import util.CallBack;
import entity.Student;
import model.StudentTableModel;

public class StudentFrame {

	studentDao studentDao = new studentDao();
	List<Student> list = new ArrayList<Student>();
	StudentTableModel stuModel;
	JTextField nameText;
	JTextField sexText;
	JTextField ageText;
	JTextField classText;
	int tablerow = 0;
	int id = 0;
	JTable table;
	JScrollPane scrollPane;

	public void init() {
		final JFrame frame = new JFrame();
		frame.setTitle("学生管理系统");
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = (JPanel) frame.getContentPane();
		// 设置主布局为从上到下。
		BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(layout);
		JPanel panel1 = new JPanel();
		// 设置上层panel1的内容布局
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		// 姓名
		JLabel nameLabel = new JLabel("姓名");
		panel1.add(nameLabel);
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(80, 30));
		panel1.add(nameText);
		// 性别
		JLabel sexLabel = new JLabel("性别");
		panel1.add(sexLabel);
		sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(80, 30));
		panel1.add(sexText);
		// 年龄
		JLabel ageLabel = new JLabel("年龄");
		panel1.add(ageLabel);
		ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(80, 30));
		panel1.add(ageText);
		//班级
		JLabel classLabel = new JLabel("班级");
		panel1.add(classLabel);
		classText = new JTextField();
		classText.setPreferredSize(new Dimension(80, 30));
		panel1.add(classText);
		// 查找按钮
		JButton searchButton = new JButton("查找");
		searchButton.setPreferredSize(new Dimension(80, 30));
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			String name = nameText.getText();
			String sex = sexText.getText();
			String classid = classText.getText();
			int age = -1;
			//注意此处对age的判断！！！
			if(!ageText.getText().equals("")){
				age = Integer.parseInt(ageText.getText());
			}
			Student student = new Student();
			student.setName(name);
			student.setSex(sex);
			student.setAge(age);
			student.setClassid(classid);
			list = studentDao.findList(student);
			stuModel.setData(list);
			}
		});
		panel1.add(searchButton);
		// 设置中部panel2的内容
		JPanel panel2 = new JPanel();
		// 此处data使用的数据是dao层里面存储的数据，但是不能直接跨层访问，只
		// 能访问业务逻辑层，所以需要在biz中建立一个中转的方法。
		// new一个新的滚动条
		// 读取数据

		// 把更新后的数据放在新的table中
		list = studentDao.select();
		stuModel = new StudentTableModel(list);
		table = new JTable(stuModel);
		// 把table放在新的滚动条中以实现实时更新
		scrollPane = new JScrollPane(table);
		// 设置table大小
		scrollPane.setPreferredSize(new Dimension(600, 300));
		// 添加更新后的新的滚动条
		panel2.add(scrollPane);
		// 设置下部panel内容
		JPanel panel3 = new JPanel();
		JButton addButton = new JButton("添加");
		addButton.setPreferredSize(new Dimension(80, 30));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddFrame addFrame = new AddFrame(new CallBack() {

					@Override
					public void callback() {
						refresh();
					}
				});
				addFrame.init();
			}
		});
		JButton deleteButton = new JButton("删除");
		deleteButton.setPreferredSize(new Dimension(80, 30));
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index > -1) {
					int id = Integer.parseInt(stuModel.getValueAt(index, 0)
							.toString());
					DeleteFrame deleteFrame = new DeleteFrame(new CallBack() {
						@Override
						public void callback() {
							refresh();
						}
					});
					deleteFrame.init(id);
				} else {
					JOptionPane.showMessageDialog(null, "请选中要删除的学生");
				}
			}
		});
		JButton motifyButton = new JButton("修改");
		motifyButton.setPreferredSize(new Dimension(80, 30));
		motifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index > -1) {
					int id = Integer.parseInt(stuModel.getValueAt(index, 0)
							.toString());
					UpdateFrame updateFrame = new UpdateFrame(new CallBack() {

						@Override
						public void callback() {
							refresh();
						}
					}, id);
					updateFrame.init(id);
				} else {
					JOptionPane.showMessageDialog(null, "请选中要修改的学生");
				}
			}
		});
		panel3.add(addButton);
		panel3.add(deleteButton);
		panel3.add(motifyButton);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		frame.setVisible(true);

	}

	public void refresh() {
		list = studentDao.select();
		stuModel.setData(list);
	}

	public static void main(String[] args) {
		StudentFrame frame = new StudentFrame();
		frame.init();
	}
}
