
package model.function;

import java.io.File;
import com.aspose.cells.Workbook;

public class ExcelToPdfExporter {
	public void exportExcelToPdf(String sourceFilePath, String destinationFilePath) throws Exception {
		File sourceFile = new File(sourceFilePath);
		Workbook workbook = new Workbook(sourceFile.getAbsolutePath());
		workbook.save(destinationFilePath);

	}
}
