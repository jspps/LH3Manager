package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;

//learnhall3_design - boughtkinds
@SuppressWarnings({ "static-access" })
public class BoughtkindsEntity extends BoughtkindsInternal{
    static Log log = LogFactory.getLog(BoughtkindsEntity.class);

    public static final BoughtkindsEntity my = new BoughtkindsEntity();

    static BoughtkindsDAO BoughtkindsDAO = null;
    public static BoughtkindsDAO BoughtkindsDAO() {
        if( BoughtkindsDAO == null)
            BoughtkindsDAO = new BoughtkindsDAO(com.admin.content.AppContext.dsData());
        return BoughtkindsDAO;
    }


    public static void insertMmTry(final Boughtkinds boughtkinds) {
        BoughtkindsDAO DAO = BoughtkindsDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(boughtkinds, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

