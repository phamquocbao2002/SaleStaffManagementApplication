
package model.function;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ExcelToPdfExporter {
	private static final String fontPath = "database//ArialUnicodeMS.ttf";
	public void exportExcelToPdf(String sourceFile, String destinationFile) {
		try (FileInputStream fis = new FileInputStream(sourceFile);
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				FileOutputStream fos = new FileOutputStream(destinationFile)) {

			Document pdfDoc = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(pdfDoc, fos);
			pdfDoc.open();

			BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font defaultFont = new Font(baseFont, 10, Font.NORMAL);

			HSSFSheet sheet = workbook.getSheetAt(0);
			int maxColumns = 7;
			PdfPTable table = new PdfPTable(maxColumns);
			table.setWidthPercentage(100);
			float[] columnWidths = calculateColumnWidths(sheet, maxColumns);
			table.setWidths(columnWidths);		

			for (int r = 0; r <= sheet.getLastRowNum(); r++) {
				HSSFRow row = sheet.getRow(r);
				
				for (int c = 0; c < maxColumns; c++) {
					HSSFCell cell = row != null ? row.getCell(c) : null;
					PdfPCell pdfCell = createPdfCellFromExcelCell(cell, baseFont, defaultFont, workbook);
					
					if (row == null) {
						pdfCell.setFixedHeight(20);
					}
					if (r == 4) { 
					    pdfCell.setColspan(7);
					    pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					    pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					    pdfCell.setNoWrap(true);
					    pdfCell.setFixedHeight(40); 
					    table.addCell(pdfCell);
					    break;
					} else {
					    table.addCell(pdfCell);
					}
				}

			}
			pdfDoc.add(table);
			pdfDoc.close();
			System.out.println("Export thành công: " + destinationFile);
		} catch (Exception e) {
			System.err.println("Lỗi khi xuất PDF: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static PdfPCell createPdfCellFromExcelCell(HSSFCell cell, BaseFont baseFont, Font defaultFont,
			Workbook workbook) {
		PdfPCell pdfCell;
		if (cell != null) {
			CellStyle cellStyle = cell.getCellStyle();
			String cellValue = getCellValue(cell);
			HSSFFont font1 = (HSSFFont) workbook.getFontAt(cellStyle.getFontIndex());
			Font font = new Font(baseFont, font1.getFontHeightInPoints());
			pdfCell = new PdfPCell(new Phrase(cellValue, font));
			pdfCell.setHorizontalAlignment(getHorizontalAlignment(cellStyle.getAlignmentEnum()));
			pdfCell.setVerticalAlignment(getVerticalAlignment(cellStyle.getVerticalAlignmentEnum()));
			pdfCell.setBorderWidthLeft(cellStyle.getBorderLeftEnum() != BorderStyle.NONE ? 1 : 0);
			pdfCell.setBorderWidthRight(cellStyle.getBorderRightEnum() != BorderStyle.NONE ? 1 : 0);
			pdfCell.setBorderWidthTop(cellStyle.getBorderTopEnum() != BorderStyle.NONE ? 1 : 0);
			pdfCell.setBorderWidthBottom(cellStyle.getBorderBottomEnum() != BorderStyle.NONE ? 1 : 0);
			List<Integer> cellIdx = Arrays.asList(cell.getColumnIndex(), cell.getRowIndex());
			if (Arrays.asList(Arrays.asList(0,0), Arrays.asList(0,1), Arrays.asList(0,2), 
					Arrays.asList(0,5), Arrays.asList(0,6), Arrays.asList(0,7),
					Arrays.asList(1,5), Arrays.asList(1,6), Arrays.asList(1,7), 
					Arrays.asList(1,8), Arrays.asList(0,4), Arrays.asList(0,7), Arrays.asList(5,6)).contains(cellIdx) || cellIdx.get(0) == 3) {
				pdfCell.setNoWrap(true);
			} else {
				pdfCell.setNoWrap(false);
			}

		} else {
			pdfCell = new PdfPCell(new Phrase("", defaultFont));
			pdfCell.setBorder(Rectangle.NO_BORDER);
		}

		return pdfCell;
	}

	private static float[] calculateColumnWidths(HSSFSheet sheet, int maxColumns) {
		float[] columnWidths = new float[maxColumns];

		for (int c = 0; c < maxColumns; c++) {
			columnWidths[c] = sheet.getColumnWidth(c) / 256f;
		}
		return columnWidths;
	}


	private static String getCellValue(HSSFCell cell) {
		switch (cell.getCellTypeEnum()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			}
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		default:
			return "";
		}
	}

	private static int getHorizontalAlignment(HorizontalAlignment alignment) {
		switch (alignment) {
		case CENTER:
			return Element.ALIGN_CENTER;
		case RIGHT:
			return Element.ALIGN_RIGHT;
		default:
			return Element.ALIGN_LEFT;
		}
	}

	private static int getVerticalAlignment(VerticalAlignment alignment) {
		switch (alignment) {
		case CENTER:
			return Element.ALIGN_MIDDLE;
		case TOP:
			return Element.ALIGN_TOP;
		case BOTTOM:
			return Element.ALIGN_BOTTOM;
		default:
			return Element.ALIGN_MIDDLE;
		}
	}
}
