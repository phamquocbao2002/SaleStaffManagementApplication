package model.entity;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class product {
	private String id;
	private String name;
	private String unit;
	private float price;
	private int quantity;
	
	public product(String id) {
		// TODO Auto-generated constructor stub
		this.id = id;
		String filePath = "database/database.xls";
		Workbook workbook = null;
		try {
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(filePath), encode);
			Sheet sheet_sanpham = workbook.getSheet("sanpham");
			for (int row = 1; row < sheet_sanpham.getRows(); row++) {
				if (this.id.equals(sheet_sanpham.getCell(0, row).getContents())) {
					this.name = sheet_sanpham.getCell(1, row).getContents();
					this.unit = sheet_sanpham.getCell(2, row).getContents();
					this.price = Float.parseFloat(sheet_sanpham.getCell(3, row).getContents());
					break;
				}

			}
		} catch (Exception e) {
			System.out.println("err");
			
		}
		
		workbook.close();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Float getAmount() {
		return price*quantity;
	}
	
	
}
