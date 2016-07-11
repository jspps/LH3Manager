package com.admin.logic.model;

import java.util.List;
import java.util.Map;

import com.admin.db.bean.Msg;
import com.admin.db.entity.MsgEntity;
import com.bowlong.util.page.APage;

/**
 * 分页对象-消息
 * 
 * @author hxw
 * 
 */
public class PageMsg extends APage<Msg> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return MsgEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Msg> getList(Map<String, Object> params, int page,
			int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			return MsgEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
		}
		return null;
	}
}
