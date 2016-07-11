package com.admin.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.admin.db.bean.Adcourses;
import com.admin.db.bean.Kind;
import com.admin.db.bean.Learnhub;
import com.admin.db.bean.Openkind4third;
import com.admin.db.entity.Openkind4thirdEntity;
import com.bowlong.util.ListEx;
import com.bowlong.util.page.APage;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageOpenKind4Third extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return Openkind4thirdEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Openkind4third> list = Openkind4thirdEntity.getListBy(params,
					begin, limit);
			if (!ListEx.isEmpty(list)) {
				List<Map> result = new ArrayList<Map>();
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Openkind4third en = list.get(i);
					if (en == null)
						continue;

					Map map = en.toBasicMap();
					Kind enKind = en.getKindFkKindid();
					if (enKind != null) {
						map.put("kindEn", enKind);
						Adcourses course = enKind.getAdcoursesFkCoursid();
						if(course != null){
							map.put("course", course);
							map.put("nmDepart", course.getAdqdepartmentFkDepartid().getName());
						}
						
						// 学习中心
						Learnhub lhub = enKind.getLearnhubFkLhubid();
						map.put("lhub", lhub);
					}
					
					result.add(map);
				}
				return result;
			}
		} catch (Exception e) {
		}
		return null;
	}

}
