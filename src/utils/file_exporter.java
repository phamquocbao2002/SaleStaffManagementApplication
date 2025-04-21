package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.entity.order;
import model.entity.product;
import view.subView.Message;

public class file_exporter {
	private String source;
	public List<Object> data;

	public file_exporter(String source, List<Object> data) {
		this.source = source;
		this.data = data;
	}

	private static String chooseDestinationFile(String extension, String description) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Export Data to");
		fileChooser.setCurrentDirectory(new File("C://BANHANG//PHIEUXUATKHO"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extension);
		fileChooser.setFileFilter(filter);

		int userChoice = fileChooser.showSaveDialog(null);
		if (userChoice == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();

			if (!filePath.endsWith("." + extension)) {
				filePath += "." + extension;
			}
			return filePath;
		} else {
			return null;
		}
	}

	public void saveAsXls() {
		String destination = chooseDestinationFile("xls", "Excel Workbook");
		Message message = new Message("Đang xuất file...");
		SwingWorker<Void, Void> worker = new SwingWorker<>() {

			@Override
			protected Void doInBackground() throws Exception {
				try {
					Files.copy(Path.of(source), Path.of(destination), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					message.setMessage("Xuất file thất bại. Vui lòng thử lại !");
				}
				return null;
			}

			@Override
			protected void done() {
				SwingUtilities.invokeLater(() -> message.setMessage("Xuất file thành công"));
			}
		};

		if (destination != null) {
			SwingUtilities.invokeLater(() -> message.setVisible(true));
			worker.execute();
		}

	}

	public void saveAsPdf() {
		String destination = chooseDestinationFile("pdf", "PDF Document");
		Message message = new Message("Đang xuất file...");
		SwingWorker<Void, Void> worker = new SwingWorker<>() {
			@Override
			protected Void doInBackground() throws Exception {
				try {
					new ExcelToPdfExporter().exportExcelToPdf(source, destination);
				} catch (Exception e) {
					message.setMessage("Xuất file thất bại. Vui lòng thử lại !");
				}

				return null;
			}

			@Override
			protected void done() {
				SwingUtilities.invokeLater(() -> message.setMessage("Xuất file thành công"));
			}
		};

		if (destination != null) {
			SwingUtilities.invokeLater(() -> message.setVisible(true));
			worker.execute();
		}
	}

	public void wirteDataToSource() {
		Message message = new Message("Đang xử lý...");
		SwingWorker<Void, Void> worker = new SwingWorker<>() {
			@Override
			protected Void doInBackground() {
				try {
					doWriteDataToSource();
				} catch (Exception e) {
					message.setMessage("Có lỗi xảy ra. Vui lòng thử lại !");
				}
				return null;
			}

			@Override
			protected void done() {
				SwingUtilities.invokeLater(() -> message.dispose());
			}
		};

		SwingUtilities.invokeLater(() -> message.setVisible(true));
		worker.execute();
	}

	private void doWriteDataToSource() throws Exception {
		WritableWorkbook writableWorkbook = null;
		WritableCellFormat cellFormatFinalContent = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12));
		WritableCellFormat cellFormatRightContent = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12));
		WritableCellFormat cellFormatAmtRightContent = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12));
		WritableCellFormat cellFormatLeftContent = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12));
		WritableCellFormat cellFormatOther = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12));
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
		cellFormatOther.setAlignment(Alignment.RIGHT);
		cellFormatFinalContent.setVerticalAlignment(VerticalAlignment.CENTRE);
		cellFormatRightContent.setVerticalAlignment(VerticalAlignment.CENTRE);
		cellFormatAmtRightContent.setVerticalAlignment(VerticalAlignment.CENTRE);
		cellFormatLeftContent.setVerticalAlignment(VerticalAlignment.CENTRE);
		cellFormatOther.setVerticalAlignment(VerticalAlignment.CENTRE);

		if (data.get(0).getClass().getSimpleName().equals("order")) {
			order order = (order) data.get(0);
			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			Workbook workbook = Workbook.getWorkbook(new File(source), encode);
			writableWorkbook = Workbook.createWorkbook(new File(source), workbook);
			WritableSheet sheet = writableWorkbook.getSheet(0);
			while (sheet.getRows() > 10 == true) {
				sheet.removeRow(sheet.getRows() - 1);
			}
			Label sophieu = new Label(6, 0, order.getId(), cellFormatOther);
			Label ngay = new Label(6, 2, order.getDate(), cellFormatOther);
			Label khachhang = new Label(0, 5, "Khách hàng: " + order.getCustomer().getName());
			Label sdt_khachhang = new Label(6, 5, order.getCustomer().getPhoneNumber(), cellFormatOther);
			Label diachi_khachhang = new Label(0, 6, "Địa chỉ: " + order.getCustomer().getAddress());
			Label nvkd = new Label(6, 6, order.getStaff().getName(), cellFormatOther);
			sheet.addCell(sophieu);
			sheet.addCell(ngay);
			sheet.addCell(khachhang);
			sheet.addCell(sdt_khachhang);
			sheet.addCell(diachi_khachhang);
			sheet.addCell(nvkd);
			List<product> products = order.getProducts();
			int k = 0;
			for (product product : products) {
				sheet.addCell(new Label(0, 10 + k, Integer.toString(k + 1), cellFormatLeftContent));
				sheet.addCell(new Label(1, 10 + k, product.getId(), cellFormatLeftContent));
				sheet.addCell(new Label(2, 10 + k, product.getName(), cellFormatLeftContent));
				sheet.addCell(new Label(3, 10 + k, product.getUnit(), cellFormatLeftContent));
				sheet.addCell(new Label(4, 10 + k, Integer.toString(product.getQuantity()), cellFormatRightContent));
				sheet.addCell(new Label(5, 10 + k, new float_1(product.getPrice()).tostring(), cellFormatRightContent));
				sheet.addCell(
						new Label(6, 10 + k, new float_1(product.getAmount()).tostring(), cellFormatAmtRightContent));
				sheet.setRowView(10 + k, 20 * 20);
				k++;
			}

			sheet.addCell(new Label(5, 10 + k, "Cộng tiền:", cellFormatOther));
			sheet.addCell(new Label(5, 10 + k + 1, "Chiết khấu:", cellFormatOther));
			sheet.addCell(new Label(5, 10 + k + 2, "Tiền giảm:", cellFormatOther));
			sheet.addCell(new Label(5, 10 + k + 3, "Tổng tiền:", cellFormatOther));
			sheet.setRowView(5, 20 * 20);
			sheet.setRowView(6, 20 * 20);
			sheet.setRowView(10 + k, 20 * 20);
			sheet.setRowView(10 + k + 1, 20 * 20);
			sheet.setRowView(10 + k + 2, 20 * 20);
			sheet.setRowView(10 + k + 3, 20 * 20);
			sheet.addCell(new Label(6, 10 + k, new float_1(order.getOrderValue()).tostring(), cellFormatFinalContent));
			sheet.addCell(new Label(6, 10 + k + 1, order.getDiscountRate(), cellFormatFinalContent));
			sheet.addCell(
					new Label(6, 10 + k + 2, new float_1(order.getDiscount()).tostring(), cellFormatFinalContent));
			sheet.addCell(
					new Label(6, 10 + k + 3, new float_1(order.getNetRevenue()).tostring(), cellFormatFinalContent));

			sheet.addCell(new Label(3, 16 + k, "Người giao hàng", cellFormatOther));
			sheet.addCell(new Label(3, 17 + k, "(ký, họ tên)", cellFormatOther));
			sheet.addCell(new Label(6, 16 + k, "Người lập phiếu", cellFormatOther));
			sheet.addCell(new Label(6, 17 + k, "(ký, họ tên)", cellFormatOther));
			sheet.addCell(new Label(6, 19 + k, "VÕ VĂN BẢY", cellFormatOther));
		} else {
			@SuppressWarnings("unchecked")
			List<Object> ordersMasterData = (List<Object>) data.get(0);
			@SuppressWarnings("unchecked")
			List<order> orders = (List<order>) data.get(1);

			WorkbookSettings encode = new jxl.WorkbookSettings();
			encode.setEncoding("ISO-8859-1");
			Workbook workbook = Workbook.getWorkbook(new File(source), encode);
			writableWorkbook = Workbook.createWorkbook(new File(source), workbook);
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
					sheet.addCell(new Label(0, 3 + k, Integer.toString(k + 1)));
					sheet.addCell(new Label(1, 3 + k, order.getDate()));
					sheet.addCell(new Label(2, 3 + k, order.getId()));
					sheet.addCell(new Label(3, 3 + k, order.getCustomer().getName()));
					sheet.addCell(new Label(4, 3 + k, new float_1(order.getOrderValue()).tostring()));
					revenue += order.getOrderValue();
					sheet.addCell(new Label(5, 3 + k, new float_1(order.getDiscount()).tostring()));
					discount += order.getDiscount();
					sheet.addCell(new Label(6, 3 + k, new float_1(order.getNetRevenue()).tostring()));
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
		}
		writableWorkbook.write();
		writableWorkbook.close();
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
