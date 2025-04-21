package view.mainView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import utils.layoutSetter;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class homeWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel main_view_panel;
	public layoutSetter ls;
	public homeWindow() {
		this.ls = new layoutSetter(this);
		this.setSize(700,500);
		this.setTitle("Quản lý nhân viên");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ls.setEmployeePanelLayout();
		
		main_view_panel = new JPanel();
		main_view_panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(main_view_panel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(main_view_panel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		main_view_panel.setLayout(new BorderLayout(0, 0));
		
		getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Nhân viên");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ls.setEmployeePanelLayout();
			}
		});
		
		
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("Khách hàng");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ls.setCustomerPanelLayout();
			}
		});
		
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Hóa đơn");
		mnNewMenu_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ls.setOrderPanelLayout();
			}
		});
		
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("Sản phẩm");
		mnNewMenu_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ls.setProductPanelLayout();
			}
		});
		
		menuBar.add(mnNewMenu_4);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new homeWindow();
	}
}

