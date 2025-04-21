package view.subView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.entity.employee;
import utils.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class newCustomerAddingFr extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField customer;
	private JTextField phone;
	private JTextField address;
	public JComboBox<String> staff;
	private String staffInfo;

	public newCustomerAddingFr(newReceiptAddingFr raf, String staffInfo) {
		this.staffInfo = staffInfo;
		this.setTitle("newCustomerAddingFr");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("THÊM MỚI KHÁCH HÀNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_1 = new JLabel("Tên khách:");

		JLabel lblNewLabel_2 = new JLabel("Sđt:");

		JLabel lblNewLabel_3 = new JLabel("Địa chỉ:");

		customer = new JTextField();
		customer.setColumns(10);

		phone = new JTextField();
		phone.setColumns(10);

		address = new JTextField();
		address.setColumns(10);
		JButton btnNewButton = new JButton("Lưu khách hàng");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customer.getText() != "") {
					WritableWorkbook writableWorkbook = null;
					try {
						WorkbookSettings encode = new jxl.WorkbookSettings();
						encode.setEncoding("ISO-8859-1");
						Workbook workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);
						writableWorkbook = Workbook.createWorkbook(new File("database/database.xls"), workbook);
						WritableSheet sheet = writableWorkbook.getSheet("khachhang");
						int rows_in_kh = sheet.getRows();
						sheet.addCell(new Label(0, rows_in_kh, customer.getText()));
						sheet.addCell(new Label(1, rows_in_kh, phone.getText()));
						sheet.addCell(new Label(2, rows_in_kh, address.getText()));
						String staffInfo = (String) staff.getSelectedItem();
						sheet.addCell(new Label(3, rows_in_kh, (staffInfo.split(" - ")[1])));
						writableWorkbook.write();
						writableWorkbook.close();
						raf.customerPN.setText(phone.getText());
						raf.customerName.setText(customer.getText());
						setVisible(false);
						JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");

					} catch (Exception e1) {
						e1.printStackTrace();
						try {
							writableWorkbook.close();
						} catch (WriteException e2) {
							e2.printStackTrace();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					}
				} else {
					raf.customerPN.setText("");
				}
			}
		});

		JLabel lblNewLabel_4 = new JLabel("Nhân viên phụ trách");

		staff = new JComboBox<String>();
		for (employee employee: new dataLoader().getAllEmployees()) {
			this.staff.addItem(employee.getName() + " - " + employee.getPhoneNumber());
		}
		
		this.staff.setSelectedItem(this.staffInfo);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (customer.getText().equals("")) {
					customer.setForeground(new Color(0, 0, 0));
					customer.setText("");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
												.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 130,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(staff, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.LEADING,
												gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE,
																		80, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE,
																		50, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE,
																		60, GroupLayout.PREFERRED_SIZE))
														.addGap(34)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(address, GroupLayout.PREFERRED_SIZE, 150,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(phone, GroupLayout.PREFERRED_SIZE, 150,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(customer, GroupLayout.PREFERRED_SIZE, 150,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnNewButton, Alignment.TRAILING,
																		GroupLayout.PREFERRED_SIZE, 150,
																		GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(328, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(
						customer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(28)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2).addComponent(
						phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(29)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_3).addComponent(
						address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4).addComponent(
						staff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(20).addComponent(btnNewButton).addContainerGap(63, Short.MAX_VALUE)));

		panel.setLayout(gl_panel);

	}
}
