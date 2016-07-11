package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.*;

import com.admin.content.Svc;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;
import com.bowlong.lang.StrEx;

//learnhall3_design - appraise
@SuppressWarnings({ "static-access" })
public class AppraiseEntity extends AppraiseInternal {
	static Log log = LogFactory.getLog(AppraiseEntity.class);

	public static final AppraiseEntity my = new AppraiseEntity();

	static AppraiseDAO AppraiseDAO = null;

	public static AppraiseDAO AppraiseDAO() {
		if (AppraiseDAO == null)
			AppraiseDAO = new AppraiseDAO(com.admin.content.AppContext.dsData());
		return AppraiseDAO;
	}

	public static void insertMmTry(final Appraise appraise) {
		AppraiseDAO DAO = AppraiseDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(appraise, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		AppraiseDAO dao = AppraiseDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Appraise> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		AppraiseDAO dao = AppraiseDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Appraise.class);
	}
	// types end

}
