package com.yichang.uep.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YCompareBatch;

public interface CompareBatchRepo extends JpaRepository<YCompareBatch, Integer> {

	Optional<YCompareBatch> findTopByOrderByBatchIdDesc();
}
