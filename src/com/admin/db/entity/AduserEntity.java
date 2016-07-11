package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;
import com.bowlong.lang.StrEx;

//learnhall3_design - aduser
@SuppressWarnings({ "static-access" })
public class AduserEntity extends AduserInternal {
	static Log log = LogFactory.getLog(AduserEntity.class);

	public static final AduserEntity my = new AduserEntity();

	static AduserDAO AduserDAO = null;

	public static AduserDAO AduserDAO() {
		if (AduserDAO == null)
			AduserDAO = new AduserDAO(com.admin.content.AppContext.dsData());
		return AduserDAO;
	}

	public static void insertMmTry(final Aduser aduser) {
		AduserDAO DAO = AduserDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(aduser, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// public void loadLinked(final Aduser aduser) {
	// if(aduser == null) return;
	// Agent agents = aduser.getAgentFkUid(); // agent
	// List<Aduser2power> aduser2powers = aduser.getAduser2powersFkUid(); //
	// aduser2power
	// }

	// types begin
	static public List<Aduser> getListByUname(String uname) {
		AduserDAO dao = AduserDAO();
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT * FROM ").append(dao.TABLENAME)
				.append(" WHERE 1 = 1 ");
		if (!StrEx.isEmptyTrim(uname)) {
			buff.append(" AND uname LIKE '%").append(uname.trim()).append("%'");
		}
		try {
			return dao.queryForList(buff.toString(), Aduser.class);
		} catch (Exception e) {
			return new ArrayList<Aduser>();
		}
	}
	// types end

}
