package com.yichang.uep.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

import com.yichang.uep.dto.ImportFaildLine;
import com.yichang.uep.model.YZtry;
import com.yichang.uep.repo.ZtryRepo;
import com.yichang.uep.service.ZtryExtractor;

/**
 * 
 * @author Lirui
 *
 */

@Controller
public class ImportZtryController extends BaseController {

	Logger logger = LoggerFactory.getLogger(ImportZtryController.class);
	
	@Autowired
	ZtryRepo ztryRepo;
	
	@GetMapping("/import-ztry")
	public String view() {
		return "import-ztry";
	}
	/**
	 * 追逃数据库上传
	 * @param file
	 * @return
	 */
	@PostMapping("/import-ztry")
	public String upload(@RequestParam("file") MultipartFile[] file, Model model){
		List<ImportFaildLine> faildList = new ArrayList<ImportFaildLine>();
		Stream.of(file)
		.filter(f -> (f != null && f.getSize() > 0 ) )
		.forEach(f -> {
				long size = f.getSize();
				String name = f.getOriginalFilename();
				logger.info(name+"  ,size="+size);
				try {
					faildList.addAll(importZtry(f.getInputStream()));
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
		});
		model.addAttribute("faildList", faildList);
		return "import-ztry";
	}
	
	private List<ImportFaildLine> importZtry(InputStream fileIn) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn));
		String line = null;
		List<ImportFaildLine> faildList = new ArrayList<ImportFaildLine>();
		ZtryExtractor extractor = new ZtryExtractor();
		int lineNum = 1;
		while( (line = reader.readLine()) != null) {
			if(line.indexOf("ZTRYBH") < 0) {
				try {
					YZtry ztry = extractor.extractObj(line);
					ztryRepo.save(ztry);
				} catch (Exception e) {
					faildList.add(new ImportFaildLine(lineNum, e.getMessage(), line));
				}
			}
			lineNum++;
		}
		return faildList;
	}
}
