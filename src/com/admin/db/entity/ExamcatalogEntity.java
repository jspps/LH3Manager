package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.admin.content.Svc;
import com.admin.db.bean.Examcatalog;
import com.admin.db.dao.ExamcatalogDAO;
import com.admin.db.internal.ExamcatalogInternal;

//learnhall3_design - examcatalog
@SuppressWarnings({ "static-access" })
public class ExamcatalogEntity extends ExamcatalogInternal {
	static Log log = LogFactory.getLog(ExamcatalogEntity.class);

	public static final ExamcatalogEntity my = new ExamcatalogEntity();

	static ExamcatalogDAO ExamcatalogDAO = null;

	public static ExamcatalogDAO ExamcatalogDAO() {
		if (ExamcatalogDAO == null)
			ExamcatalogDAO = new ExamcatalogDAO(
					com.admin.content.AppContext.dsData());
		return ExamcatalogDAO;
	}

	public static void insertMmTry(final Examcatalog examcatalog) {
		ExamcatalogDAO DAO = ExamcatalogDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(examcatalog, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数(params的val是一个条件字符串> like,!=的模式) */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		ExamcatalogDAO dao = ExamcatalogDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象(params的val是一个条件字符串> like,!=的模式) */
	static public List<Examcatalog> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		ExamcatalogDAO dao = ExamcatalogDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		return dao.queryForList(sql, Examcatalog.class);
	}

	/** 取得满足参数的分页列表对象(params的val是一个条件字符串> like,!=的模式) */
	static public List<Examcatalog> getListByParentid(int parentid)
			throws Exception {
		ExamcatalogDAO dao = ExamcatalogDAO();
		String sql = "SELECT * FROM " + dao.TABLENAME + " WHERE parentid = "
				+ parentid;
		return dao.queryForList(sql, Examcatalog.class);
	}

	/** 删除目录结构，并删除该目录结构下面的试题 */
	static public void delExamCatalogAndOptQuestion(int examcatalogid) {
		OptquestionEntity.delByExamCatalogId(examcatalogid);
		ExamcatalogDAO dao = ExamcatalogDAO();
		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM examcatalog WHERE 1 = 1 AND parentid = ")
				.append(examcatalogid).append(" OR id = ")
				.append(examcatalogid);
		String sql = buff.toString();
		try {
			dao.update(sql);
		} catch (Exception e) {
		}
	}
	// types end

}
