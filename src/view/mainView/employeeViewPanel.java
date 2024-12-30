package view.mainView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import controller.ImportEmployeeDataListener;
import controller.employeesTableMouseListener;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import view.mainView.homeWindow;
import view.subView.employeeDetailInfoFr;
import view.subView.newEmployeeAddingFr;


public class employeeViewPanel {
	private JPanel employee_view_panel;
	public homeWindow hw;
	private GroupLayout hw_layout;
	public JTable employees_listing_table;
	public employeeViewPanel(homeWindow hw) {
		this.hw = hw;
		
		JPanel employee_view_panel = new JPanel();
		employee_view_panel.setBackground(Color.WHITE);
		hw_layout = new GroupLayout(hw.main_view_panel);
		hw_layout.setHorizontalGroup(
				hw_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(hw_layout.createSequentialGroup()
					.addComponent(employee_view_panel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		hw_layout.setVerticalGroup(
				hw_layout.createParallelGroup(Alignment.LEADING)
				.addGroup(hw_layout.createSequentialGroup()
					.addComponent(employee_view_panel, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Danh sách nhân viên:");
		
		JButton btnNewButton = new JButton("Nhập dữ liệu nhân viên");
		btnNewButton.addActionListener(new ImportEmployeeDataListener());
		
		JLabel lblNewLabel_1 = new JLabel("*Nhập file csv theo đúng định dạng bên dưới");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Thêm mới nhân viên");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new newEmployeeAddingFr();
		}});
		GroupLayout gl_employee_view_panel = new GroupLayout(employee_view_panel);
		gl_employee_view_panel.setHorizontalGroup(
			gl_employee_view_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_employee_view_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_employee_view_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_employee_view_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(290, Short.MAX_VALUE))
		);
		gl_employee_view_panel.setVerticalGroup(
			gl_employee_view_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_employee_view_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addGap(3)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		
		employees_listing_table = new JTable();
		DefaultTableModel model = new DefaultTableModel(1000,3) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		employees_listing_table.setModel(model);
		model.setColumnIdentifiers(new Object[]{"Tên nhân viên", "Số điện thoại"});
        
        employees_listing_table.addMouseListener(new employeesTableMouseListener(this));
        
		employees_listing_table.getColumnModel().getColumn(0).setPreferredWidth(33);
		scrollPane.setViewportView(employees_listing_table);
		employee_view_panel.setLayout(gl_employee_view_panel);
		
		}
	public JPanel getPanel() {
		return employee_view_panel;
	}
	public GroupLayout getLayout() {
		return hw_layout;
	}
}
