package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import studnetManager.studentDao;
import util.CallBack;

import entity.Student;

public class UpdateFrame {
	

	studentDao studentDao = new studentDao();
	JFrame jFrame;
	CallBack cb;
	int id;
	public UpdateFrame(CallBack cb,int id) {
		this.cb = cb; 
		this.id = id;
	}
	
	public void init(final int id) {
		Student student = null;
		student = studentDao.findById(id);
		jFrame = new JFrame();
		jFrame.setTitle("修改学生");
		jFrame.setSize(300, 300);
		jFrame.setLocation(550, 250);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel cmPanel = (JPanel) jFrame.getContentPane();
		BoxLayout bLayout = new BoxLayout(cmPanel, BoxLayout.Y_AXIS);
		cmPanel.setLayout(bLayout);
		// panel1
		JPanel paneln1 = new JPanel();
		paneln1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel naJLabel = new JLabel("姓名");
		paneln1.add(naJLabel);
		final JTextField nmTextField = new JTextField();
		nmTextField.setText(student.getName());
		nmTextField.setPreferredSize(new Dimension(80, 20));
		paneln1.add(nmTextField);

		JPanel paneln2 = new JPanel();
		paneln2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel seJLabel = new JLabel("性别");
		paneln2.add(seJLabel);
		final JTextField seTextField = new JTextField();
		seTextField.setText(student.getSex());
		seTextField.setPreferredSize(new Dimension(80, 20));
		paneln2.add(seTextField);

		JPanel paneln3 = new JPanel();
		paneln3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel aJLabel = new JLabel("年龄");
		paneln3.add(aJLabel);
		final JTextField aTextField = new JTextField();
		aTextField.setText(String.valueOf(student.getAge()));
		aTextField.setPreferredSize(new Dimension(80, 20));
		paneln3.add(aTextField);
		
		JPanel paneln4 = new JPanel();
		paneln4.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel cJLabel = new JLabel("班级");
		paneln4.add(cJLabel);
		final JTextField cTextField = new JTextField();
		cTextField.setText(String.valueOf(student.getClassid()));
		cTextField.setPreferredSize(new Dimension(80, 20));
		paneln4.add(cTextField);
		
		JPanel paneln5 = new JPanel();
		paneln5.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton saveButton = new JButton("保存");
		saveButton.setPreferredSize(new Dimension(60, 30));
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 此处使用.getText();
				String name = nmTextField.getText();
				String sex = seTextField.getText();
				int age = Integer.parseInt(aTextField.getText());
				String classid = cTextField.getText();
				Student student = new Student(name,sex,age,classid);
				studentDao.update(student,id);
				cb.callback();
				jFrame.dispose();
			}
		});
		paneln5.add(saveButton);
		cmPanel.add(paneln1);
		cmPanel.add(paneln2);
		cmPanel.add(paneln3);
		cmPanel.add(paneln4);
		cmPanel.add(paneln5);
		jFrame.setVisible(true);
	}

}
