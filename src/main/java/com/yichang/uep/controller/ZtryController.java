package com.yichang.uep.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.yichang.uep.model.YZtry;
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
		model.addAttribute("compare", "123");
		model.addAttribute("list", Collections.EMPTY_LIST);
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
