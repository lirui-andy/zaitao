package com.yichang.uep.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.yichang.uep.dto.EventQueryVO;
import com.yichang.uep.model.YZtry;
import com.yichang.uep.repo.ZtryRepo;
import com.yichang.uep.utils.StringUtils;

@Component
public class ZtryManageImpl implements ZtryManage {

	@Autowired
	ZtryRepo ztryRepo;
	

	//组装查询条件数组
	private List<Predicate> commenSepc(final EventQueryVO event, Root<YZtry> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if(event == null){
			return predicates;
		}
		
		//身份证号
		if(!StringUtils.isBlank( event.getIdNum()))
			predicates.add( cb.like(root.get("sfzh"), 
					"%"+StringUtils.trimAllWhitespace(event.getIdNum())+"%") );
		//姓名
		if(!StringUtils.isBlank( event.getName()))
			predicates.add(cb.like(root.get("xm"), 
					"%"+StringUtils.trimAllWhitespace(event.getName())+"%" ));
		return predicates;
	}
	
	//拼装普通查询Specification
	private Specification<YZtry> ztrySpec(final EventQueryVO event){
		Specification<YZtry> spec = new Specification<YZtry>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<YZtry> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = commenSepc(event, root, query, cb);
				return query.where(predicates.toArray(new Predicate[]{} )).getRestriction();
			}
		};
		return spec;
	}
	
	private PageRequest pageRequest(Integer pageNum){
		if(pageNum == null ) pageNum = 0;
		return PageRequest.of(pageNum, 20);
	}
	

	//按条件查询
	@Override
	public Page<YZtry> findZtry(EventQueryVO event,Integer pageNum) {	
		return ztryRepo.findAll( ztrySpec(event), pageRequest(pageNum));
	}


}
