package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import studnetManager.studentDao;
import util.CallBack;

public class DeleteFrame {
	
	
	studentDao studentDao = new studentDao();
	JFrame jFrame;
	CallBack cb;
	
	public DeleteFrame(CallBack cb) {
		this.cb = cb; 
	}

	
	public void init(final int id) {
		jFrame = new JFrame();
		jFrame.setTitle("是否删除学生");
		jFrame.setSize(300, 200);
		jFrame.setLocation(550, 300);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel paneln4 = (JPanel) jFrame.getContentPane();
		paneln4.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		JButton confirmButton = new JButton("是");
		JButton denybButton = new JButton("否");
		confirmButton.setPreferredSize(new Dimension(60, 30));
		denybButton.setPreferredSize(new Dimension(60, 30));
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				studentDao.delete(id);
				cb.callback();
				jFrame.dispose();
			}
		});
		
		denybButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cb.callback();
				jFrame.dispose();
			}
		});
		paneln4.add(confirmButton);
		paneln4.add(denybButton);
//		cmPanel.add(paneln1);
//		cmPanel.add(paneln2);
//		cmPanel.add(paneln3);
//		cmPanel.add(paneln4);
		jFrame.setVisible(true);
	}

}
