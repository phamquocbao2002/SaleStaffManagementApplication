package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.entity.order;
import model.entity.product;
import model.function.file_exporter;
import model.function.float_1;

public class fileExportActionListener implements ActionListener {

	private file_exporter fe;
	public List<Object> data;
	private String source;
	private String fileType;

	public fileExportActionListener(String source, List<Object> data, String fileType) {
		super();
		this.data = data;
		this.source = source;
		this.fileType = fileType;
		this.fe = new file_exporter(this.source);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		wirteDataToSource();

		if (fileType.equals("xls")) {
			fe.saveAsXls();
		} else {
			fe.saveAsPdf();
		}
	}

	private void wirteDataToSource() {
		WritableWorkbook writableWorkbook = null;
		WritableCellFormat cellFormatFinalContent = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 12));
		WritableCellFormat cellFormatRightContent = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 12));
		WritableCellFormat cellFormatAmtRightContent = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 12));
		WritableCellFormat cellFormatLeftContent = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 12));
		try {
			cellFormatRightContent.setWrap(true);
			cellFormatLeftContent.setWrap(true);
			cellFormatFinalContent.setWrap(true);
			cellFormatRightContent.setBorder(Border.LEFT, BorderLineStyle.THIN);
			cellFormatRightContent.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cellFormatLeftContent.setBorder(Border.LEFT, BorderLineStyle.THIN);
			cellFormatLeftContent.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cellFormatAmtRightContent.setBorder(Border.LEFT, BorderLineStyle.THIN);
			cellFormatAmtRightContent.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cellFormatAmtRightContent.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			cellFormatLeftContent.setAlignment(Alignment.LEFT);
			cellFormatRightContent.setAlignment(Alignment.RIGHT);
			cellFormatAmtRightContent.setAlignment(Alignment.RIGHT);
			cellFormatFinalContent.setAlignment(Alignment.RIGHT);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (data.get(0).getClass().getSimpleName().equals("order")) {
			order order = (order) data.get(0);
			try {
				WorkbookSettings encode = new jxl.WorkbookSettings();
				encode.setEncoding("ISO-8859-1");
				Workbook workbook = Workbook.getWorkbook(new File(this.source), encode);
				writableWorkbook = Workbook.createWorkbook(new File(this.source), workbook);
				WritableSheet sheet = writableWorkbook.getSheet(0);
				while (sheet.getRows() > 10 == true) {
					sheet.removeRow(sheet.getRows() - 1);
				}
				Label sophieu = new Label(6, 0, order.getId());
				Label ngay = new Label(6, 2, order.getDate());
				Label khachhang = new Label(0, 5, "Khách hàng: "+ order.getCustomer().getName());
				Label sdt_khachhang = new Label(6, 5, order.getCustomer().getPhoneNumber());
				Label diachi_khachhang = new Label(0, 6, "Địa chỉ: " + order.getCustomer().getAddress());
				Label nvkd = new Label(6, 6, order.getStaff().getName());
				Label n1 = new Label(0, 8, "");
				Label n2 = new Label(1, 8, "");
				Label n3 = new Label(2, 8, "");
				Label n4 = new Label(3, 8, "");
				Label n5 = new Label(4, 8, "");
				Label n6 = new Label(5, 8, "");
				Label n7 = new Label(6, 8, "");
				sheet.addCell(sophieu);
				sheet.addCell(ngay);
				sheet.addCell(khachhang);
				sheet.addCell(sdt_khachhang);
				sheet.addCell(diachi_khachhang);
				sheet.addCell(nvkd);
				sheet.addCell(n1);
				sheet.addCell(n2);
				sheet.addCell(n3);
				sheet.addCell(n4);
				sheet.addCell(n5);
				sheet.addCell(n6);
				sheet.addCell(n7);
				
				
				List<product> products = order.getProducts();
				int k = 0;
				for (product product : products) {
					sheet.addCell(new Label(0, 10 + k, Integer.toString(k + 1), cellFormatLeftContent));
					sheet.addCell(new Label(1, 10 + k, product.getId(), cellFormatLeftContent));
					sheet.addCell(new Label(2, 10 + k, product.getName(), cellFormatLeftContent));
					sheet.addCell(new Label(3, 10 + k, product.getUnit(), cellFormatLeftContent));
					sheet.addCell(new Label(4, 10 + k, Integer.toString(product.getQuantity()), cellFormatRightContent));
					sheet.addCell(new Label(5, 10 + k, new float_1(product.getPrice()).tostring(), cellFormatRightContent));
					sheet.addCell(new Label(6, 10 + k, new float_1(product.getAmount()).tostring(), cellFormatAmtRightContent));
					k++;
				}

				sheet.addCell(new Label(5, 10 + k, "Cộng tiền:"));
				sheet.addCell(new Label(5, 10 + k + 1, "Chiết khấu:"));
				sheet.addCell(new Label(5, 10 + k + 2, "Tiền giảm:"));
				sheet.addCell(new Label(5, 10 + k + 3, "Tổng tiền:"));
				sheet.addCell(new Label(6, 10 + k, new float_1(order.getOrderValue()).tostring(), cellFormatFinalContent));
				sheet.addCell(new Label(6, 10 + k + 1, order.getDiscountRate(), cellFormatFinalContent));
				sheet.addCell(new Label(6, 10 + k + 2, new float_1(order.getDiscount()).tostring(), cellFormatFinalContent));
				sheet.addCell(new Label(6, 10 + k + 3, new float_1(order.getNetRevenue()).tostring(), cellFormatFinalContent));

				sheet.addCell(new Label(3, 16 + k, "Người giao hàng"));
				sheet.addCell(new Label(3, 17 + k, "(ký, họ tên)"));
				sheet.addCell(new Label(6, 16 + k, "Người lập phiếu"));
				sheet.addCell(new Label(6, 17 + k, "(ký, họ tên)"));
				sheet.addCell(new Label(6, 19 + k, "VÕ VĂN BẢY"));
				writableWorkbook.write();

			} catch (Exception e1) {

			}

			try {
				writableWorkbook.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			@SuppressWarnings("unchecked")
			List<Object> ordersMasterData = (List<Object>) data.get(0);
			@SuppressWarnings("unchecked")
			List<order> orders = (List<order>) data.get(1);
			try {
				WorkbookSettings encode = new jxl.WorkbookSettings();
				encode.setEncoding("ISO-8859-1");
				Workbook workbook = Workbook.getWorkbook(new File(this.source), encode);
				writableWorkbook = Workbook.createWorkbook(new File(this.source), workbook);
				WritableSheet sheet = writableWorkbook.getSheet(0);
				while (sheet.getRows() > 3) {
					sheet.removeRow(sheet.getRows() - 1);
				}
				String st = (String) ordersMasterData.get(1);
				String et = (String) ordersMasterData.get(2);
				String nv = (String) ordersMasterData.get(0);
				sheet.addCell(new Label(0, 1, "Từ ngày " + st + " đến ngày " + et));
				sheet.addCell(new Label(9, 1, nv));
				Float revenue = (float) 0;
				Float discount = (float) 0;
				Float amt = (float) 0;
				int k = 0;
				for (order order : orders) {
					if (orders.size() != 0) {
						sheet.addCell(new Label(0, 3 + k,
								Integer.toString(k+1)));
						sheet.addCell(new Label(1, 3 + k,
								order.getDate()));
						sheet.addCell(new Label(2, 3 + k,
								order.getId()));
						sheet.addCell(new Label(3, 3 + k,
								order.getCustomer().getName()));
						sheet.addCell(new Label(4, 3 + k,
								new float_1(order.getOrderValue()).tostring()));
						revenue += order.getOrderValue();
						sheet.addCell(new Label(5, 3 + k,
								new float_1(order.getDiscount()).tostring()));
						discount += order.getDiscount();
						sheet.addCell(new Label(6, 3 + k,
								new float_1(order.getNetRevenue()).tostring()));
						amt += order.getNetRevenue();
						k++;
					} else {
						break;
					}

				}
				sheet.addCell(new Label(3, 3 + k, "Tổng cộng:"));
				sheet.addCell(new Label(4, 3 + k, new float_1(revenue).tostring()));
				sheet.addCell(new Label(5, 3 + k, new float_1(discount).tostring()));
				sheet.addCell(new Label(6, 3 + k, new float_1(amt).tostring()));
				for (int z = 2; z <= 4 + k; z++) {
					for (int n = 0; n <= 6; n++) {
						sheet.getWritableCell(n, z).setCellFormat(getBorderedCellFormat());
					}
				}
				writableWorkbook.write();
			} catch (Exception e1) {
				
			}
			
			try {
				writableWorkbook.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static jxl.write.WritableCellFormat getBorderedCellFormat() {
		jxl.write.WritableCellFormat cellFormat = new jxl.write.WritableCellFormat();
		try {
			
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cellFormat;
	}

}
