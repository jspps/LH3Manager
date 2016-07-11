package com.admin.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.admin.db.bean.*;
import com.admin.db.dao.*;
import com.admin.db.internal.*;
//import com.admin.content.AppContext;

//learnhall3_design - shoppingcart
@SuppressWarnings({ "static-access" })
public class ShoppingcartEntity extends ShoppingcartInternal{
    static Log log = LogFactory.getLog(ShoppingcartEntity.class);

    public static final ShoppingcartEntity my = new ShoppingcartEntity();

    static ShoppingcartDAO ShoppingcartDAO = null;
    public static ShoppingcartDAO ShoppingcartDAO() {
        if( ShoppingcartDAO == null)
            ShoppingcartDAO = new ShoppingcartDAO(com.admin.content.AppContext.dsData());
        return ShoppingcartDAO;
    }


    public static void insertMmTry(final Shoppingcart shoppingcart) {
        ShoppingcartDAO DAO = ShoppingcartDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(shoppingcart, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

