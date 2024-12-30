package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jxl.*;
import jxl.write.*;
import view.subView.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ImportEmployeeDataListener implements ActionListener {

    private static final String DEST_FILE_PATH = "database/database.xls";
    private static final String DEST_SHEET_NAME = "nhanvien";

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "Không có file nào được chọn!");
            return;
        }

        File sourceFile = fileChooser.getSelectedFile();

        if (!sourceFile.getName().toLowerCase().endsWith(".csv")) {
            JOptionPane.showMessageDialog(null, "File không đúng định dạng! Vui lòng chọn file .csv.");
            return;
        }

        Message message = new Message("Đang nhập dữ liệu...");
        message.setVisible(true);

        Thread messageThread = new Thread(() -> {
            WorkbookSettings encode = new jxl.WorkbookSettings();
            encode.setEncoding("ISO-8859-1");
            Workbook destWorkbook = null;
            WritableWorkbook writableDestWorkbook = null;
            try {
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
                    if (data.length < 2) continue;

                    String employeeName = data[0].trim();
                    String phoneNumber = data[1].trim();

                    if (!existingPhoneNumbers.contains(phoneNumber)) {
                        WritableCell nameCell = new Label(0, destRowNum, employeeName);
                        WritableCell phoneCell = new Label(1, destRowNum, phoneNumber);
                        destSheet.addCell(nameCell);
                        destSheet.addCell(phoneCell);

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
