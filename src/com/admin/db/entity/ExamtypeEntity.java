package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;

//learnhall3_design - examtype
@SuppressWarnings({ "static-access" })
public class ExamtypeEntity extends ExamtypeInternal{
    static Log log = LogFactory.getLog(ExamtypeEntity.class);

    public static final ExamtypeEntity my = new ExamtypeEntity();

    static ExamtypeDAO ExamtypeDAO = null;
    public static ExamtypeDAO ExamtypeDAO() {
        if( ExamtypeDAO == null)
            ExamtypeDAO = new ExamtypeDAO(com.admin.content.AppContext.dsData());
        return ExamtypeDAO;
    }


    public static void insertMmTry(final Examtype examtype) {
        ExamtypeDAO DAO = ExamtypeDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(examtype, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

