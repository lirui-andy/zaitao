package com.yichang.uep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.dto.EventQueryVO;
import com.yichang.uep.dto.datatables.PageAdapter;
import com.yichang.uep.dto.datatables.RequestAdapter;
import com.yichang.uep.model.YZtry;
import com.yichang.uep.service.ZtryManage;

/**
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("ztry")
public class ZtryController extends BaseController {
	
	@Autowired
	ZtryManage ztryManange;
	
	@PostMapping(path="/search", consumes={"text/plain", "application/json"})
	@ResponseBody
	public PageAdapter<YZtry> search(@RequestBody RequestAdapter<EventQueryVO> searchParam){
		Page<YZtry> page = ztryManange.findZtry(searchParam.getCondition(), searchParam.getPage());
		return PageAdapter.create(page, searchParam.getDraw());
	}
}
