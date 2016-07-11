package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;

//learnhall3_design - recordques4exam
@SuppressWarnings({ "static-access" })
public class Recordques4examEntity extends Recordques4examInternal{
    static Log log = LogFactory.getLog(Recordques4examEntity.class);

    public static final Recordques4examEntity my = new Recordques4examEntity();

    static Recordques4examDAO Recordques4examDAO = null;
    public static Recordques4examDAO Recordques4examDAO() {
        if( Recordques4examDAO == null)
            Recordques4examDAO = new Recordques4examDAO(com.admin.content.AppContext.dsData());
        return Recordques4examDAO;
    }


    public static void insertMmTry(final Recordques4exam recordques4exam) {
        Recordques4examDAO DAO = Recordques4examDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(recordques4exam, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

