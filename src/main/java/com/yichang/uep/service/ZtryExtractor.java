package com.yichang.uep.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yichang.uep.exception.ImportException;
import com.yichang.uep.model.YZtry;

/**
 * 在逃数据库文件解析器
 * 
 * @author lirui
 *
 */
public class ZtryExtractor {
	Logger logger = LoggerFactory.getLogger(ZtryExtractor.class);
	String[] titles = new String[] { "ZTRYBH", "XM", "BMCH", "XBDM", "MZDM", "CSRQ", "HJDZ_XZQHDM", "HJDZ_DZMC",
			"XZZ_XZQHDM", "XZZ_DZMC", "SFZH", "CYZJ", "SGXX", "SGSX", "ASJBH", "TPSJ", "TPFX_JYQK", "TJJBDM", "ZTJJ",
			"ZTRYLXDM", "LADW_GAJGJGDM", "ZBR_XM", "ZBR_LXDH" };

	public ZtryExtractor() {
	}

	public YZtry extractObj(String contentLine) throws ImportException {
		if (contentLine == null || contentLine.isEmpty() || contentLine.length() < 50) {
			throw new ImportException("行内容太短.");
		}

		String[] line = contentLine.substring(1, contentLine.length() - 1).split("\",\"");
		if (line.length < titles.length) {
			throw new ImportException("行内容与表头不一致.");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < titles.length; i++) {
			map.put(titles[i], line[i]);
		}
//sgxx - sgsx
		YZtry ztry = new YZtry();
		ztry.setZtrybh(map.get("ZTRYBH"));
		ztry.setXm(map.get("XM"));
		ztry.setBmch(map.get("BMCH"));
		ztry.setXbdm(map.get("XBDM"));
		ztry.setMzdm(map.get("MZDM"));
		ztry.setCsrq(map.get("CSRQ"));
		ztry.setHjdzDzmc(map.get("HJDZ_XZQHDM"));
		ztry.setHjdzDzmc(map.get("HJDZ_DZMC"));
		ztry.setXzzXzqhdm(map.get("XZZ_XZQHDM"));
		ztry.setXzzDzmc(map.get("XZZ_DZMC"));
		ztry.setSfzh(map.get("SFZH"));
		ztry.setCyzj(map.get("CYZJ"));
		ztry.setSgxx(map.get("SGXX"));
		ztry.setSgxx(map.get("SGSX"));
		ztry.setAsjbh(map.get("ASJBH"));
		ztry.setTpsj(map.get("TPSJ"));
		ztry.setTpfxJyqk(map.get("TPFX_JYQK"));
		ztry.setTjjbdm(map.get("TJJBDM"));
		ztry.setZtjj(map.get("ZTJJ"));
		ztry.setZtrylxdm(map.get("ZTRYLXDM"));
		ztry.setLadwGajgjgdm(map.get("LADW_GAJGJGDM"));
		ztry.setZbrXm(map.get("ZBR_XM"));
		ztry.setZbrLxdh(map.get("ZBR_LXDH"));

		if(ztry.getZtrybh() == null || ztry.getZtrybh().length() == 0) {
			throw new ImportException("缺少在逃人员编号.");
		}
		if(ztry.getSfzh() == null || ztry.getSfzh().length() == 0) {
			throw new ImportException("缺少身份证号.");
		}
		return ztry;
	}

}
