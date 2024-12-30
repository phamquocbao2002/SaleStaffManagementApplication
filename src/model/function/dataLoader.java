package model.function;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import model.entity.customer;
import model.entity.employee;
import model.entity.order;

public class dataLoader {
	private String dataPath = "database/database.xls";

	public dataLoader() {

	}

	public List<employee> getAllEmployees() {
		List<employee> employees = new ArrayList<>();
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(dataPath), encode);
			Sheet sheet_nhanvien = workbook.getSheet("nhanvien");
			for (int row = 1; row < sheet_nhanvien.getRows(); row++) {
				employees.add(new employee(sheet_nhanvien.getCell(1, row).getContents()));
			}

		} catch (Exception e) {

		}

		workbook.close();
		return employees;
	}

	public List<customer> getAllCustomers() {
		List<customer> customers = new ArrayList<>();
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(dataPath), encode);
			Sheet sheet_khachhang = workbook.getSheet("khachhang");
			for (int row = 1; row < sheet_khachhang.getRows(); row++) {
				customers.add(new customer(sheet_khachhang.getCell(1, row).getContents()));
			}

		} catch (Exception e) {

		}

		workbook.close();
		return customers;
	}

	public List<order> getAllOrders() {
		List<order> orders = new ArrayList<>();
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(dataPath), encode);
			Sheet sheet_hoadon = workbook.getSheet("hoadon");
			for (int row = 1; row < sheet_hoadon.getRows(); row++) {
				orders.add(new order(sheet_hoadon.getCell(0, row).getContents()));
			}

		} catch (Exception e) {

		}

		workbook.close();
		return orders;
	}

	public List<order> getOrdersByEmployee(String ePhoneNumber) {
		List<order> orders = new ArrayList<>();
		if (ePhoneNumber.equals("") || ePhoneNumber == null) {
			orders = getAllOrders();
		} else {
			Workbook workbook = null;
			try {
				WorkbookSettings encode = new jxl.WorkbookSettings();
				encode.setEncoding("ISO-8859-1");
				workbook = Workbook.getWorkbook(new File(dataPath), encode);
				Sheet sheet_hoadon = workbook.getSheet("hoadon");
				for (int row = 1; row < sheet_hoadon.getRows(); row++) {
					if (ePhoneNumber.equals(sheet_hoadon.getCell(2, row).getContents())) {
						orders.add(new order(sheet_hoadon.getCell(0, row).getContents()));
					}
				}

			} catch (Exception e) {

			}

			workbook.close();

		}
		return orders;
	}
	
	public List<order> getOrdersByConditions(List<String> conditions) {

		List<order> orders = new ArrayList<>();
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(dataPath), encode);
			Sheet sheet_hoadon = workbook.getSheet("hoadon");
			for (int row = 1; row < sheet_hoadon.getRows(); row++) {
				List<String> values = Arrays.asList(sheet_hoadon.getCell(1, row).getContents(), 
						sheet_hoadon.getCell(2, row).getContents());
				if (meetConditions(conditions, values)) {
					orders.add(new order(sheet_hoadon.getCell(0, row).getContents()));
				}
			}

		} catch (Exception e) {
			
		}

		workbook.close();
		return orders;
	}

	private boolean meetConditions(List<String> conditions, List<String> values) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    boolean meetConditions = false;

	    try {
	        Date orderTime = dateFormat.parse(values.get(0)); 
	        String ePN = values.get(1);
	        
	        int i = 0;
	        for (String condition : conditions) {
	            if (condition == null || condition.isEmpty()) {
	                meetConditions = true;
	            } else {
	                switch (i) {
	                    case 0:
	                        meetConditions = ePN.equals(condition.split(" - ")[1]);
	                        break;
	                    case 1:
	                      
	                        Date st = dateFormat.parse(condition);
	                        meetConditions = !orderTime.before(st); 
	                        break;
	                    case 2:
	                        
	                        Date et = dateFormat.parse(condition);
	                        meetConditions = !orderTime.after(et); 
	                        break;
	                    default:
	                        meetConditions = false;
	                        break;
	                }
	            }
	            if (!meetConditions) {
	                break; 
	            }
	            i++;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; 
	    }

	    return meetConditions;
	}

}
