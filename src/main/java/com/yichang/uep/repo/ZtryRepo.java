package com.yichang.uep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yichang.uep.model.YEvent;
import com.yichang.uep.model.YZtry;

public interface ZtryRepo extends JpaRepository<YZtry, String>, JpaSpecificationExecutor<YZtry> {

//	List<YZtry> findBy
	
	int countAll();
}
