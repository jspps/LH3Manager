package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
import com.admin.content.AppContext;

//learnhall3_design - answer
@SuppressWarnings({ "static-access" })
public class AnswerEntity extends AnswerInternal{
    static Log log = LogFactory.getLog(AnswerEntity.class);

    public static final AnswerEntity my = new AnswerEntity();

    static AnswerDAO AnswerDAO = null;
    public static AnswerDAO AnswerDAO() {
        if( AnswerDAO == null)
            AnswerDAO = new AnswerDAO(AppContext.dsData());
        return AnswerDAO;
    }


    public static void insertMmTry(final Answer answer) {
        AnswerDAO DAO = AnswerDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(answer, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

