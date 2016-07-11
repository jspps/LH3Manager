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
import com.admin.db.bean.Optquestion;
import com.admin.db.dao.OptquestionDAO;
import com.admin.db.internal.OptquestionInternal;

//learnhall3_design - optquestion
@SuppressWarnings({ "static-access" })
public class OptquestionEntity extends OptquestionInternal {
	static Log log = LogFactory.getLog(OptquestionEntity.class);

	public static final OptquestionEntity my = new OptquestionEntity();

	static OptquestionDAO OptquestionDAO = null;

	public static OptquestionDAO OptquestionDAO() {
		if (OptquestionDAO == null)
			OptquestionDAO = new OptquestionDAO(
					com.admin.content.AppContext.dsData());
		return OptquestionDAO;
	}

	public static void insertMmTry(final Optquestion optquestion) {
		OptquestionDAO DAO = OptquestionDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(optquestion, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		OptquestionDAO dao = OptquestionDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Optquestion> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		OptquestionDAO dao = OptquestionDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		return dao.queryForList(sql, Optquestion.class);
	}

	/** 删除满足时间目录的所有题 */
	static public void delByExamCatalogId(int examcatalogid) {
		OptquestionDAO dao = OptquestionDAO();
		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM ").append(dao.TABLENAME)
				.append(" WHERE 1 = 1 AND examcatalogid IN (")
				.append("SELECT id FROM examcatalog WHERE parentid = ")
				.append(examcatalogid).append(" OR id = ")
				.append(examcatalogid).append(")");
		String sql = buff.toString();
		try {
			dao.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*** 根据学习中心id,取得录入的题数 **/
	static public int getCountByLhubid(int lhubid) {
		String sql = "SELECT COUNT(*) FROM  optquestion WHERE examid IN(SELECT id FROM exam WHERE lhubid = "
				+ lhubid + ")";
		OptquestionDAO dao = OptquestionDAO();
		try {
			return dao.queryForInt(sql);
		} catch (SQLException e) {
			return 0;
		}
	}

	/*** 根据产品目录id,取得录入的题数 **/
	static public int getCountByCatalog4Procut(int pro0etpid) {
		String sql = "SELECT COUNT(*) FROM  optquestion WHERE examid IN(SELECT id FROM exam WHERE pro0etpid = "
				+ pro0etpid + ")";
		OptquestionDAO dao = OptquestionDAO();
		try {
			return dao.queryForInt(sql);
		} catch (SQLException e) {
			return 0;
		}
	}

	/*** 根据试卷目录id,取得录入的题数 **/
	static public int getCountByCatalog4Exam(int examcatalogid) {
		String sql = "SELECT COUNT(*) FROM  optquestion WHERE examcatalogid = "
				+ examcatalogid;
		OptquestionDAO dao = OptquestionDAO();
		try {
			return dao.queryForInt(sql);
		} catch (SQLException e) {
			return 0;
		}
	}

	/*** 根据试卷id,取得录入的题数 **/
	static public int getCountByExamid(int examid) {
		String sql = "SELECT COUNT(*) FROM  optquestion WHERE examid = "
				+ examid;
		OptquestionDAO dao = OptquestionDAO();
		try {
			return dao.queryForInt(sql);
		} catch (SQLException e) {
			return 0;
		}
	}
	// types end

}
