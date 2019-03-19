package com.yichang.uep.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.autumn.pxlsreader.SimpleXlsReader;
import com.autumn.pxlsreader.XlsReaderCallback;
import com.yichang.uep.dto.ImportFaildLine;
import com.yichang.uep.model.YImpCrew;
import com.yichang.uep.repo.CrewRepo;
import com.yichang.uep.utils.DateUtils;

/**
 * 
 * @author Lirui
 *
 */

@Controller
public class ImportCrewController extends BaseController {

	Logger logger = LoggerFactory.getLogger(ImportCrewController.class);
	
	@Autowired
	CrewRepo crewRepo;
	
	@GetMapping("/import-crew")
	public String view() {
		return "import-crew";
	}
	/**
	 * 船员数据库上传
	 * @param file
	 * @return
	 */
	@PostMapping("/import-crew")
	public String upload(@RequestParam("file") MultipartFile[] file, Model model){
		List<ImportFaildLine> faildList = new ArrayList<ImportFaildLine>();
		Stream.of(file)
		.filter(f -> (f != null && f.getSize() > 0 ) )
		.forEach(f -> {
				long size = f.getSize();
				String name = f.getOriginalFilename();
				logger.info(name+"  ,size="+size);
				try {
					faildList.addAll(importData(f.getInputStream()));
				} catch (IOException e) {
					faildList.add(new ImportFaildLine(0, "文件读取失败，请重试。"));
					logger.error(e.getMessage(), e);
				}
		});
		model.addAttribute("faildList", faildList);
		return "import-crew";
	}
	
	private List<ImportFaildLine> importData(InputStream fileIn) throws IOException{
		crewRepo.deleteAllInBatch();
		CrewImportCallback callback = new CrewImportCallback();
		SimpleXlsReader.newInstance().readXls(fileIn, callback);
		return callback.faildList;
//		return Collections.EMPTY_LIST;
	}

	class CrewImportCallback implements XlsReaderCallback {
		List<ImportFaildLine> faildList = new ArrayList<ImportFaildLine>();
		
		String[] titles = new String[] {"", "SHIP_CREW_ID", "CREW_ID", "ID_CARD", "CREWNAME", "SHIP_ID", "ONSHIP_DUTY",
				"CREW_STATUS", "START_DATE", "END_DATE", "REMARK", "OPERUSER", "OPERTIME" };
		Map<String, String> rowData;
		
		List<YImpCrew> crewCache = new ArrayList<YImpCrew>(500);

		int dataRowIndex = Integer.MAX_VALUE;
		boolean inDataRow = false;
		int successCount = 0;
		
		public void newSheet(String sheetName) {
			inDataRow = false;
			flushCrewCacheIfNeed();
		}
	
		public void newRow(int row) {
		}
	
		public void newCell(Object value, int row, int col) {
			if("SHIP_CREW_ID".equals(value.toString())) {
				dataRowIndex = row + 1;
			}
			inDataRow = row >= dataRowIndex;
			if(inDataRow) {
				if(rowData == null) {
					rowData = new HashMap<String, String>();
				}
				String title = titles[col];
				rowData.put(title, value.toString());
			}
		}
	
		public void endRow(int row) {
			if(inDataRow) {
				logger.debug("Crew:"+rowData.get("SHIP_CREW_ID"));
				try {
					cacheAndSaveData(translateRowData(rowData));
				} catch (Exception e) {
					faildList.add(new ImportFaildLine(row + 1, "存入数据库失败:"+e.getMessage()));
				}
			}
			rowData = null;
		}
		
		
		@Override
		public void endFile() {
			flushCrewCacheIfNeed();
		}

		private YImpCrew translateRowData(Map<String, String> map) {
			YImpCrew crew = new YImpCrew();
			crew.setShipCrewId(Integer.parseInt(map.get("SHIP_CREW_ID")));
			crew.setCrewId(Integer.parseInt(map.get("CREW_ID")));
			crew.setIdCard(map.get("ID_CARD"));
			crew.setCrewname(map.get("CREWNAME"));
			crew.setShipId(Integer.parseInt(map.get("SHIP_ID")));
			crew.setOnshipDuty(map.get("ONSHIP_DUTY"));
			crew.setCrewStatus(Integer.parseInt(map.get("CREW_STATUS")));
			crew.setStartDate(DateUtils.parse(map.get("START_DATE")));
			crew.setEndDate(DateUtils.parse(map.get("END_DATE")));
			crew.setRemark(map.get("REMARK"));
			crew.setOperuser(map.get("OPERUSER"));
			crew.setOpertime(DateUtils.parse(map.get("OPERTIME")));
			return crew;
		}
		
		private void cacheAndSaveData(YImpCrew crew) {
			crewCache.add(translateRowData(rowData));
			if(crewCache.size() >= 500) {
				flushCrewCacheIfNeed();
			}
		}
		
		private void flushCrewCacheIfNeed() {
			if(crewCache != null && crewCache.size() > 0) {
				crewRepo.saveAll(crewCache);
				successCount += crewCache.size();
				crewCache.clear();
			}
		}
	}
}
