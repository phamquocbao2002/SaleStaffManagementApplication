package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import utils.file_exporter;

public class fileExportActionListener implements ActionListener {

	public file_exporter fe;
	private String source;
	private String fileType;

	public fileExportActionListener(String source, List<Object> data, String fileType) {
		super();
		this.source = source;
		this.fileType = fileType;
		this.fe = new file_exporter(this.source, data);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		fe.wirteDataToSource();
		if (fileType.equals("xls")) {
			fe.saveAsXls();
		} else {
			fe.saveAsPdf();
		}
	}

}
