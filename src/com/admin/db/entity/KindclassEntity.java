package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;

//learnhall3_design - kindclass
@SuppressWarnings({ "static-access" })
public class KindclassEntity extends KindclassInternal{
    static Log log = LogFactory.getLog(KindclassEntity.class);

    public static final KindclassEntity my = new KindclassEntity();

    static KindclassDAO KindclassDAO = null;
    public static KindclassDAO KindclassDAO() {
        if( KindclassDAO == null)
            KindclassDAO = new KindclassDAO(com.admin.content.AppContext.dsData());
        return KindclassDAO;
    }


    public static void insertMmTry(final Kindclass kindclass) {
        KindclassDAO DAO = KindclassDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(kindclass, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

