package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.admin.content.Svc;
import com.admin.db.bean.Exam;
import com.admin.db.dao.ExamDAO;
import com.admin.db.internal.ExamInternal;

//learnhall3_design - exam
@SuppressWarnings({ "static-access" })
public class ExamEntity extends ExamInternal {
	static Log log = LogFactory.getLog(ExamEntity.class);

	public static final ExamEntity my = new ExamEntity();

	static ExamDAO ExamDAO = null;

	public static ExamDAO ExamDAO() {
		if (ExamDAO == null)
			ExamDAO = new ExamDAO(com.admin.content.AppContext.dsData());
		return ExamDAO;
	}

	public static void insertMmTry(final Exam exam) {
		ExamDAO DAO = ExamDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(exam, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		ExamDAO dao = ExamDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Exam> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		ExamDAO dao = ExamDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		return dao.queryForList(sql, Exam.class);
	}

	/** 取得满足参数的总页数(params的val是一个条件字符串> like,!=的模式) */
	static public int getCountAllBy2(Map<String, Object> params)
			throws Exception {
		ExamDAO dao = ExamDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象(params的val是一个条件字符串> like,!=的模式) */
	static public List<Exam> getListBy2(Map<String, Object> params, int begin,
			int limit) throws Exception {
		ExamDAO dao = ExamDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		return dao.queryForList(sql, Exam.class);
	}

	// public void loadLinked(final Exam exam) {
	// if(exam == null) return;
	// List<Exam2kind> exam2kinds = exam.getExam2kindsFkExamid(); // exam2kind
	// }

	// types begin
	/***
	 * 试卷数量 examtypeid (1, '章节练习'),(2, '历年真题'),(3, '全真模拟'),(4, '绝胜押题'),(5,
	 * '知识要点'),(6, 'ITMS辅助')
	 * **/
	static public int getCountByProductidEType(int productid, int examtypeid) {
		String sql = "SELECT COUNT(*) FROM  exam WHERE 1 = 1 ";
		if (productid > 0) {
			sql += " AND productid = " + productid;
		}

		if (examtypeid > 0) {
			sql += " AND examtypeid = " + examtypeid;
		}

		ExamDAO dao = ExamDAO();
		try {
			return dao.queryForInt(sql);
		} catch (SQLException e) {
			return 0;
		}
	}
	// types end

}
