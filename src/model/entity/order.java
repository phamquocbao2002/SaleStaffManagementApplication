package model.entity;

import java.io.File;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class order {
	private String id;
	private String date;
	private customer customer;
	private employee staff;
	private Float discount = (float) 0;
	private String discountRate;
	private ArrayList<product> products = new ArrayList<>();
	private Float orderValue = (float) 0;

	public order(String id) {
		this.id = id;
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File("database/database.xls"), encode);
			Sheet sheet_hoadon = workbook.getSheet("hoadon");
			Sheet sheet_chitiethoadon = workbook.getSheet("chitiethoadon");
			for (int i = 1; i < sheet_hoadon.getRows(); i++) {
				if (this.id.equals(sheet_hoadon.getCell(0, i).getContents())) {
					this.date = sheet_hoadon.getCell(1, i).getContents();
					this.customer = new customer(sheet_hoadon.getCell(3, i).getContents());
					this.staff = new employee(sheet_hoadon.getCell(2, i).getContents());
					for (int k = 1; k < sheet_chitiethoadon.getRows(); k++) {
						if (this.id.equals(sheet_chitiethoadon.getCell(0, k).getContents())) {
							product product = new product(sheet_chitiethoadon.getCell(1, k).getContents());
							product.setQuantity(Integer.parseInt(sheet_chitiethoadon.getCell(3, k).getContents()));
							this.products.add(product);
							this.orderValue += product.getAmount();
						}
					}
					this.discountRate = sheet_hoadon.getCell(4, i).getContents();
					float discountRate = Float.parseFloat(this.discountRate
							.substring(0, this.discountRate.length() - 1)) / 100;
					this.discount = discountRate*this.orderValue;
					
					break;
				}
			}
		} catch (Exception e) {
			
		}
		
		workbook.close();
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public customer getCustomer() {
		return customer;
	}

	public employee getStaff() {
		return staff;
	}

	public float getDiscount() {
		return discount;
	}
	
	public String getDiscountRate() {
		return discountRate;
	}

	public ArrayList<product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<product> products) {
		this.products = products;
	}

	public void setOrderValue(float orderValue) {
		this.orderValue = orderValue;
	}

	public Float getOrderValue() {
		return orderValue;
	}
	
	public Float getNetRevenue() {
		return orderValue - discount;
	}

}
