package model.entity;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class customer {
	private String phoneNumber;
	private String name;
	private String address;
	private employee staff;
	
	
	public customer(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		String filePath = "database/database.xls";
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(filePath), encode);
			Sheet sheet_khachhang = workbook.getSheet("khachhang");
			for (int row = 1; row < sheet_khachhang.getRows(); row++) {
				if (phoneNumber.equals(sheet_khachhang.getCell(1, row).getContents())) {
					this.name = sheet_khachhang.getCell(0, row).getContents();
					this.address = sheet_khachhang.getCell(2, row).getContents();
					this.staff = new employee(sheet_khachhang.getCell(3, row).getContents());
					break;
				}

			}
		} catch (Exception e) {
			
		}
		
		workbook.close();
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public employee getStaff() {
		return staff;
	}



	public float getTotalOrdersValue() {
		float totalOrdersValue = 0;
		String filePath = "database/database.xls";
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(filePath), encode);
			Sheet sheet_hoadon = workbook.getSheet("hoadon");
			for (int row = 1; row < sheet_hoadon.getRows(); row++) {
				if (name.equals(sheet_hoadon.getCell(2, row).getContents())) {
					order order = new order(sheet_hoadon.getCell(0, row).getContents());
					totalOrdersValue += order.getOrderValue();
					order = null;
				}

			}
		} catch (Exception e) {
			
		}
		
		workbook.close();
		return totalOrdersValue;
	}
	
	

}
