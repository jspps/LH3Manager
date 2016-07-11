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

//learnhall3_design - kind
@SuppressWarnings({ "static-access" })
public class KindEntity extends KindInternal{
    static Log log = LogFactory.getLog(KindEntity.class);

    public static final KindEntity my = new KindEntity();

    static KindDAO KindDAO = null;
    public static KindDAO KindDAO() {
        if( KindDAO == null)
            KindDAO = new KindDAO(com.admin.content.AppContext.dsData());
        return KindDAO;
    }


    public static void insertMmTry(final Kind kind) {
        KindDAO DAO = KindDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(kind, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }



    // public void loadLinked(final Kind kind) {
        // if(kind == null) return;
        // List<Exam2kind> exam2kinds = kind.getExam2kindsFkKindid(); // exam2kind
    // }

    // types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params) throws Exception{
		 KindDAO dao = KindDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}
	
	/** 取得满足参数的分页列表对象 */
	static public List<Kind> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		KindDAO dao = KindDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		return dao.queryForList(sql, Kind.class);
	}
    // types end

}

