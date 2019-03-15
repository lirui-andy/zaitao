package com.yichang.uep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yichang.uep.model.YZtry;

public interface ZtryRepo extends JpaRepository<YZtry, String>, JpaSpecificationExecutor<YZtry> {

//	List<YZtry> findBy
	
}
