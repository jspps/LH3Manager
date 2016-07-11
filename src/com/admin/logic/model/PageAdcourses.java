package com.admin.logic.model;

import java.util.List;
import java.util.Map;

import com.admin.db.bean.Adcourses;
import com.admin.db.entity.AdcoursesEntity;
import com.bowlong.util.page.APage;

/**
 * 分页对象-代理商
 * 
 * @author hxw
 * 
 */
public class PageAdcourses extends APage<Adcourses> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return AdcoursesEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Adcourses> getList(Map<String, Object> params, int page,
			int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			return AdcoursesEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
		}
		return null;
	}
}
