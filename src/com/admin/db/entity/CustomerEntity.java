package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;

//learnhall3_design - customer
@SuppressWarnings({ "static-access" })
public class CustomerEntity extends CustomerInternal{
    static Log log = LogFactory.getLog(CustomerEntity.class);

    public static final CustomerEntity my = new CustomerEntity();

    static CustomerDAO CustomerDAO = null;
    public static CustomerDAO CustomerDAO() {
        if( CustomerDAO == null)
            CustomerDAO = new CustomerDAO(com.admin.content.AppContext.dsData());
        return CustomerDAO;
    }


    public static void insertMmTry(final Customer customer) {
        CustomerDAO DAO = CustomerDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(customer, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

