package com.yichang.uep.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.dto.EventQueryVO;
import com.yichang.uep.dto.MatchBatchQueryVO;
import com.yichang.uep.dto.datatables.PageAdapter;
import com.yichang.uep.dto.datatables.RequestAdapter;
import com.yichang.uep.model.YCompareBatch;
import com.yichang.uep.model.YCompareDetail;
import com.yichang.uep.model.YZtry;
import com.yichang.uep.repo.CompareBatchRepo;
import com.yichang.uep.repo.CompareDetailRepo;
import com.yichang.uep.service.ZtryManage;
import com.yichang.uep.utils.DateUtils;

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
	@PostMapping(path="/dbjg/search", consumes={"text/plain", "application/json"})
	@ResponseBody
	public PageAdapter<YCompareBatch>  dbjg(@RequestBody RequestAdapter<MatchBatchQueryVO> queryParam) {
		Date today = DateUtils.truncate(Calendar.getInstance().getTime());
		Date startDay = DateUtils.addDay(today, -30); //默认开始日期为30天前0点
		Date endDay = DateUtils.addDay(today, 1);//默认结束日期为明天0点
		
		if(queryParam != null && queryParam.getCondition()!=null) {
			//如果指定日期范围，则根据日期范围查询
			String dateStr = queryParam.getCondition().getDateRange();
			if(dateStr != null && dateStr.length() > 20) {
				startDay = DateUtils.parse(dateStr.substring(0, 10));
				endDay = DateUtils.parse(dateStr.substring(dateStr.length() - 10));
			}
		}
		Page<YCompareBatch> page = compareBatchRepo.findInDate(startDay, endDay, 
				PageRequest.of(queryParam.getPage(), 
						queryParam.getLength() < 1 ? 1 : queryParam.getLength(), 
						Direction.DESC, 
						"batchId"));
		return PageAdapter.create(page, queryParam.getDraw());
	}

	@PostMapping(path="/dbjg/details", consumes={"text/plain", "application/json"})
	@ResponseBody
	public PageAdapter<YCompareDetail>  dbjgDetails(@RequestBody RequestAdapter<MatchBatchQueryVO> queryParam) {
		
		Page<YCompareDetail> page = compareDetailRepo.findByBatchIdOrderByCompareDetailIdDesc(queryParam.getCondition().getBatchId(), 
				PageRequest.of(queryParam.getPage(), 
						queryParam.getLength() < 1 ? 1 : queryParam.getLength()));
		return PageAdapter.create(page, queryParam.getDraw());
	}
}
