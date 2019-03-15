package com.yichang.uep.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.dto.EventQueryVO;
import com.yichang.uep.dto.datatables.PageAdapter;
import com.yichang.uep.dto.datatables.RequestAdapter;
import com.yichang.uep.model.YCompareBatch;
import com.yichang.uep.model.YCompareDetail;
import com.yichang.uep.model.YZtry;
import com.yichang.uep.repo.CompareBatchRepo;
import com.yichang.uep.repo.CompareDetailRepo;
import com.yichang.uep.service.ZtryManage;

/**
 * 
 * @author Lirui
 *
 */

@Controller
public class ZtryController extends BaseController {
	
	@Autowired
	ZtryManage ztryManange;

	@Autowired
	CompareBatchRepo compareBatchRepo;
	@Autowired
	CompareDetailRepo compareDetailRepo;
	/**
	 * 追逃人员查询
	 * @return
	 */
	@GetMapping("/ztry/list")
	public String list() {
		return "ztry";
	}

	@PostMapping(path="/ztry/search", consumes={"text/plain", "application/json"})
	@ResponseBody
	public PageAdapter<YZtry> search(@RequestBody RequestAdapter<EventQueryVO> searchParam){
		Page<YZtry> page = ztryManange.findZtry(searchParam.getCondition(), searchParam.getPage());
		return PageAdapter.create(page, searchParam.getDraw());
	}
		
	/**
	 * 最新对比结果
	 * @return
	 */
	@GetMapping("/dbjg/zx")
	public String zxdbjg(Model model) {
		model.addAttribute("list", Collections.EMPTY_LIST);
		Optional<YCompareBatch> batch = compareBatchRepo.findTopByOrderByBatchIdDesc();

		if(batch.isPresent()) {
			YCompareDetail example = new YCompareDetail();
			example.setBatchId(batch.get().getBatchId());
			List<YCompareDetail> details = compareDetailRepo.findAll(Example.of(example));

			model.addAttribute("batch", batch.get());
			model.addAttribute("details", details);
		} 
		return "dbjg-zx";
	}
	
	/**
	 * 历史对比结果
	 * @param model
	 * @return
	 */
	@GetMapping("/dbjg/list")
	public String dbjg(Model model) {
		model.addAttribute("compare", "123");
		model.addAttribute("list", Collections.EMPTY_LIST);
		return "dbjg-list";
	}
}
