package com.admin.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.admin.db.bean.Account;
import com.admin.db.bean.Customer;
import com.admin.db.bean.Recordanswer;
import com.admin.db.entity.CustomerEntity;
import com.admin.db.entity.RecordanswerEntity;
import com.bowlong.util.ListEx;
import com.bowlong.util.page.APage;

/**
 * 分页对象-产品Product的Map对象
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageRecordAnser extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return RecordanswerEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Recordanswer> list = RecordanswerEntity.getListBy(params,
					begin, limit);
			if (!ListEx.isEmpty(list)) {
				List<Map> result = new ArrayList<Map>();
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Recordanswer en = list.get(i);
					Customer customer = CustomerEntity.getByKey(en
							.getCustomerid());
					if (null == customer) {
						continue;
					}
					Account account = customer.getAccountFkAccountid();

					Map map = en.toBasicMap();
					map.put("custname", customer.getName());
					map.put("phone", account.getPhone());
					map.put("cust_createtime", account.getCreatetime());
					result.add(map);
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
