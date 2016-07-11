package com.admin.logic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.db.bean.Errorfeedback;
import com.admin.db.bean.Learnhub;
import com.admin.db.entity.ErrorfeedbackEntity;
import com.bowlong.util.ListEx;
import com.bowlong.util.page.APage;

/**
 * 分页对象-错误反馈
 * 
 * @author hxw
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PageErrorfeedback extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return ErrorfeedbackEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Errorfeedback> list = ErrorfeedbackEntity.getListBy(params,
					begin, limit);
			if (ListEx.isEmpty(list))
				return null;
			int lens = list.size();
			List<Map> ret = new ArrayList<Map>();
			Map<Integer, Learnhub> cache4Lhub = new HashMap<Integer, Learnhub>();
			for (int i = 0; i < lens; i++) {
				Errorfeedback en = list.get(i);
				Map map = en.toBasicMap();
				Learnhub lhub = cache4Lhub.get(en.getLhubid());
				if (lhub == null) {
					lhub = en.getLearnhubFkLhubid();
					cache4Lhub.put(en.getLhubid(), lhub);
				}

				if (lhub != null) {
					map.put("nmLhub", lhub.getName());
				}
				
				ret.add(map);
			}
			return ret;
		} catch (Exception e) {
		}
		return null;
	}
}
