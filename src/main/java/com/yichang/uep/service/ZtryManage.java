package com.yichang.uep.service;

import org.springframework.data.domain.Page;

import com.yichang.uep.dto.EventQueryVO;
import com.yichang.uep.model.YZtry;

public interface ZtryManage {

	public Page<YZtry> findZtry(EventQueryVO event, Integer pageNum);
	
}
