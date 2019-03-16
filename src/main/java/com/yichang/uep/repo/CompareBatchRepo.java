package com.yichang.uep.repo;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yichang.uep.model.YCompareBatch;

public interface CompareBatchRepo extends JpaRepository<YCompareBatch, Integer> {

	Optional<YCompareBatch> findTopByOrderByBatchIdDesc();
	
	@Query("select c from YCompareBatch c where c.compareTime between ?1 and ?2")
	Page<YCompareBatch> findInDate(Date beginDate, Date endDate, Pageable page);
}
