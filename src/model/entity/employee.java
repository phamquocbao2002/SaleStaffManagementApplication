package model.entity;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class employee {
	private String name;
	private String phoneNumber;

	public employee(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		String filePath = "database/database.xls";
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(filePath), encode);
			Sheet sheet_nhanvien = workbook.getSheet("nhanvien");
			for (int row = 1; row < sheet_nhanvien.getRows(); row++) {
				if (phoneNumber.equals(sheet_nhanvien.getCell(1, row).getContents())) {
					this.name = sheet_nhanvien.getCell(0, row).getContents();
					break;
				}

			}
		} catch (Exception e) {

		}

		workbook.close();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Float getSales() {
		Float sales = (float) 0;
		String filePath = "database/database.xls";
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(filePath), encode);
			Sheet sheet_hoadon = workbook.getSheet("hoadon");
			for (int row1 = 1; row1 < sheet_hoadon.getRows(); row1++) {
				if (phoneNumber.equals(sheet_hoadon.getCell(2, row1).getContents())) {
					String mhd = sheet_hoadon.getCell(0, row1).getContents();
					order order = new order(mhd);
					sales += order.getOrderValue();
					order = null;
				}
			}
		} catch (Exception e) {
			
		}
		return sales;
	}

}
