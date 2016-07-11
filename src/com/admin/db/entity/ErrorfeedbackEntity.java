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

//learnhall3_design - errorfeedback
@SuppressWarnings({ "static-access" })
public class ErrorfeedbackEntity extends ErrorfeedbackInternal{
    static Log log = LogFactory.getLog(ErrorfeedbackEntity.class);

    public static final ErrorfeedbackEntity my = new ErrorfeedbackEntity();

    static ErrorfeedbackDAO ErrorfeedbackDAO = null;
    public static ErrorfeedbackDAO ErrorfeedbackDAO() {
        if( ErrorfeedbackDAO == null)
            ErrorfeedbackDAO = new ErrorfeedbackDAO(com.admin.content.AppContext.dsData());
        return ErrorfeedbackDAO;
    }


    public static void insertMmTry(final Errorfeedback errorfeedback) {
        ErrorfeedbackDAO DAO = ErrorfeedbackDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(errorfeedback, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


	 

    // types begin
	static public int getCountAllBy(Map<String, Object> params) throws Exception{
		   ErrorfeedbackDAO dao = ErrorfeedbackDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}
	
	/** 取得满足参数的分页列表对象 */
	static public List<Errorfeedback> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		   ErrorfeedbackDAO dao = ErrorfeedbackDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Errorfeedback.class);
	}
    // types end

}

