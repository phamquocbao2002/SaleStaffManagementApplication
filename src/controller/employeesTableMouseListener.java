package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.entity.order;
import view.mainView.employeeViewPanel;
import view.subView.employeeDetailInfoFr;

public class employeesTableMouseListener implements MouseListener {
	private employeeViewPanel evp;

	public employeesTableMouseListener(employeeViewPanel evp) {
		this.evp = evp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int sl_row = this.evp.employees_listing_table.getSelectedRow();
		String eN = (String) this.evp.employees_listing_table.getValueAt(sl_row, 0);
		String ePB = (String) this.evp.employees_listing_table.getValueAt(sl_row, 1);
		employeeDetailInfoFr edif = new employeeDetailInfoFr(this.evp.hw);
		edif.employee_name.setText(eN);
		edif.employee_phone.setText(ePB);
		List<order> eOrders = this.evp.hw.ls.dl.getOrdersByEmployee(ePB);
		DefaultTableModel model = (DefaultTableModel) edif.receipts_listing_table.getModel();
		model.setRowCount(eOrders.size());
		for (int i = 0; i < eOrders.size(); i++) {
			edif.receipts_listing_table.setValueAt(eOrders.get(i).getId(), i, 0);
			edif.receipts_listing_table.setValueAt(eOrders.get(i).getOrderValue(), i, 1);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
