package com.admin.logic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.db.bean.Customer;
import com.admin.db.bean.Kind;
import com.admin.db.bean.Orders4profit;
import com.admin.db.entity.CustomerEntity;
import com.admin.db.entity.KindEntity;
import com.admin.db.entity.Orders4profitEntity;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.APage;

/**
 * 分页对象-订单分成(分润记录)
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageOrder4Profit extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			if (params != null) {
				if (params.containsKey("lhubid")) {
					return Orders4profitEntity.getCountByLhubid(MapEx.getInt(
							params, "lhubid"));
				} else if (params.containsKey("agentid")) {
					return Orders4profitEntity.getCountByAgentid(MapEx.getInt(
							params, "agentid"));
				}
			}
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Orders4profit> list = null;
			if (params != null) {
				if (params.containsKey("lhubid")) {
					list = Orders4profitEntity.getListByLhubid(
							MapEx.getInt(params, "lhubid"), begin, limit);
				} else if (params.containsKey("agentid")) {
					list = Orders4profitEntity.getListByAgentid(
							MapEx.getInt(params, "agentid"), begin, limit);
				}
			}
			if (ListEx.isEmpty(list))
				return null;
			int lens = list.size();
			List<Map> result = new ArrayList<Map>();
			for (int i = 0; i < lens; i++) {
				Orders4profit en = list.get(i);
				Map map = en.toBasicMap();
				result.add(map);
			}
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	/*** nmCust,nmAgent **/
	public void resetList4Cust(List<Map> list) {
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();

			Map<Integer, Customer> cache4Cust = new HashMap<Integer, Customer>();
			Map<Integer, Kind> cache4Kind = new HashMap<Integer, Kind>();
			
			for (int i = 0; i < lens; i++) {
				Map map = list.get(i);
				if (map == null) {
					continue;
				}

				int customerid = MapEx.getInt(map, "custid");
				Customer cust = cache4Cust.get(customerid);
				if (cust == null) {
					cust = CustomerEntity.getByKey(customerid);
					cache4Cust.put(customerid, cust);
				}

				if (cust != null) {
					map.put("cust", cust);
				}
				
				int kindid = MapEx.getInt(map, "kindid");
				Kind kind = cache4Kind.get(kindid);
				if (kind == null) {
					kind = KindEntity.getByKey(kindid);
					cache4Kind.put(kindid, kind);
				}

				if (kind != null) {
					map.put("kind",kind);
				}
				
			}
		}
	}
}
