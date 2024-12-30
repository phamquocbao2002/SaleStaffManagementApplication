package view.subView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class newEmployeeAddingFr extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField employee;
	private JTextField phone;
	public newEmployeeAddingFr() {
		this.setSize(400,300);
		this.setTitle("newEmployeeAddingFr");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("Tên nhân viên:");
		
		employee = new JTextField();
		employee.setColumns(10);
		
		JButton btnNewButton = new JButton("Lưu nhân viên");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WritableWorkbook writableWorkbook = null;
				try {
					WorkbookSettings encode = new jxl.WorkbookSettings();
					encode.setEncoding("ISO-8859-1");
					Workbook workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);
					writableWorkbook = Workbook.createWorkbook(new File("database/database.xls"), workbook);
					WritableSheet sheet = writableWorkbook.getSheet("nhanvien");
					int r = sheet.getRows();
					sheet.addCell(new Label(0, r, employee.getText()));
					sheet.addCell(new Label(1, r, phone.getText()));
					writableWorkbook.write();
					writableWorkbook.close();
					JOptionPane.showMessageDialog(null, "Thêm mới nhân viên thành công");
				} catch (Exception e1) {
					try {
						writableWorkbook.close();
					} catch (WriteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				} 
			}
		});
		
		JLabel lblNewLabel_1_1 = new JLabel("Sđt:");
		
		phone = new JTextField();
		phone.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(150)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(phone, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(employee, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(employee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
						.addComponent(phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(btnNewButton)
					.addContainerGap(108, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		
	}
	public static void main(String[] args) {
		new newEmployeeAddingFr();
	}

}
