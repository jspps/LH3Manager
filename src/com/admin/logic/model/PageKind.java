package com.admin.logic.model;

import java.util.List;
import java.util.Map;

import com.admin.db.bean.Kind;
import com.admin.db.entity.KindEntity;
import com.bowlong.util.page.APage;

/**
 * 分页对象-大套餐
 * 
 * @author  
 * 
 */
public class PageKind extends APage<Kind> {

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return KindEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Kind> getList(Map<String, Object> params, int page,
			int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			return KindEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
		}
		return null;
	}
}
