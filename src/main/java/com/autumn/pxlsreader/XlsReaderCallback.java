package com.autumn.pxlsreader;

public interface XlsReaderCallback {

	void newSheet(String sheetName);
	void newRow(int row);
	void newCell(Object value, int row, int col);
	void endRow(int row);
	void endFile();
}
