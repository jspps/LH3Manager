package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;

//learnhall3_design - ask
@SuppressWarnings({ "static-access" })
public class AskEntity extends AskInternal{
    static Log log = LogFactory.getLog(AskEntity.class);

    public static final AskEntity my = new AskEntity();

    static AskDAO AskDAO = null;
    public static AskDAO AskDAO() {
        if( AskDAO == null)
            AskDAO = new AskDAO(com.admin.content.AppContext.dsData());
        return AskDAO;
    }


    public static void insertMmTry(final Ask ask) {
        AskDAO DAO = AskDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(ask, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

