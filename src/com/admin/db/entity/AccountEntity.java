package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;

//learnhall3_design - account
@SuppressWarnings({ "static-access" })
public class AccountEntity extends AccountInternal{
    static Log log = LogFactory.getLog(AccountEntity.class);

    public static final AccountEntity my = new AccountEntity();

    static AccountDAO AccountDAO = null;
    public static AccountDAO AccountDAO() {
        if( AccountDAO == null)
            AccountDAO = new AccountDAO(com.admin.content.AppContext.dsData());
        return AccountDAO;
    }


    public static void insertMmTry(final Account account) {
        AccountDAO DAO = AccountDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(account, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

