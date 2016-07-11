package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.admin.db.bean.Orders4profit;
import com.admin.db.dao.Orders4profitDAO;
import com.admin.db.internal.Orders4profitInternal;
import com.bowlong.lang.PStr;

//learnhall3_design - orders4profit
@SuppressWarnings({ "static-access" })
public class Orders4profitEntity extends Orders4profitInternal {
	static Log log = LogFactory.getLog(Orders4profitEntity.class);

	public static final Orders4profitEntity my = new Orders4profitEntity();

	static Orders4profitDAO Orders4profitDAO = null;

	public static Orders4profitDAO Orders4profitDAO() {
		if (Orders4profitDAO == null)
			Orders4profitDAO = new Orders4profitDAO(
					com.admin.content.AppContext.dsData());
		return Orders4profitDAO;
	}

	public static void insertMmTry(final Orders4profit orders4profit) {
		Orders4profitDAO DAO = Orders4profitDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(orders4profit, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/*** 根据学习中心id,取得成功的订单交易购买套餐成功的数量 **/
	static public int getCountByLhubid(int lhubid) {
		if (lhubid <= 0)
			return 0;
		PStr pStr = PStr.b("SELECT COUNT(*) FROM  orders4profit WHERE 1 = 1");
		pStr.a(" AND status = 0");
		pStr.a(" AND isProfit4Lhub = true");
		pStr.a(" AND lhubid = ", lhubid);
		pStr.a(" AND orderNo IN(SELECT orderNo FROM orders WHERE statusProcess > 0 AND status = 0 AND type = 0 ORDER BY createtime DESC )");
		String sql = pStr.end();
		Orders4profitDAO dao = Orders4profitDAO();
		try {
			return dao.queryForInt(sql);
		} catch (SQLException e) {
			return 0;
		}
	}

	/*** 根据学习中心id,取得成功的订单交易购买套餐成功的记录 **/
	static public List<Orders4profit> getListByLhubid(int lhubid, int begin, int limit) {
		if (lhubid <= 0)
			return null;
		PStr pStr = PStr.b("SELECT * FROM  orders4profit WHERE 1 = 1");
		pStr.a(" AND status = 0");
		pStr.a(" AND isProfit4Lhub = true");
		pStr.a(" AND lhubid = ", lhubid);
		pStr.a(" AND orderNo IN(SELECT orderNo FROM orders WHERE statusProcess > 0 AND status = 0 AND type = 0 ORDER BY createtime DESC )");
		pStr.a(" ORDER BY createtime DESC ");
		pStr.a(" LIMIT ",begin,",",limit);
		String sql = pStr.end();

		Orders4profitDAO dao = Orders4profitDAO();
		try {
			return dao.queryForList(sql, Orders4profit.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	/*** 根据学习中心id,取得成功的订单交易购买套餐成功的数量 **/
	static public int getCountByAgentid(int agentid) {
		if (agentid <= 0)
			return 0;
		PStr pStr = PStr.b("SELECT COUNT(*) FROM  orders4profit WHERE 1 = 1");
		pStr.a(" AND status = 0");
		pStr.a(" AND isProfit4Lhub = true");
		pStr.a(" AND agentid = ", agentid);
		pStr.a(" AND orderNo IN(SELECT orderNo FROM orders WHERE statusProcess > 0 AND status = 0 AND type = 0 ORDER BY createtime DESC )");
		String sql = pStr.end();
		Orders4profitDAO dao = Orders4profitDAO();
		try {
			return dao.queryForInt(sql);
		} catch (SQLException e) {
			return 0;
		}
	}

	/*** 根据学习中心id,取得成功的订单交易购买套餐成功的记录 **/
	static public List<Orders4profit> getListByAgentid(int agentid, int begin, int limit) {
		if (agentid <= 0)
			return null;
		PStr pStr = PStr.b("SELECT * FROM  orders4profit WHERE 1 = 1");
		pStr.a(" AND status = 0");
		pStr.a(" AND isProfit4Lhub = true");
		pStr.a(" AND agentid = ", agentid);
		pStr.a(" AND orderNo IN(SELECT orderNo FROM orders WHERE statusProcess > 0 AND status = 0 AND type = 0 ORDER BY createtime DESC )");
		pStr.a(" ORDER BY createtime DESC ");
		pStr.a(" LIMIT ",begin,",",limit);
		String sql = pStr.end();

		Orders4profitDAO dao = Orders4profitDAO();
		try {
			return dao.queryForList(sql, Orders4profit.class);
		} catch (Exception e) {
			return null;
		}
	}
	// types end

}
