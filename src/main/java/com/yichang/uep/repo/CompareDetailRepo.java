package com.yichang.uep.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YCompareDetail;

public interface CompareDetailRepo extends JpaRepository<YCompareDetail, Integer> {

	Page<YCompareDetail> findByBatchIdOrderByCompareDetailIdDesc(int compareDetailId, Pageable page);
}
