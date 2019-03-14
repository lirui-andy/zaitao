DELIMITER &&

CREATE  PROCEDURE  p_batch_compare()  
  MODIFIES SQL DATA  
  BEGIN  
      DECLARE var_batch_id  INT  DEFAULT 0;
      declare var_match_count int default 0;
      declare var_ztry_count int default 0;
      declare var_now datetime default now();
      
      select IFNULL(max(batch_id), 0) + 1 into var_batch_id from y_compare_batch;
      select count(1) into var_ztry_count from y_ztry;
      
      INSERT INTO y_compare_detail(batch_id, person_id, ztrybh, xm, xbdm, sfzh, xzz_dzmc, tpsj, zbr_xm, zbr_lxdh)
      select var_batch_id, person_id, ztrybh, xm, xbdm, sfzh, xzz_dzmc, tpsj, zbr_xm, zbr_lxdh
      from v_compare_person;
      
      INSERT INTO y_compare_detail(batch_id, person_id, ztrybh, xm, xbdm, sfzh, xzz_dzmc, tpsj, zbr_xm, zbr_lxdh)
      select var_batch_id, person_id, ztrybh, xm, xbdm, sfzh, xzz_dzmc, tpsj, zbr_xm, zbr_lxdh
      from v_compare_crew;
      
      select count(1) into var_match_count from y_compare_detail where var_batch_id = var_batch_id;
      
      INSERT INTO y_compare_batch(batch_id, compare_time, ztry_count, matched_count)
      values(var_batch_id, var_now, var_ztry_count, var_match_count);
       
  END &&
  
  DELIMITER ;