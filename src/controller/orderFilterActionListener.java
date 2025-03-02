package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingWorker;

import model.entity.order;
import view.mainView.orderViewPanel;
import view.subView.Message;

public class orderFilterActionListener implements ActionListener {

	private orderViewPanel ovp;

	public orderFilterActionListener(orderViewPanel ovp) {
		super();
		this.ovp = ovp;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String employee = (String) ovp.salesfaff_filter.getSelectedItem();
		String ePN ="";
		if (employee != null && employee != "Tất cả") {
			ePN = employee;
		}
		List<String> conditions = Arrays.asList(ePN, ovp.startTime.getText(), ovp.endTime.getText());
		Message message = new Message("Đang tải dữ liệu....");
	    SwingWorker<List<order>, Void> worker = new SwingWorker<>() {
	        @Override
	        protected List<order> doInBackground() throws Exception {
	            return ovp.hw.ls.dl.getOrdersByConditions(conditions); 
	        }

	        @Override
	        protected void done() {
	            try {
	                List<order> orders = get();
	                ovp.displayData(orders);
	                fileExportActionListener fel = (fileExportActionListener) ovp.exportFileBtn.getActionListeners()[0];
	        		fel.fe.data.set(0, conditions);
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                message.dispose();
	            }
	        }
	    };

	    
	    new Thread(() -> {
	        message.setVisible(true);
	    }).start();

	    worker.execute(); 
	}
}
