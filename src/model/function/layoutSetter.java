package model.function;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import model.entity.customer;
import model.entity.employee;
import model.entity.order;
import view.mainView.customerViewPanel;
import view.mainView.employeeViewPanel;
import view.mainView.homeWindow;
import view.mainView.productViewPanel;
import view.mainView.orderViewPanel;
import view.subView.Message;

public class layoutSetter {
	private homeWindow hw;
	public dataLoader dl = new dataLoader();
	public layoutSetter(homeWindow hw) {
		this.hw = hw;
	}

	public void setEmployeePanelLayout() {
		
		Message message = new Message("Đang tải dữ liệu....");
	    SwingWorker<List<employee>, Void> worker = new SwingWorker<>() {
	        @Override
	        protected List<employee> doInBackground() throws Exception {
	        	return dl.getAllEmployees();
	        }

	        @Override
	        protected void done() {
	        	
	            try {
	            	hw.main_view_panel.removeAll();
	        		employeeViewPanel evp = new employeeViewPanel(hw);
	        		hw.main_view_panel.setLayout(evp.getLayout());
	        		List<employee> employees = get();
	        		DefaultTableModel model = (DefaultTableModel) evp.employees_listing_table.getModel();
	        		model.setRowCount(employees.size());
	        		for (employee employee : employees) {
	        			evp.employees_listing_table.setValueAt(employee.getName(), employees.indexOf(employee), 0);
	        			evp.employees_listing_table.setValueAt(employee.getPhoneNumber(), employees.indexOf(employee), 1);
	        		}
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

	public void setCustomerPanelLayout() {
		 Message message = new Message("Đang tải dữ liệu....");
		    SwingWorker<List<customer>, Void> worker = new SwingWorker<>() {
		        @Override
		        protected List<customer> doInBackground() throws Exception {
		            return dl.getAllCustomers(); 
		        }

		        @Override
		        protected void done() {
		        	
		            try {
		            	hw.main_view_panel.removeAll();
		        		customerViewPanel cvp = new customerViewPanel(hw);
		        		hw.main_view_panel.setLayout(cvp.getLayout());
		        		List<customer> customers = get();
		        		DefaultTableModel model = (DefaultTableModel) cvp.customers_listing_table.getModel();
		        		model.setRowCount(customers.size());
		        		for (customer customer : customers) {
		        			cvp.customers_listing_table.setValueAt(customer.getName(), customers.indexOf(customer), 0);
		        			cvp.customers_listing_table.setValueAt(customer.getPhoneNumber(), customers.indexOf(customer), 1);
		        			cvp.customers_listing_table.setValueAt(customer.getAddress(), customers.indexOf(customer), 2);
		        			cvp.customers_listing_table.setValueAt(customer.getStaff().getName(), customers.indexOf(customer), 3);
		        		}
		        		

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

	public void setOrderPanelLayout() {
	    Message message = new Message("Đang tải dữ liệu....");
	    SwingWorker<List<order>, Void> worker = new SwingWorker<>() {
	        @Override
	        protected List<order> doInBackground() throws Exception {
	            return dl.getAllOrders(); 
	        }

	        @Override
	        protected void done() {
	        	
	            try {
	            	hw.main_view_panel.removeAll();
	        	    orderViewPanel ovp = new orderViewPanel(hw);
	            	hw.main_view_panel.setLayout(ovp.getLayout());
	                List<order> orders = get();
	                ovp.displayData(orders);
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



	public void setProductPanelLayout() {
		hw.main_view_panel.removeAll();
		productViewPanel pvp = new productViewPanel(hw);
		hw.main_view_panel.setLayout(pvp.getLayout());
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);
			Sheet sheet = workbook.getSheet("sanpham");
			for (int row = 1; row < sheet.getRows(); row++) {
				for (int col = 0; col < sheet.getColumns(); col++) {
					Cell cell = sheet.getCell(col, row);
					if (col == 3) {
						pvp.products_listing_table.setValueAt(Float.parseFloat(cell.getContents()), row - 1, col);
					} else {
						pvp.products_listing_table.setValueAt(cell.getContents(), row - 1, col);
					}
				}
			}
			workbook.close();
		} catch (Exception e1) {
			workbook.close();
		}
	}
}
