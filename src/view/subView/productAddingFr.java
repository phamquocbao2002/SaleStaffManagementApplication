package view.subView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import utils.layoutSetter;
import view.mainView.homeWindow;

import javax.swing.JTextField;
import javax.swing.JButton;

public class productAddingFr extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField product_unitprice;
	private JTextField productid;
	private JTextField productname;
	public productAddingFr(homeWindow hw) {
		this.setSize(300,350);
		this.setTitle("productDetailFr");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Mã sản phẩm:");
		
		JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm:");
		
		JLabel lblNewLabel_2 = new JLabel("Đơn giá:");
		
		product_unitprice = new JTextField();
		product_unitprice.setColumns(10);
		
		JButton btnNewButton = new JButton("Lưu sản phẩm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WritableWorkbook writableWorkbook = null;
				Workbook workbook = null;
				try {
					WorkbookSettings encode = new jxl.WorkbookSettings();
					encode.setEncoding("ISO-8859-1");
					workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);
					writableWorkbook = Workbook.createWorkbook(new File("database/database.xls"), workbook);
					WritableSheet sheet = writableWorkbook.getSheet("sanpham");
					int slmh = sheet.getRows();
					sheet.addCell(new Label(0,slmh, productid.getText()));
					sheet.addCell(new Label(1,slmh, productname.getText()));
					sheet.addCell(new Label(2,slmh, "Hộp"));
					String up = "";
					for (int idx = 0; idx <product_unitprice.getText().split(",").length; idx++){
						up = up + product_unitprice.getText().split(",")[idx];
					}
					sheet.addCell(new Label(3,slmh, up));
					
					writableWorkbook.write();
					writableWorkbook.close();
					workbook.close();
					setVisible(false);
					layoutSetter ml = new layoutSetter(hw);
					ml.setProductPanelLayout();
					JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
				} catch (Exception e1) {
					workbook.close();
				} 
			}
			}
		);
		
		productid = new JTextField();
		productid.setColumns(10);
		
		productname = new JTextField();
		productname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("*Nhập giá theo cú pháp: #,###,###.##");
		lblNewLabel_3.setForeground(Color.RED);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(product_unitprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(174, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(154, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(productid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(188, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(productname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(188, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3)
					.addContainerGap(90, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(productid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(productname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(product_unitprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(19))
		);
		panel.setLayout(gl_panel);
	}
	
}
