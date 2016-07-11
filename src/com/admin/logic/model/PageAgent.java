package com.admin.logic.model;

import java.util.List;
import java.util.Map;

import com.admin.db.bean.Agent;
import com.admin.db.entity.AgentEntity;
import com.bowlong.util.page.APage;

/**
 * 分页对象-代理商
 * 
 * @author Canyon
 * 
 */
public class PageAgent extends APage<Agent> {

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return AgentEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Agent> getList(Map<String, Object> params, int page,
			int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			return AgentEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
		}
		return null;
	}
}
