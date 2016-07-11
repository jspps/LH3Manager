package com.admin.logic.model;

import java.util.List;
import java.util.Map;

import com.admin.db.bean.Learnhub;
import com.admin.db.entity.LearnhubEntity;
import com.bowlong.util.page.APage;

/**
 * 分页对象-代理商
 * 
 * @author hxw
 * 
 */
public class PageLearnhub extends APage<Learnhub> {

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return LearnhubEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Learnhub> getList(Map<String, Object> params, int page,
			int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			return LearnhubEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
		}
		return null;
	}
}
