package com.autumn.pxlsreader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello World!");
		FileInputStream in = new FileInputStream("/Users/lirui/Desktop/20190311/crew.xls");
		SimpleXlsReader.newInstance().readXls(in, null);
	}
}

class MyCallback implements XlsReaderCallback {

	String[] titles = new String[] {"", "SHIP_CREW_ID", "CREW_ID", "ID_CARD", "CREWNAME", "SHIP_ID", "ONSHIP_DUTY",
			"CREW_STATUS", "START_DATE", "END_DATE", "REMARK", "OPERUSER", "OPERTIME" };
	Map<String, Object> rowData;
	public void newSheet(String sheetName) {
		System.out.println(sheetName);
	}

	public void newRow(int row) {
		rowData = new HashMap<String, Object>();
	}

	public void newCell(Object value, int row, int col) {
		String title = titles[col];
		rowData.put(title, value);
	}

	public void endRow(int row) {
		System.out.println(rowData);
	}

	@Override
	public void endFile() {
		
	}

}
