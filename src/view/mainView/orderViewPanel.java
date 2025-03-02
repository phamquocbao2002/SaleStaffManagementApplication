package view.mainView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import controller.fileExportActionListener;
import controller.orderFilterActionListener;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import model.entity.order;
import view.subView.receiptDetailFr;

public class orderViewPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	public homeWindow hw;
	private GroupLayout hw_layout;
	public JTextField startTime;
	public JComboBox<String> salesfaff_filter;
	private JTable dataTable;
	private List<order> data = new ArrayList<>();
	public JTextField endTime;
	public JButton exportFileBtn;

	public orderViewPanel(homeWindow hw) {
		this.hw = hw;
		JPanel receipt_view_panel = new JPanel();
		receipt_view_panel.setBackground(Color.WHITE);
		hw_layout = new GroupLayout(hw.main_view_panel);
		hw_layout.setHorizontalGroup(hw_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(hw_layout.createSequentialGroup()
						.addComponent(receipt_view_panel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		hw_layout.setVerticalGroup(hw_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(hw_layout.createSequentialGroup()
						.addComponent(receipt_view_panel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("Lọc:");

		JLabel lblNewLabel_1 = new JLabel("Ngày:");

		startTime = new JTextField();
		startTime.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("*Nhập theo đúng định dạng: ngày/tháng/năm");
		lblNewLabel_2.setForeground(Color.RED);

		JLabel lblNewLabel_3 = new JLabel("NVKD:");

		exportFileBtn = new JButton("Xuất Excel");
		exportFileBtn.addActionListener(new fileExportActionListener("file_exporter/thongkehoadon.xls",
				Arrays.asList(Arrays.asList("", "", ""), data), "xls"));

		salesfaff_filter = new JComboBox<String>();
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			Workbook workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);
			Sheet sheet_nhanvien = workbook.getSheet("nhanvien");
			for (int i = 1; i < sheet_nhanvien.getRows(); i++) {
				salesfaff_filter.addItem(sheet_nhanvien.getCell(0, i).getContents() + " - "
						+ sheet_nhanvien.getCell(1, i).getContents());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		salesfaff_filter.addItem("Tất cả");
		salesfaff_filter.setSelectedItem(null);

		JButton btnNewButton = new JButton("Lọc");
		btnNewButton.addActionListener(new orderFilterActionListener(this));

		JLabel lblNewLabel_4 = new JLabel("-");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));

		endTime = new JTextField();
		endTime.setColumns(10);
		GroupLayout gl_receipt_view_panel = new GroupLayout(receipt_view_panel);
		gl_receipt_view_panel.setHorizontalGroup(gl_receipt_view_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_receipt_view_panel.createSequentialGroup().addContainerGap().addGroup(gl_receipt_view_panel
						.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_receipt_view_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_receipt_view_panel.createSequentialGroup().addComponent(lblNewLabel_2)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_receipt_view_panel.createSequentialGroup()
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(startTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(endTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(salesfaff_filter, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addGap(2))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
								.addComponent(exportFileBtn)))
						.addGap(90)));
		gl_receipt_view_panel.setVerticalGroup(gl_receipt_view_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_receipt_view_panel
						.createSequentialGroup().addGap(3).addComponent(lblNewLabel).addGap(
								15)
						.addGroup(gl_receipt_view_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(startTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)
								.addComponent(salesfaff_filter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4).addComponent(endTime, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_receipt_view_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2).addComponent(btnNewButton))
						.addGap(11)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(exportFileBtn)
						.addContainerGap(27, Short.MAX_VALUE)));

		dataTable = new JTable();
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Float.class, Float.class,
					Float.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		dataTable.setModel(model);
		model.setColumnIdentifiers(
				new Object[] { "Ngày lập", "Mã HĐ", "Khách hàng", "Cộng tiền", "Chiết khấu", "Tổng tiền" });

		dataTable.getColumnModel().getColumn(0).setPreferredWidth(33);
		scrollPane.setViewportView(dataTable);
		receipt_view_panel.setLayout(gl_receipt_view_panel);
		dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				String orderId = (String) dataTable.getValueAt(dataTable.getSelectedRow(), 1);
				new receiptDetailFr(orderId);
			}
		});

	}

	public GroupLayout getLayout() {
		return hw_layout;
	}

	public void displayData(List<order> data) {
		this.data = data;
		fileExportActionListener fel = (fileExportActionListener) this.exportFileBtn.getActionListeners()[0];
		fel.fe.data.set(1, data);
		DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
		model.setRowCount(data.size());
		Collections.sort(data,
				Comparator.comparing(
						o -> (LocalDate) LocalDate.parse(o.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
						Comparator.reverseOrder()));
		for (order order : data) {
			this.dataTable.setValueAt(order.getDate(), data.indexOf(order), 0);
			this.dataTable.setValueAt(order.getId(), data.indexOf(order), 1);
			this.dataTable.setValueAt(order.getCustomer().getName(), data.indexOf(order), 2);
			this.dataTable.setValueAt(order.getOrderValue(), data.indexOf(order), 3);
			this.dataTable.setValueAt(order.getDiscount(), data.indexOf(order), 4);
			this.dataTable.setValueAt(order.getNetRevenue(), data.indexOf(order), 5);
		}
	}

}
