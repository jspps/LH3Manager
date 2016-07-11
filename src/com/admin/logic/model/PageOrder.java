package com.admin.logic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.db.bean.Agent;
import com.admin.db.bean.Customer;
import com.admin.db.bean.Orders;
import com.admin.db.entity.AgentEntity;
import com.admin.db.entity.CustomerEntity;
import com.admin.db.entity.OrdersEntity;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.APage;

/**
 * 分页对象-订单
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageOrder extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return OrdersEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Orders> list = OrdersEntity.getListBy(params, begin, limit);
			if (ListEx.isEmpty(list))
				return null;
			int lens = list.size();
			List<Map> result = new ArrayList<Map>();
			for (int i = 0; i < lens; i++) {
				Orders en = list.get(i);
				Map map = en.toBasicMap();
				result.add(map);
			}
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	/*** nmCust,nmAgent **/
	public void resetList(List<Map> list) {
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();

			Map<Integer, Customer> cache4Cust = new HashMap<Integer, Customer>();
			Map<String, Agent> cache4Agent = new HashMap<String, Agent>();
			for (int i = 0; i < lens; i++) {
				Map map = list.get(i);
				if (map == null) {
					continue;
				}

				int customerid = MapEx.getInt(map, "customerid");
				Customer cust = cache4Cust.get(customerid);
				if (cust == null) {
					cust = CustomerEntity.getByKey(customerid);
					cache4Cust.put(customerid, cust);
				}
				
				if (cust != null) {
					map.put("nmCust", cust.getName());
				}

				String recommendCode = MapEx.getString(map, "recommendCode");
				Agent agent = cache4Agent.get(recommendCode);
				if (agent == null) {
					agent = AgentEntity.getByCode(recommendCode);
					cache4Agent.put(recommendCode, agent);
				}

				if (agent != null) {
					map.put("nmAgent", agent.getUname());
				}
			}
		}
	}
}
