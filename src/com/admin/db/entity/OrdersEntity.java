package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.*;

import com.admin.content.Svc;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;
import com.bowlong.lang.StrEx;

//learnhall3_design - orders
@SuppressWarnings({ "static-access" })
public class OrdersEntity extends OrdersInternal {
	static Log log = LogFactory.getLog(OrdersEntity.class);

	public static final OrdersEntity my = new OrdersEntity();

	static OrdersDAO OrdersDAO = null;

	public static OrdersDAO OrdersDAO() {
		if (OrdersDAO == null)
			OrdersDAO = new OrdersDAO(com.admin.content.AppContext.dsData());
		return OrdersDAO;
	}

	public static void insertMmTry(final Orders orders) {
		OrdersDAO DAO = OrdersDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(orders, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	/**
	 * 取得满足参数的总页数
	 * 
	 * @throws SQLException
	 */
	public static int getCountAllBy(Map<String, Object> params)
			throws SQLException {
		OrdersDAO DAO = OrdersDAO();
		String sql = Svc.getSql4Count(DAO.TABLENAME, params);
		return DAO.queryForInt(sql);
	}

	/**
	 * 取得满足参数的分页列表对象
	 * 
	 * @throws Exception
	 */
	public static List<Orders> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		OrdersDAO DAO = OrdersDAO();
		String sql = Svc.getSql4List(DAO.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return DAO.queryForList(sql, Orders.class);
	}
	// types end

}
