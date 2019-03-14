package com.yichang.uep.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YPerson;

public interface PersonRepo extends JpaRepository<YPerson, Integer> {


}
