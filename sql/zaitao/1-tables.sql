drop table y_ztry;
drop table y_imp_crew;
drop table y_person;
drop table y_compare_batch;
drop table y_compare_detail;


CREATE TABLE `y_ztry` (
  `ztrybh` varchar(25) NOT NULL, 
  `xm` varchar(100) NOT NULL,
  `bmch` varchar(100) DEFAULT NULL,
  `xbdm` varchar(100) DEFAULT NULL,
  `mzdm` varchar(100) DEFAULT NULL,
  `csrq` varchar(100) DEFAULT NULL,
  `hjdz` varchar(100) DEFAULT NULL,
  `xzqhdm` varchar(100) DEFAULT NULL,
  `hjdz_dzmc` varchar(100) DEFAULT NULL,
  `xzz_xzqhdm` varchar(100) DEFAULT NULL,
  `xzz_dzmc` varchar(100) DEFAULT NULL,
  `sfzh` varchar(100) DEFAULT NULL,
  `cyzj` varchar(100) DEFAULT NULL,
  `sgxx` varchar(100) DEFAULT NULL,
  `asjbh` varchar(100) DEFAULT NULL,
  `tpsj` varchar(100) DEFAULT NULL,
  `tpfx_jyqk` varchar(100) DEFAULT NULL,
  `tjjbdm` varchar(100) DEFAULT NULL,
  `ztjj` varchar(100) DEFAULT NULL,
  `ztrylxdm` varchar(100) DEFAULT NULL,
  `ladw_gajgjgdm` varchar(100) DEFAULT NULL,
  `zbr_xm` varchar(100) DEFAULT NULL,
  `zbr_lxdh` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ztrybh`),
  KEY `idxztry_1` (`sfzh`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='追逃人员表';

truncate table `y_imp_crew`;
select count(1) from y_imp_crew;
select * from y_imp_crew limit 0, 100

create table `y_imp_crew`(
`imp_crew_id` int(11) NOT NULL AUTO_INCREMENT,
`ship_crew_id` int(11) DEFAULT NULL,
`crew_id` int(11) DEFAULT NULL,
`id_card` varchar(30) DEFAULT NULL,
`crewname` varchar(50) DEFAULT NULL,
`ship_id` int(11) DEFAULT NULL,
`onship_duty` varchar(10) DEFAULT NULL,
`crew_status` int(11) DEFAULT NULL,
`start_date` date DEFAULT NULL,
`end_date` date DEFAULT NULL,
`remark` varchar(300) DEFAULT NULL,
`operuser` varchar(30) DEFAULT NULL,
`opertime` datetime DEFAULT NULL,
`impuser` varchar(30) default null, 
`imptime` datetime default null, 
  PRIMARY KEY (`imp_crew_id`),
  KEY `idxcrew_1` (`id_card`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='导入船员表';

create table `y_person`(
`person_id` int(11) NOT NULL AUTO_INCREMENT,
`id_card` varchar(30) DEFAULT NULL,
`name` varchar(50) DEFAULT NULL,
`impuser` varchar(30) default null,
`imptime` datetime default null,
  PRIMARY KEY (`person_id`),
  KEY `idxperson_1` (`id_card`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='排查人员表';


create table `y_compare_batch`(
`batch_id` int(11) NOT NULL AUTO_INCREMENT,
`compare_time`  datetime default null,
`end_time` datetime default null,
`ztry_count` int DEFAULT NULL,
`matched_count` int default null,
PRIMARY KEY (`batch_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='对比批次表';

create table `y_compare_detail`(
`compare_detail_id` int(11) NOT NULL AUTO_INCREMENT,
`batch_id` int(11) NOT NULL,
`person_id` int(11) DEFAULT NULL,  /*y_person.person_id*/
  `ztrybh` varchar(25) NOT NULL, 
  `xm` varchar(100) NOT NULL,
  `xbdm` varchar(100) DEFAULT NULL,
  `xzz_dzmc` varchar(100) DEFAULT NULL,
  `sfzh` varchar(100) DEFAULT NULL,
  `tpsj` varchar(100) DEFAULT NULL,
  `zbr_xm` varchar(100) DEFAULT NULL,
  `zbr_lxdh` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`compare_detail_id`),
  KEY `idxcomdetail_1` (`batch_id`),
  KEY `idxcomdetail_2` (`sfzh`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='对比结果表';


create view `v_compare_person` as
select b.person_id, a.ztrybh, a.xm, a.xbdm, a.xzz_dzmc, a.sfzh, a.tpsj, a.zbr_xm, a.zbr_lxdh
from  `y_ztry` a, `y_person` b
where a.`sfzh` = b.`id_card`
;


create view `v_compare_crew` as
select 0 as person_id, a.ztrybh, a.xm, a.xbdm, a.xzz_dzmc, a.sfzh, a.tpsj, a.zbr_xm, a.zbr_lxdh
from  `y_ztry` a, `y_imp_crew` b
where a.`sfzh` = b.`id_card`
;



