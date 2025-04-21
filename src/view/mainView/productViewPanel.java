package view.mainView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import utils.float_1;
import view.subView.productAddingFr;
import view.subView.productDetailFr;
public class productViewPanel {
	private homeWindow hw;
	public JTable products_listing_table;
	private GroupLayout hw_layout;
	public productViewPanel(homeWindow hw) {
		this.hw = hw;
		
		JPanel product_view_panel = new JPanel();
		product_view_panel.setBackground(Color.WHITE);
		hw_layout = new GroupLayout(hw.main_view_panel);
		hw_layout.setHorizontalGroup(
				hw_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(hw_layout.createSequentialGroup()
					.addComponent(product_view_panel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		hw_layout.setVerticalGroup(
				hw_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(hw_layout.createSequentialGroup()
					.addComponent(product_view_panel, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Danh sách sản phẩm:");
		
		JButton btnNewButton = new JButton("Thêm mới sản phẩm");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new productAddingFr(hw);
			}
			
		});
		JLabel lblNewLabel_1 = new JLabel("*Nhập file excel theo đúng định dạng bên dưới");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		GroupLayout gl_product_view_panel = new GroupLayout(product_view_panel);
		gl_product_view_panel.setHorizontalGroup(
			gl_product_view_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_product_view_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_product_view_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(90, Short.MAX_VALUE))
		);
		gl_product_view_panel.setVerticalGroup(
			gl_product_view_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_product_view_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addGap(3)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		
		products_listing_table = new JTable();
		DefaultTableModel model = new DefaultTableModel(10000,4) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, Float.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		products_listing_table.setModel(model);
		model.setColumnIdentifiers(new Object[]{"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Đơn giá"});
        for (int row = 0; row < 10000; row++) {
            for (int col = 0; col < 4; col++) {
            	products_listing_table.setValueAt(null, row, col);
            }
        }
        products_listing_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int sl_row = products_listing_table.getSelectedRow();
				productDetailFr pdf = new productDetailFr(hw);
				pdf.product_id.setText((String) products_listing_table.getValueAt(sl_row, 0));
				pdf.product_name.setText((String) products_listing_table.getValueAt(sl_row, 1));
				pdf.product_unitprice.setText(new float_1((float)products_listing_table.getValueAt(sl_row, 3)).tostring());
			}
		});
		products_listing_table.getColumnModel().getColumn(0).setPreferredWidth(33);
		scrollPane.setViewportView(products_listing_table);
		product_view_panel.setLayout(gl_product_view_panel);
	}
	public GroupLayout getLayout() {
		return hw_layout;
	}

}
