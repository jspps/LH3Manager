package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.admin.content.Svc;
import com.admin.db.bean.Openkind4third;
import com.admin.db.dao.Openkind4thirdDAO;
import com.admin.db.internal.Openkind4thirdInternal;
import com.bowlong.lang.StrEx;

//learnhall3_design - openkind4third
@SuppressWarnings({ "static-access" })
public class Openkind4thirdEntity extends Openkind4thirdInternal{
    static Log log = LogFactory.getLog(Openkind4thirdEntity.class);

    public static final Openkind4thirdEntity my = new Openkind4thirdEntity();

    static Openkind4thirdDAO Openkind4thirdDAO = null;
    public static Openkind4thirdDAO Openkind4thirdDAO() {
        if( Openkind4thirdDAO == null)
            Openkind4thirdDAO = new Openkind4thirdDAO(com.admin.content.AppContext.dsData());
        return Openkind4thirdDAO;
    }


    public static void insertMmTry(final Openkind4third openkind4third) {
        Openkind4thirdDAO DAO = Openkind4thirdDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(openkind4third, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    /** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params)
			throws Exception {
		Openkind4thirdDAO dao = Openkind4thirdDAO();
		String sql = Svc.getSql4CountBy(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}

	/** 取得满足参数的分页列表对象 */
	static public List<Openkind4third> getListBy(Map<String, Object> params,
			int begin, int limit) throws Exception {
		Openkind4thirdDAO dao = Openkind4thirdDAO();
		String sql = Svc.getSql4ListBy(dao.TABLENAME, params, begin, limit);
		String left = StrEx.left(sql, "LIMIT");
		String right = StrEx.right(sql, "LIMIT");
		sql = left + " ORDER BY createtime DESC LIMIT " + right;
		return dao.queryForList(sql, Openkind4third.class);
	}
    // types end

}

