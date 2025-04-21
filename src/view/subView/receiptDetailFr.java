package view.subView;

import javax.swing.JFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.fileExportActionListener;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.entity.order;
import model.entity.product;
import utils.file_exporter;
import utils.float_1;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

import jxl.format.Border;
import jxl.format.BorderLineStyle;

public class receiptDetailFr extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;
	private JTable products_listing_table;
	public receiptDetailFr(String orderId) {
		this.orderId = orderId;
		order order = new order(this.orderId);
		this.setSize(700, 600);
		this.setTitle("receiptDetailFr");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(1, Short.MAX_VALUE)));

		JLabel lblNewLabel = new JLabel("Chi tiết đơn hàng:");

		JLabel lblNewLabel_1 = new JLabel("Số phiếu:");

		JLabel receipt_number = new JLabel(order.getId());

		JLabel lblNewLabel_2 = new JLabel("Ngày:");

		JLabel receipt_date = new JLabel(order.getDate());

		JLabel lblNewLabel_3 = new JLabel("Khách hàng:");

		JLabel customer_name = new JLabel(order.getCustomer().getName());

		JLabel lblNewLabel_4 = new JLabel("NVKD:");

		JLabel salestaff_name = new JLabel(order.getStaff().getName());

		JLabel lblNewLabel_5 = new JLabel("Sđt:");

		JLabel customer_phone = new JLabel(order.getCustomer().getPhoneNumber());

		JLabel lblNewLabel_6 = new JLabel("Địa chỉ:");

		JLabel customer_address = new JLabel(order.getCustomer().getAddress());

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel_7 = new JLabel("Cộng tiền:");

		JLabel amt = new JLabel(new float_1(order.getOrderValue()).tostring());

		JLabel lblNewLabel_8 = new JLabel("Tiền giảm:");

		JLabel discount_amt = new JLabel(new float_1(order.getDiscount()).tostring());

		JLabel lblNewLabel_9 = new JLabel("Tổng tiền:");

		JLabel final_amt = new JLabel(new float_1(order.getNetRevenue()).tostring());

		JLabel lblNewLabel_10 = new JLabel("Chiết khấu:");

		JLabel discount_rate = new JLabel(order.getDiscountRate());

		JButton btnNewButton = new JButton("Xuất Excel");
		btnNewButton.addActionListener(new fileExportActionListener("file_exporter/phieuxuatkho.xls", Arrays.asList(order), "xls"));

		JButton btnNewButton_1 = new JButton("Xuất PDF");
		btnNewButton_1.addActionListener(new fileExportActionListener("file_exporter/phieuxuatkho.xls", Arrays.asList(order), "pdf"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(21)
						.addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
										.addComponent(
												btnNewButton_1, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
										.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup().addGap(99)
														.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 65,
																GroupLayout.PREFERRED_SIZE)
														.addGap(35).addComponent(discount_rate))
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE))
										.addGap(74)
										.addGroup(
												gl_panel.createParallelGroup(Alignment.LEADING).addComponent(
														final_amt).addComponent(discount_amt).addComponent(amt))
										.addGap(196))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 500,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE,
																		45, GroupLayout.PREFERRED_SIZE)
																.addGap(28).addComponent(customer_address))
														.addComponent(
																lblNewLabel, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
																.createParallelGroup(Alignment.LEADING, false)
																.addGroup(gl_panel.createSequentialGroup()
																		.addComponent(lblNewLabel_3,
																				GroupLayout.PREFERRED_SIZE, 75,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(customer_name))
																.addGroup(gl_panel.createSequentialGroup()
																		.addComponent(lblNewLabel_2)
																		.addPreferredGap(ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(receipt_date))
																.addGroup(gl_panel.createSequentialGroup()
																		.addComponent(lblNewLabel_1,
																				GroupLayout.PREFERRED_SIZE, 60,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(118).addComponent(receipt_number)))
																.addGap(73)
																.addGroup(
																		gl_panel.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblNewLabel_4)
																				.addComponent(lblNewLabel_5,
																						GroupLayout.PREFERRED_SIZE, 45,
																						GroupLayout.PREFERRED_SIZE))
																.addGap(91).addGroup(
																		gl_panel.createParallelGroup(Alignment.LEADING)
																				.addComponent(customer_phone)
																				.addComponent(salestaff_name))))
												.addContainerGap(179, Short.MAX_VALUE))))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(receipt_number).addComponent(lblNewLabel_4).addComponent(salestaff_name))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2).addComponent(receipt_date))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_3)
						.addComponent(customer_name).addComponent(lblNewLabel_5).addComponent(customer_phone))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_6)
						.addComponent(customer_address))
				.addGap(28).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_7).addComponent(amt)
						.addComponent(lblNewLabel_10).addComponent(discount_rate))
				.addGroup(
						gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_8).addComponent(discount_amt))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_9).addComponent(final_amt)))
								.addGroup(gl_panel.createSequentialGroup().addGap(24).addComponent(btnNewButton)))
				.addGap(24).addComponent(btnNewButton_1).addContainerGap(50, Short.MAX_VALUE)));

		products_listing_table = new JTable();
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, Integer.class,
					Float.class, Float.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
				
			};
		model.setColumnIdentifiers(new Object[] { "STT", "Mã", "Sản phẩm", "ĐVT", "Số lượng", "Đơn giá", "Thành tiền" });
		model.setRowCount(order.getProducts().size());
		products_listing_table.setModel(model);
		scrollPane.setViewportView(products_listing_table);
		int row = 0;
		for (product product: order.getProducts()) {
			products_listing_table.setValueAt(row+1, row, 0);
			products_listing_table.setValueAt(product.getId(), row, 1);
			products_listing_table.setValueAt(product.getName(), row, 2);
			products_listing_table.setValueAt(product.getUnit(), row, 3);
			products_listing_table.setValueAt(product.getQuantity(), row, 4);
			products_listing_table.setValueAt(product.getPrice(), row, 5);
			products_listing_table.setValueAt(product.getAmount(), row, 6);
			row++;
		}
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
