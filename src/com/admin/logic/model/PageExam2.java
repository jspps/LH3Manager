package com.admin.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.admin.db.bean.Exam;
import com.admin.db.entity.ExamEntity;
import com.admin.db.entity.OptquestionEntity;
import com.bowlong.util.ListEx;
import com.bowlong.util.page.APage;

/**
 * 分页对象-试卷
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageExam2 extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return ExamEntity.getCountAllBy2(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Exam> list = ExamEntity.getListBy2(params, begin, limit);
			if (!ListEx.isEmpty(list)) {
				List<Map> result = new ArrayList<Map>();
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Exam en = list.get(i);
					if (en == null)
						continue;
					Map map = en.toBasicMap();
					int numQues = OptquestionEntity
							.getCountByExamid(en.getId());
					map.put("numQues", numQues);
					result.add(map);
				}
				return result;
			}
		} catch (Exception e) {
		}
		return null;
	}
}
