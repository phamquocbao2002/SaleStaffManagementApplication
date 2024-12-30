package view.mainView;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import controller.ImportCustomerDataListener;

public class customerViewPanel {
	private homeWindow hw;
	private GroupLayout hw_layout;
	public JTable customers_listing_table;

	public customerViewPanel(homeWindow hw) {
		this.hw = hw;
		JPanel customer_view_panel = new JPanel();
		customer_view_panel.setBackground(Color.WHITE);
		hw_layout = new GroupLayout(hw.main_view_panel);
		hw_layout.setHorizontalGroup(hw_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(hw_layout.createSequentialGroup()
						.addComponent(customer_view_panel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		hw_layout.setVerticalGroup(hw_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(hw_layout.createSequentialGroup()
						.addComponent(customer_view_panel, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("Danh sách khách hàng:");

		JButton btnNewButton = new JButton("Nhập dữ liệu khách hàng");
		btnNewButton.addActionListener(new ImportCustomerDataListener());

		JLabel lblNewLabel_1 = new JLabel(
				"*Nhập file csv theo đúng định dạng bên dưới");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		GroupLayout gl_customer_view_panel = new GroupLayout(customer_view_panel);
		gl_customer_view_panel.setHorizontalGroup(gl_customer_view_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_customer_view_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_customer_view_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 550,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(240, Short.MAX_VALUE)));
		gl_customer_view_panel.setVerticalGroup(gl_customer_view_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_customer_view_panel.createSequentialGroup().addContainerGap().addComponent(btnNewButton)
						.addGap(3).addComponent(lblNewLabel_1).addGap(18).addComponent(lblNewLabel).addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(79, Short.MAX_VALUE)));

		customers_listing_table = new JTable();
		DefaultTableModel model = new DefaultTableModel() {
			
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		model.setColumnCount(4);
		customers_listing_table.setModel(model);
		model.setColumnIdentifiers(
				new Object[] { "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Nhân viên phụ trách" });

		customers_listing_table.getColumnModel().getColumn(0).setPreferredWidth(33);
		scrollPane.setViewportView(customers_listing_table);
		customer_view_panel.setLayout(gl_customer_view_panel);

	}

	public GroupLayout getLayout() {
		return hw_layout;
	}
}
