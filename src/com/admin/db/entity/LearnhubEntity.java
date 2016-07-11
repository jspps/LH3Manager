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

//learnhall3_design - learnhub
@SuppressWarnings({ "static-access" })
public class LearnhubEntity extends LearnhubInternal{
    static Log log = LogFactory.getLog(LearnhubEntity.class);

    public static final LearnhubEntity my = new LearnhubEntity();

    static LearnhubDAO LearnhubDAO = null;
    public static LearnhubDAO LearnhubDAO() {
        if( LearnhubDAO == null)
            LearnhubDAO = new LearnhubDAO(com.admin.content.AppContext.dsData());
        return LearnhubDAO;
    }


    public static void insertMmTry(final Learnhub learnhub) {
        LearnhubDAO DAO = LearnhubDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(learnhub, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params) throws Exception{
		LearnhubDAO dao = LearnhubDAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}
	
	/** 取得满足参数的分页列表对象 */
	static public List<Learnhub> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		LearnhubDAO dao = LearnhubDAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		return dao.queryForList(sql, Learnhub.class);
	}
    // types end

}

