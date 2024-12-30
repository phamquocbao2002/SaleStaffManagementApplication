package view.subView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.function.float_1;
import model.function.substringchecker;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class newReceiptAddingFr extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String staffInfo;
	public JTextField receipt_id;
	public JTextField discount_rate;
	public JTable products_table;
	private JLabel amt_label;
	private Float amt;
	private JLabel final_amt;
	private JLabel discount_amt_label;
	private substringchecker SC;
	public JTextField customerPN;
	private JTextField receipt_date;
	public JLabel customerName;

	public newReceiptAddingFr(String staffInfo) {
		this.staffInfo = staffInfo;
		this.SC = new substringchecker();
		this.amt = (float) 0;
		this.setSize(700, 580);
		this.setTitle("newReceiptAddingFr");
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("NVKD:");

		JLabel staff = new JLabel(staffInfo);

		JLabel lblNewLabel_1 = new JLabel("Số phiếu:");

		receipt_id = new JTextField();
		receipt_id.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Ngày lập:");

		JLabel lblNewLabel_3 = new JLabel("Chiết khấu:");

		discount_rate = new JTextField();
		discount_rate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String discount_rat = discount_rate.getText();
				Float discount_amt = Float.parseFloat(discount_rat) * amt / 100;
				amt_label.setText(new float_1(amt).tostring());
				discount_amt_label.setText(new float_1(discount_amt).tostring());
				final_amt.setText(new float_1(amt - discount_amt).tostring());
			}
		});
		discount_rate.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("%");

		JLabel lblNewLabel_5 = new JLabel("Nhập danh sách sản phẩm vào bảng dưới:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNewButton = new JButton("Lưu hóa đơn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WritableWorkbook writableWorkbook = null;
				try {
					WorkbookSettings encode = new jxl.WorkbookSettings();
					encode.setEncoding("ISO-8859-1");
					Workbook workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);

					writableWorkbook = Workbook.createWorkbook(new File("database/database.xls"), workbook);
					WritableSheet sheet_hoadon = writableWorkbook.getSheet("hoadon");
					WritableSheet sheet_chitiethoadon = writableWorkbook.getSheet("chitiethoadon");
					int rows_in_hd = sheet_hoadon.getRows();
					sheet_hoadon.addCell(new Label(0, rows_in_hd, receipt_id.getText()));
					sheet_hoadon.addCell(new Label(1, rows_in_hd, receipt_date.getText()));
					sheet_hoadon.addCell(new Label(2, rows_in_hd, staffInfo.split(" - ")[1]));
					sheet_hoadon.addCell(new Label(3, rows_in_hd, customerPN.getText()));
					sheet_hoadon.addCell(new Label(4, rows_in_hd, discount_rate.getText() + "%"));
					int rows_in_cthd = sheet_chitiethoadon.getRows();
					for (int row = 0; row < products_table.getModel().getRowCount(); row++) {
						if (products_table.getValueAt(row, 0) != null) {
							sheet_chitiethoadon.addCell(new Label(0, rows_in_cthd + row, receipt_id.getText()));
							sheet_chitiethoadon.addCell(
									new Label(1, rows_in_cthd + row, (String) products_table.getValueAt(row, 1)));
							sheet_chitiethoadon.addCell(new Label(2, rows_in_cthd + row, "Hộp"));
							sheet_chitiethoadon.addCell(new Label(3, rows_in_cthd + row,
									Integer.toString((int) products_table.getValueAt(row, 4))));
						} else {
							break;
						}
					}
					writableWorkbook.write();
					writableWorkbook.close();
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Thêm mới hóa đơn thành công");

				} catch (Exception e1) {
					e1.printStackTrace();
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

		JLabel lblNewLabel_6 = new JLabel("SĐT khách hàng:");

		JLabel lblNewLabel_7 = new JLabel("*Lưu ý: nhấn enter mỗi khi nhập xong 1 giá trị vào bảng");
		lblNewLabel_7.setForeground(Color.RED);

		JLabel lblNewLabel_8 = new JLabel("Cộng tiền:");

		JLabel lblNewLabel_8_1 = new JLabel("Tiền giảm:");

		JLabel lblNewLabel_8_2 = new JLabel("Tổng tiền:");

		amt_label = new JLabel("");

		discount_amt_label = new JLabel("");

		final_amt = new JLabel("");

		customerPN = new JTextField();
		customerPN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Workbook workbook = null;
				try {
					WorkbookSettings encode = new jxl.WorkbookSettings();
					encode.setEncoding("ISO-8859-1");
					workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);
					Sheet sheet = workbook.getSheet("khachhang");
					for (int row = 1; row < sheet.getRows(); row++) {
						if (sheet.getCell(3, row).getContents().equals(staffInfo.split(" - ")[1])) {
							if (new substringchecker().checksubstring(sheet.getCell(1, row).getContents().toLowerCase(),
									customerPN.getText().toLowerCase())) {
								customerPN.setText((sheet.getCell(1, row).getContents()));
								customerName.setText((sheet.getCell(0, row).getContents()));
								break;
							}
						}

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					if (workbook != null) {
						workbook.close();
					}
				}
			}
		});
		customerPN.setForeground(Color.LIGHT_GRAY);
		customerPN.setText("Nhập khách hàng cũ");
		customerPN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (customerPN.getText().equals("Nhập khách hàng cũ")) {
					customerPN.setText("");
					customerPN.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (customerPN.getText().equals("")) {
					customerPN.setText("Nhập khách hàng cũ");

				}
			}
		});
		customerPN.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("hoặc");

		JButton btnNewButton_1 = new JButton("Thêm mới khách hàng");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new newCustomerAddingFr(newReceiptAddingFr.this, staffInfo);
			}
		});
		
		receipt_date = new JTextField();
		receipt_date.setText("04/12/2024");
		receipt_date.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Tên khách hàng:");
		
		customerName = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(amt_label)
											.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
											.addComponent(discount_amt_label)
											.addComponent(final_amt))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
									.addGap(50)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(discount_rate, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel_4))
										.addComponent(receipt_id, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addComponent(staff)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(customerPN, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
												.addComponent(receipt_date, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(customerName))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(438)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_8_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(330, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(staff))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(receipt_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(customerPN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_9)
						.addComponent(btnNewButton_1))
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(receipt_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_10)
						.addComponent(customerName))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(discount_rate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addGap(30)
					.addComponent(lblNewLabel_5)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(amt_label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8_1)
						.addComponent(discount_amt_label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8_2)
						.addComponent(final_amt))
					.addGap(10)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_7)
					.addContainerGap(231, Short.MAX_VALUE))
		);

		products_table = new JTable();
		products_table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				amt = (float) 0;
				int sl_row = products_table.getSelectedRow();
				String tsp = (String) products_table.getValueAt(sl_row, 2);
				int stt = sl_row + 1;
				String msp = "";
				Float dgsp = (float) 0;
				int slsp = (Integer) products_table.getValueAt(sl_row, 4);
				String đvt = "";
				Float product_amt = (float) 0;
				Workbook workbook = null;
				try {
					WorkbookSettings encode = new jxl.WorkbookSettings();
					encode.setEncoding("ISO-8859-1");
					workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);
					Sheet sheet_sanpham = workbook.getSheet("sanpham");
					for (int h = 1; h < sheet_sanpham.getRows(); h++) {
						if (SC.checksubstring(sheet_sanpham.getCell(1, h).getContents().toLowerCase(),
								tsp.toLowerCase())) {
							msp = sheet_sanpham.getCell(0, h).getContents();
							tsp = sheet_sanpham.getCell(1, h).getContents();
							đvt = sheet_sanpham.getCell(2, h).getContents();
							dgsp += Float.parseFloat(sheet_sanpham.getCell(3, h).getContents());
							break;
						}
						// System.out.println("a");
					}
					product_amt = dgsp * slsp;
					products_table.setValueAt(stt, sl_row, 0);
					products_table.setValueAt(msp, sl_row, 1);
					products_table.setValueAt(tsp, sl_row, 2);
					products_table.setValueAt(đvt, sl_row, 3);
					products_table.setValueAt(dgsp, sl_row, 5);
					products_table.setValueAt(product_amt, sl_row, 6);
					for (int i = 0; i < 50; i++) {
						if (products_table.getValueAt(i, 0) == null) {
							break;
						} else {
							amt += (float) products_table.getValueAt(i, 6);
						}
					}
					String discount_rat = discount_rate.getText();
					Float discount_amt = Float.parseFloat(discount_rat) * amt / 100;
					amt_label.setText(new float_1(amt).tostring());
					discount_amt_label.setText(new float_1(discount_amt).tostring());
					final_amt.setText(new float_1(amt - discount_amt).tostring());
					workbook.close();

				} catch (IOException | BiffException e1) {
					workbook.close();
				}
			}
		});

		DefaultTableModel model = new DefaultTableModel(100, 7) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, Integer.class,
					Float.class, Float.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, true, false, true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};
		model.setColumnIdentifiers(
				new Object[] { "STT", "Mã", "Sản phẩm", "ĐVT", "Số lượng", "Đơn giá", "Thành tiền" });
		products_table.setModel(model);
		scrollPane.setViewportView(products_table);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		this.setVisible(true);
	}
}
