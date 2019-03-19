package com.autumn.pxlsreader;

public class NoopReaderCallback implements XlsReaderCallback{

	public void newSheet(String sheetName) {
		System.out.println("Sheet: " + sheetName);
	}

	public void newRow(int row) {
		System.out.println("Row start:"+row);
	}

	public void newCell(Object value, int row, int col) {
		System.out.println("Cell(" + row + ", " + col + "):" + value);
	}

	public void endRow(int row) {
		System.out.println("Row end:"+row);
		
	}

	@Override
	public void endFile() {
		System.out.println("File end.");
	}
	
}
