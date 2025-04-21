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

public class productDetailFr extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel product_id;
	public JLabel product_name;
	public JTextField product_unitprice;
	public productDetailFr(homeWindow hw) {
		this.setSize(300,350);
		this.setTitle("productDetailFr");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Mã sản phẩm:");
		
		product_id = new JLabel("New label");
		
		JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm:");
		
		product_name = new JLabel("New label");
		
		JLabel lblNewLabel_2 = new JLabel("Đơn giá:");
		
		product_unitprice = new JTextField();
		product_unitprice.setColumns(10);
		
		JButton btnNewButton = new JButton("Lưu thay đổi");
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
					for (int i=1;i<sheet.getRows();i++) {
						if(sheet.getCell(0, i).getContents().equals(product_id.getText())){
							String up = "";
							for (int idx = 0; idx <product_unitprice.getText().split(",").length; idx++){
								up = up + product_unitprice.getText().split(",")[idx];
							}
							sheet.addCell(new Label(3,i, up));
							break;
						}
					}
					writableWorkbook.write();
					writableWorkbook.close();
					setVisible(false);
					layoutSetter ml = new layoutSetter(hw);
					ml.setProductPanelLayout();
					JOptionPane.showMessageDialog(null, "Đã lưu thay đổi");
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
			}
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(product_id)
						.addComponent(lblNewLabel_1)
						.addComponent(product_name)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(product_unitprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(174, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(154, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(product_id)
					.addGap(26)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(product_name)
					.addGap(27)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(product_unitprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(23))
		);
		panel.setLayout(gl_panel);
	}
	
}
