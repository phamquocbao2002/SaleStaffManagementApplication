package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import jxl.*;
import jxl.write.*;
import view.subView.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportCustomerDataListener implements ActionListener {

    private static final String DEST_FILE_PATH = "database/database.xls";
    private static final String DEST_SHEET_NAME = "khachhang";

    @Override
    public void actionPerformed(ActionEvent e) {
        Message message = new Message("Đang nhập dữ liệu...");
        message.setVisible(true);

        Thread messageThread = new Thread(() -> {
            WorkbookSettings encode = new jxl.WorkbookSettings();
            encode.setEncoding("ISO-8859-1");
            Workbook destWorkbook = null;
            WritableWorkbook writableDestWorkbook = null;
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn file CSV nguồn");
                int result = fileChooser.showOpenDialog(null);

                if (result != JFileChooser.APPROVE_OPTION) {
                    message.setMessage("Không chọn file nguồn!");
                    return;
                }

                File sourceFile = fileChooser.getSelectedFile();

                if (!sourceFile.getName().toLowerCase().endsWith(".csv")) {
                    message.setMessage("File không đúng định dạng!");
                    return;
                }

                File destFile = new File(DEST_FILE_PATH);
                destWorkbook = Workbook.getWorkbook(destFile, encode);
                writableDestWorkbook = Workbook.createWorkbook(destFile, destWorkbook);
                WritableSheet destSheet = writableDestWorkbook.getSheet(DEST_SHEET_NAME);

                Set<String> existingPhoneNumbers = new HashSet<>();
                for (int row = 0; row < destSheet.getRows(); row++) {
                    Cell phoneCell = destSheet.getCell(1, row);
                    if (phoneCell != null && !phoneCell.getContents().trim().isEmpty()) {
                        existingPhoneNumbers.add(phoneCell.getContents().trim());
                    }
                }

                BufferedReader csvReader = new BufferedReader(new FileReader(sourceFile));
                String line;
                int destRowNum = destSheet.getRows();

                while ((line = csvReader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length < 4) continue;

                    String customerName = data[0].trim();
                    String phoneNumber = data[1].trim();
                    String address = data[2].trim();
                    String employeeInCharge = data[3].trim();

                    if (!existingPhoneNumbers.contains(phoneNumber)) {
                        destSheet.addCell(new Label(0, destRowNum, customerName));
                        destSheet.addCell(new Label(1, destRowNum, phoneNumber));
                        destSheet.addCell(new Label(2, destRowNum, address));
                        destSheet.addCell(new Label(3, destRowNum, employeeInCharge));

                        existingPhoneNumbers.add(phoneNumber);
                        destRowNum++;
                    }
                }

                csvReader.close();
                writableDestWorkbook.write();
                message.setMessage("Nhập dữ liệu thành công!");
            } catch (Exception ex) {
                ex.printStackTrace();
                message.setMessage("Nhập dữ liệu thất bại!");
            } finally {
                try {
                    if (writableDestWorkbook != null) writableDestWorkbook.close();
                    if (destWorkbook != null) destWorkbook.close();
                } catch (WriteException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        messageThread.start();
    }
}
