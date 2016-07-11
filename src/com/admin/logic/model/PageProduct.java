package com.admin.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.admin.db.bean.Adcourses;
import com.admin.db.bean.Kind;
import com.admin.db.bean.Learnhub;
import com.admin.db.bean.Product;
import com.admin.db.entity.AdcoursesEntity;
import com.admin.db.entity.ExamEntity;
import com.admin.db.entity.KindEntity;
import com.admin.db.entity.LearnhubEntity;
import com.admin.db.entity.ProductEntity;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.APage;

/**
 * 分页对象-产品Product的Map对象
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageProduct extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return ProductEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Product> list = ProductEntity.getListBy(params, begin, limit);
			if (!ListEx.isEmpty(list)) {
				List<Map> result = new ArrayList<Map>();
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Product en = list.get(i);
					Map map = en.toBasicMap();
					result.add(map);
				}
				return result;
			}
		} catch (Exception e) {
		}
		return null;
	}

	/*** 给产品添加更多的属性[coures:该产品的课程,Kinds:该产品的套餐] **/
	public void resetList(List<Map> list) {
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Map map = list.get(i);
				if (map == null) {
					continue;
				}

				int productid = MapEx.getInt(map, "id");
				if (productid <= 0) {
					continue;
				}

				if (!map.containsKey("coures")) {

					int coursesid = MapEx.getInt(map, "coursesid");
					
					int lhubid = MapEx.getInt(map, "lhubid");

					// 章节练习数量
					int exercisesnum = ExamEntity.getCountByProductidEType(
							productid, 1);
					map.put("exercisesnum", exercisesnum);
					// 历年真题数量
					int ahentinum = ExamEntity.getCountByProductidEType(
							productid, 2);
					map.put("ahentinum", ahentinum);

					// 全真模拟数量
					int simulationnum = ExamEntity.getCountByProductidEType(
							productid, 3);
					map.put("simulationnum", simulationnum);

					// 绝胜押题数量
					int vastnum = ExamEntity.getCountByProductidEType(
							productid, 4);
					map.put("vastnum", vastnum);

					// 课程
					Adcourses coures = AdcoursesEntity.getByKey(coursesid);
					map.put("coures", coures);
					
					// 学习中心
					Learnhub lhub = LearnhubEntity.getByKey(lhubid);
					map.put("lhub", lhub);

					// 套餐
					List<Kind> listKind = KindEntity.getByProductid(productid);
					map.put("kinds", listKind);
				}
			}
		}
	}

	/*** 给产品添加 课程 coures **/
	public void resetList4Coures(List<Map> list) {
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Map map = list.get(i);
				if (map == null) {
					continue;
				}

				int productid = MapEx.getInt(map, "id");
				if (productid <= 0) {
					continue;
				}

				if (!map.containsKey("coures")) {

					int coursesid = MapEx.getInt(map, "coursesid");

					// 课程
					Adcourses coures = AdcoursesEntity.getByKey(coursesid);
					map.put("coures", coures);
				}
			}
		}
	}
}
