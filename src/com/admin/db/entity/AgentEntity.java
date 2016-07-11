package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.admin.content.Svc;
import com.admin.db.bean.Agent;
import com.admin.db.dao.AgentDAO;
import com.admin.db.internal.AgentInternal;

//learnhall3_design - agent
@SuppressWarnings({ "static-access" })
public class AgentEntity extends AgentInternal {
	static Log log = LogFactory.getLog(AgentEntity.class);

	public static final AgentEntity my = new AgentEntity();

	static AgentDAO AgentDAO = null;

	public static AgentDAO AgentDAO() {
		if (AgentDAO == null)
			AgentDAO = new AgentDAO(com.admin.content.AppContext.dsData());
		return AgentDAO;
	}

	public static void insertMmTry(final Agent agent) {
		AgentDAO DAO = AgentDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(agent, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/** 取得满足参数的总页数 */
	static public int getCountAllBy(Map<String, Object> params) throws Exception{
		AgentDAO dao = DAO();
		String sql = Svc.getSql4Count(dao.TABLENAME, params);
		return dao.queryForInt(sql);
	}
	
	/** 取得满足参数的分页列表对象 */
	static public List<Agent> getListBy(Map<String, Object> params, int begin,
			int limit) throws Exception {
		AgentDAO dao = DAO();
		String sql = Svc.getSql4List(dao.TABLENAME, params, begin, limit);
		return dao.queryForList(sql, Agent.class);
	}
	// types end

}
