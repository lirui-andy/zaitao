package com.yichang.uep.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yichang.uep.model.YZtry;

/**
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("ztry")
public class ZtryController extends BaseController {

	@GetMapping("/search")
	@ResponseBody
	public List<YZtry> search(){
		
		return Collections.EMPTY_LIST;
	}
}
