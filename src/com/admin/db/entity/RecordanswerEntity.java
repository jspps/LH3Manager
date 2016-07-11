package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.admin.content.Svc;
import com.admin.db.bean.Recordanswer;
import com.admin.db.dao.RecordanswerDAO;
import com.admin.db.internal.RecordanswerInternal;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;

//learnhall3_design - recordanswer
@SuppressWarnings({ "static-access" })
public class RecordanswerEntity extends RecordanswerInternal {
	static Log log = LogFactory.getLog(RecordanswerEntity.class);

	public static final RecordanswerEntity my = new RecordanswerEntity();

	static RecordanswerDAO RecordanswerDAO = null;

	public static RecordanswerDAO RecordanswerDAO() {
		if (RecordanswerDAO == null)
			RecordanswerDAO = new RecordanswerDAO(
					com.admin.content.AppContext.dsData());
		return RecordanswerDAO;
	}

	public static void insertMmTry(final Recordanswer recordanswer) {
		RecordanswerDAO DAO = RecordanswerDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(recordanswer, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		RecordanswerDAO dao = RecordanswerDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Recordanswer> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		RecordanswerDAO dao = RecordanswerDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = PStr.str(left, " ORDER BY score DESC,lasttime DESC,createtime DESC LIMIT ",
				right);
		return dao.queryForList(sql, Recordanswer.class);
	}
	// types end

}
