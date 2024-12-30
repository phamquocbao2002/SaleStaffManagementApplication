package model.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class file_exporter {
	private String source;

	public file_exporter(String source) {
		this.source = source;
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
		if (destination != null) {
			try {
				Files.copy(Path.of(source), Path.of(destination), StandardCopyOption.REPLACE_EXISTING);
				JOptionPane.showMessageDialog(null, "Xuất dữ liệu thành công");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Xuất dữ liệu thất bại: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void saveAsPdf() {
		String destination = chooseDestinationFile("pdf", "PDF Document");
		if (destination != null) {
			try (FileInputStream fis = new FileInputStream(source);
					Workbook workbook = new HSSFWorkbook(fis);
					FileOutputStream fos = new FileOutputStream(destination)) {
				
				new ExcelToPdfExporter().exportExcelToPdf(source, destination);

				JOptionPane.showMessageDialog(null, "Xuất dữ liệu thành công");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Xuất dữ liệu thất bại: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
