package com.autumn.pxlsreader;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.CellRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 简易xls格式读取器。
 * 利用apache poi库，采用SAX方式解析。
 * 
 * @author lirui
 *
 */
public class SimpleXlsReader implements HSSFListener {
	Workbook workbook;
	XlsReaderCallback callback;
	SSTRecord sstrec;
	FormatTrackingHSSFListener formatListener;

	boolean reachedRowEnd = true;

	LinkedList<String> sheetNames = new LinkedList<String>();

	private SimpleXlsReader() {
	}

	/**
	 * 获取一个新的解析器实例
	 * @return
	 */
	public static SimpleXlsReader newInstance() {
		return new SimpleXlsReader();
	}

	/**
	 * 通知解析器开始处理xls文件。调用时需要指定ReaderCallback作为内容处理者。如果没有指定，
	 * 则默认使用一个内置为任何业务逻辑的callback实例{@link NoopReaderCallback}，仅用于通过日志打印读取到的内容。
	 * @param in
	 * @param callback
	 * @throws IOException
	 */
	public void readXls(InputStream in, XlsReaderCallback callback) throws IOException {
		this.callback = callback;
		if (this.callback == null) {
			this.callback = new NoopReaderCallback();
		}
		// create a new org.apache.poi.poifs.filesystem.Filesystem
		POIFSFileSystem poifs = new POIFSFileSystem(in);
		// get the Workbook (excel part) stream in a InputStream
		InputStream din = poifs.createDocumentInputStream("Workbook");
		
		MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
		formatListener = new FormatTrackingHSSFListener(listener);

		// construct out HSSFRequest object
		HSSFRequest req = new HSSFRequest();
		// lazy listen for ALL records with the listener shown above
		req.addListenerForAllRecords(formatListener);
		// create our event factory
		HSSFEventFactory factory = new HSSFEventFactory();
		// process our events based on the document input stream
		factory.processEvents(req, din);
		callback.endFile();
		// once all the events are processed close our file input stream
		in.close();
		poifs.close();
		din.close();
	}

	public void processRecord(Record record) {
		switch (record.getSid()) {
		// the BOFRecord can represent either the beginning of a sheet or the workbook
		case BOFRecord.sid:
			BOFRecord bof = (BOFRecord) record;
			if (bof.getType() == BOFRecord.TYPE_WORKBOOK) {
				// System.out.println("Encountered workbook");
			} else if (bof.getType() == BOFRecord.TYPE_WORKSHEET) {
				// System.out.println("Encountered sheet reference");
				callback.newSheet(sheetNames.poll());
			}
			break;
		case BoundSheetRecord.sid:
			BoundSheetRecord bsr = (BoundSheetRecord) record;
			// System.out.println("New sheet named: " + bsr.getSheetname());
			sheetNames.add(bsr.getSheetname());
			break;
		case SSTRecord.sid:
			// SSTRecords store a array of unique strings used in Excel.
			sstrec = (SSTRecord) record;
//			for (int k = 0; k < sstrec.getNumUniqueStrings(); k++) {
//				//System.out.println("String table value " + k + " = " + sstrec.getString(k));
//			}
			break;
		case RowRecord.sid:
//			RowRecord rowrec = (RowRecord) record;
//			 System.out.println("Row found, first column at " + rowrec.getFirstCol() + "last column at " + rowrec.getLastCol());
			break;
		case NumberRecord.sid:
			NumberRecord numrec = (NumberRecord) record;
			notifyIfNewRow(numrec);
			String strVal = formatListener.formatNumberDateCell(numrec);
			callback.newCell(strVal, numrec.getRow(), numrec.getColumn());
			// System.out.println("Number cell: " + numrec.getValue() + " at r= " +
			// numrec.getRow() + ", c= " + numrec.getColumn());
			break;
		case LabelSSTRecord.sid:
			LabelSSTRecord lrec = (LabelSSTRecord) record;
			notifyIfNewRow(lrec);
			callback.newCell(sstrec.getString(lrec.getSSTIndex()), lrec.getRow(), lrec.getColumn());
			// System.out.println("String cell: " + sstrec.getString(lrec.getSSTIndex()) + "
			// at r= " + lrec.getRow() + ", c= " + lrec.getColumn());
			break;
		}
		if(record instanceof LastCellOfRowDummyRecord) {
			reachedRowEnd = true;
			try {
				callback.endRow(((LastCellOfRowDummyRecord) record).getRow());
			} catch (Exception e) {
			}
		}

	}

	private void notifyIfNewRow(CellRecord cell) {
		if (reachedRowEnd) {
			try {
				callback.newRow(cell.getRow());
			} catch (Exception e) {
			}
			reachedRowEnd = false;
		}
	}

	public void newSheet(String sheetName) {
		System.out.println("Sheet: " + sheetName);
	}

	public void newRow() {
		System.out.println("Row start");
	}

	public void newCell(Object value, int row, int col) {
		System.out.println("Cell(" + row + ", " + col + "):" + value);
	}

}
