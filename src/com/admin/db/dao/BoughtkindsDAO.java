package com.admin.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import java.sql.*;
import java.util.concurrent.*;
import javax.sql.*;
import com.bowlong.util.DateEx;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.admin.db.bean.*;

//learnhall3_design - boughtkinds
@SuppressWarnings({"rawtypes", "unchecked"})
public class BoughtkindsDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(BoughtkindsDAO.class);

    public static final String TABLE = "boughtkinds";
    public static String TABLENAME = "boughtkinds";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "name", "customerid", "kindid", "price", "kbi", "num", "status", "createtime", "validtime", "lhubid"};
    public static String coulmns = "id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid";
    public static String coulmns2 = "name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid";

    public BoughtkindsDAO(Connection conn) {
        super(conn);
    }

    public BoughtkindsDAO(DataSource ds) {
        super(ds);
    }

    public BoughtkindsDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Boughtkinds boughtkinds) {
        return insert(boughtkinds, TABLENAME);
    }

    public int insert(final Boughtkinds boughtkinds, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            boughtkinds.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid) VALUES (:name, :customerid, :kindid, :price, :kbi, :num, :status, :createtime, :validtime, :lhubid)");
            Map map = super.insert(sql.toString(), boughtkinds);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Boughtkinds boughtkinds) {
        return asyncInsert(boughtkinds, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Boughtkinds boughtkinds, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(boughtkinds, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                       return 0;
                   } finally {
                       decrementAndGet();
                   }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public Future<Integer> asyncInsert2(final Boughtkinds boughtkinds) {
        return asyncInsert2(boughtkinds, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Boughtkinds boughtkinds, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(boughtkinds, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                       return 0;
                   } finally {
                       decrementAndGet();
                   }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int insert2(final Boughtkinds boughtkinds) {
        return insert2(boughtkinds, TABLENAME);
    }

    public int insert2(final Boughtkinds boughtkinds, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            boughtkinds.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid) VALUES (:id, :name, :customerid, :kindid, :price, :kbi, :num, :status, :createtime, :validtime, :lhubid)");
            Map map = super.insert(sql.toString(), boughtkinds);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Boughtkinds> boughtkindss) {
        return insert(boughtkindss, TABLENAME);
    }

    public int[] insert(final List<Boughtkinds> boughtkindss, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(boughtkindss == null || boughtkindss.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid) VALUES (:name, :customerid, :kindid, :price, :kbi, :num, :status, :createtime, :validtime, :lhubid)");
            return super.batchInsert(sql.toString(), boughtkindss);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int id) {
        return deleteByKey(id, TABLENAME);
    }

    public int deleteByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int id) {
        return asyncDeleteByKey(id, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int id, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(id, TABLENAME2);
                    } catch (Exception e) {
                       log.info(e2s(e));
                       return 0;
                    } finally {
                        decrementAndGet();
                    }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int[] deleteByKey(final int[] ids) {
        return deleteByKey(ids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            List list = newList();
            for (int id : keys) {
                Map params = newMap();
                params.put("id", id);
                list.add(params);
            }
            return super.batchUpdate(sql.toString(), list);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInKeys(final List<Integer> keys) {
        return deleteInKeys(keys, TABLENAME);
    }

    public int deleteInKeys(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Boughtkinds> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Boughtkinds> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Boughtkinds boughtkinds = beans.get(i);
                int id = boughtkinds.id;
                sb.append(id);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Boughtkinds> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPKs() {
        return selectPKs(TABLENAME);
    }

    public List<Integer> selectPKs(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Map> selectInIndex() {
        return selectInIndex(TABLENAME);
    }

    public List<Map> selectInIndex(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, customerid, kindid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Boughtkinds> selectIn(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Boughtkinds> selectIn2(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectInPKs(final List<Integer> keys) {
        return selectInPKs(keys, TABLENAME);
    }

    public List<Integer> selectInPKs(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            List<Integer> result = newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Boughtkinds> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLastPKs(final int num) {
        return selectLastPKs(num, TABLENAME);
    }

    public List<Integer> selectLastPKs(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Boughtkinds last() {
        return last(TABLENAME);
    }

    public Boughtkinds last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Boughtkinds.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Boughtkinds> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Boughtkinds> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int id) {
        return selectGtKeyPKs(id, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Boughtkinds selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Boughtkinds selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int maxId() {
        return maxId(TABLENAME);
    }

    public int maxId(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT MAX(id) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByCustomerid(final Integer customerid) {
        return countByCustomerid(customerid, TABLENAME);
    }

    public int countByCustomerid(final Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectByCustomerid(final Integer customerid) {
        return selectByCustomerid(customerid, TABLENAME);
    }

    public List<Boughtkinds> selectByCustomerid(final Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id ");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForList(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCustomeridPKs(final Integer customerid) {
        return selectByCustomeridPKs(customerid, TABLENAME);
    }

    public List<Integer> selectByCustomeridPKs(final Integer customerid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id ");
            Map params = newMap();
            params.put("customerid", customerid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectPageByCustomerid(final Integer customerid, final int begin, final int num) {
        return selectPageByCustomerid(customerid, begin, num, TABLENAME);
    }

    public List<Boughtkinds> selectPageByCustomerid(final Integer customerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customerid", customerid);
            return super.queryForList(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCustomeridPKs(final Integer customerid, final int begin, final int num) {
        return selectPageByCustomeridPKs(customerid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCustomeridPKs(final Integer customerid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE customerid = :customerid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("customerid", customerid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Boughtkinds selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Boughtkinds selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByKindid(final Integer kindid) {
        return countByKindid(kindid, TABLENAME);
    }

    public int countByKindid(final Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ");
            Map params = newMap();
            params.put("kindid", kindid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectByKindid(final Integer kindid) {
        return selectByKindid(kindid, TABLENAME);
    }

    public List<Boughtkinds> selectByKindid(final Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ORDER BY id ");
            Map params = newMap();
            params.put("kindid", kindid);
            return super.queryForList(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByKindidPKs(final Integer kindid) {
        return selectByKindidPKs(kindid, TABLENAME);
    }

    public List<Integer> selectByKindidPKs(final Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ORDER BY id ");
            Map params = newMap();
            params.put("kindid", kindid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectPageByKindid(final Integer kindid, final int begin, final int num) {
        return selectPageByKindid(kindid, begin, num, TABLENAME);
    }

    public List<Boughtkinds> selectPageByKindid(final Integer kindid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("kindid", kindid);
            return super.queryForList(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByKindidPKs(final Integer kindid, final int begin, final int num) {
        return selectPageByKindidPKs(kindid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByKindidPKs(final Integer kindid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE kindid = :kindid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("kindid", kindid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Boughtkinds selectByCustomeridKindid(final Integer customerid, Integer kindid) {
        return selectByCustomeridKindid(customerid, kindid, TABLENAME);
    }

    public Boughtkinds selectByCustomeridKindid(final Integer customerid, Integer kindid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" WHERE customerid=:customerid AND kindid=:kindid");
            Map params = newMap();
            params.put("customerid", customerid);
            params.put("kindid", kindid);
            return super.queryForObject(sql.toString(), params, Boughtkinds.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int count() {
        return count(TABLENAME);
    }

    public int count(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append("");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Boughtkinds> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Boughtkinds> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, name, customerid, kindid, price, kbi, num, status, createtime, validtime, lhubid FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Boughtkinds.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByPagePKs(final int begin, final int num) {
        return selectByPagePKs(begin, num, TABLENAME);
    }

    public List<Integer> selectByPagePKs(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Boughtkinds boughtkinds) {
        return updateByKey(boughtkinds, TABLENAME);
    }

    public int updateByKey(final Boughtkinds boughtkinds, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = boughtkinds.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), boughtkinds);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Boughtkinds boughtkinds) {
        return asyncUpdate(boughtkinds, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Boughtkinds boughtkinds, final String TABLENAME2) {
        try {

            String _ustr = boughtkinds.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, boughtkinds);
                    } catch (Exception e) {
                        log.error(e2s(e));
                        return 0;
                    } finally {
                        decrementAndGet();
                    }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int updateCustomeridByKey(final int customerid, final int id){
        return updateCustomeridByKey(customerid, id, TABLENAME);
    }

    public int updateCustomeridByKey(final int customerid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid=customerid+:customerid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMinByKey(final int id, final int customerid, final int _min){
        return updateCustomeridWithMinByKey(id, customerid, _min, TABLENAME);
    }

    public int updateCustomeridWithMinByKey(final int id, final int customerid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid = (select case when customerid+:customerid<=:_min then :_min else customerid+:customerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMinInKeys(final List<Integer> keys, final int customerid, final int _min){
        return updateCustomeridWithMinInKeys(keys, customerid, _min, TABLENAME);
    }

    public int updateCustomeridWithMinInKeys(final List<Integer> keys, final int customerid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid = (select case when customerid+:customerid<=:_min then :_min else customerid+:customerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMaxByKey(final int id, final int customerid, final int _max){
        return updateCustomeridWithMaxByKey(id, customerid, _max, TABLENAME);
    }

    public int updateCustomeridWithMaxByKey(final int id, final int customerid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid = (select case when customerid+:customerid>=:_max then :_max else customerid+:customerid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMaxInKeys(final List<Integer> keys, final int customerid, final int _max){
        return updateCustomeridWithMaxInKeys(keys, customerid, _max, TABLENAME);
    }

    public int updateCustomeridWithMaxInKeys(final List<Integer> keys, final int customerid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET customerid = (select case when customerid+:customerid>=:_max then :_max else customerid+:customerid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("customerid", customerid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCustomeridWithMinMaxByKey(final int id, final int customerid, final int _min, final int _max){
        return updateCustomeridWithMinMaxByKey(id, customerid, _min, _max, TABLENAME);
    }

    public int updateCustomeridWithMinMaxByKey(final int id, final int customerid, final int _min, final int _max, final String TABLENAME2){
        if( customerid < 0 ) {
            return updateCustomeridWithMinByKey(id, customerid, _min, TABLENAME2);
        } else {
            return updateCustomeridWithMaxByKey(id, customerid, _max, TABLENAME2);
        }
    }

    public int updateCustomeridWithMinMaxInKeys(final List<Integer> keys, final int customerid, final int _min, final int _max){
        return updateCustomeridWithMinMaxInKeys(keys, customerid, _min, _max, TABLENAME);
    }

    public int updateCustomeridWithMinMaxInKeys(final List<Integer> keys, final int customerid, final int _min, final int _max, final String TABLENAME2){
        if( customerid < 0 ) {
            return updateCustomeridWithMinInKeys(keys, customerid, _min, TABLENAME2);
        } else {
            return updateCustomeridWithMaxInKeys(keys, customerid, _max, TABLENAME2);
        }
    }

    public int updateKindidByKey(final int kindid, final int id){
        return updateKindidByKey(kindid, id, TABLENAME);
    }

    public int updateKindidByKey(final int kindid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid=kindid+:kindid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMinByKey(final int id, final int kindid, final int _min){
        return updateKindidWithMinByKey(id, kindid, _min, TABLENAME);
    }

    public int updateKindidWithMinByKey(final int id, final int kindid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid = (select case when kindid+:kindid<=:_min then :_min else kindid+:kindid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMinInKeys(final List<Integer> keys, final int kindid, final int _min){
        return updateKindidWithMinInKeys(keys, kindid, _min, TABLENAME);
    }

    public int updateKindidWithMinInKeys(final List<Integer> keys, final int kindid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid = (select case when kindid+:kindid<=:_min then :_min else kindid+:kindid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMaxByKey(final int id, final int kindid, final int _max){
        return updateKindidWithMaxByKey(id, kindid, _max, TABLENAME);
    }

    public int updateKindidWithMaxByKey(final int id, final int kindid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid = (select case when kindid+:kindid>=:_max then :_max else kindid+:kindid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMaxInKeys(final List<Integer> keys, final int kindid, final int _max){
        return updateKindidWithMaxInKeys(keys, kindid, _max, TABLENAME);
    }

    public int updateKindidWithMaxInKeys(final List<Integer> keys, final int kindid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kindid = (select case when kindid+:kindid>=:_max then :_max else kindid+:kindid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("kindid", kindid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKindidWithMinMaxByKey(final int id, final int kindid, final int _min, final int _max){
        return updateKindidWithMinMaxByKey(id, kindid, _min, _max, TABLENAME);
    }

    public int updateKindidWithMinMaxByKey(final int id, final int kindid, final int _min, final int _max, final String TABLENAME2){
        if( kindid < 0 ) {
            return updateKindidWithMinByKey(id, kindid, _min, TABLENAME2);
        } else {
            return updateKindidWithMaxByKey(id, kindid, _max, TABLENAME2);
        }
    }

    public int updateKindidWithMinMaxInKeys(final List<Integer> keys, final int kindid, final int _min, final int _max){
        return updateKindidWithMinMaxInKeys(keys, kindid, _min, _max, TABLENAME);
    }

    public int updateKindidWithMinMaxInKeys(final List<Integer> keys, final int kindid, final int _min, final int _max, final String TABLENAME2){
        if( kindid < 0 ) {
            return updateKindidWithMinInKeys(keys, kindid, _min, TABLENAME2);
        } else {
            return updateKindidWithMaxInKeys(keys, kindid, _max, TABLENAME2);
        }
    }

    public int updatePriceByKey(final double price, final int id){
        return updatePriceByKey(price, id, TABLENAME);
    }

    public int updatePriceByKey(final double price, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price=price+:price WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMinByKey(final int id, final double price, final double _min){
        return updatePriceWithMinByKey(id, price, _min, TABLENAME);
    }

    public int updatePriceWithMinByKey(final int id, final double price, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price = (select case when price+:price<=:_min then :_min else price+:price end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMinInKeys(final List<Integer> keys, final double price, final double _min){
        return updatePriceWithMinInKeys(keys, price, _min, TABLENAME);
    }

    public int updatePriceWithMinInKeys(final List<Integer> keys, final double price, final double _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price = (select case when price+:price<=:_min then :_min else price+:price end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMaxByKey(final int id, final double price, final double _max){
        return updatePriceWithMaxByKey(id, price, _max, TABLENAME);
    }

    public int updatePriceWithMaxByKey(final int id, final double price, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price = (select case when price+:price>=:_max then :_max else price+:price end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMaxInKeys(final List<Integer> keys, final double price, final double _max){
        return updatePriceWithMaxInKeys(keys, price, _max, TABLENAME);
    }

    public int updatePriceWithMaxInKeys(final List<Integer> keys, final double price, final double _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET price = (select case when price+:price>=:_max then :_max else price+:price end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("price", price);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePriceWithMinMaxByKey(final int id, final double price, final double _min, final double _max){
        return updatePriceWithMinMaxByKey(id, price, _min, _max, TABLENAME);
    }

    public int updatePriceWithMinMaxByKey(final int id, final double price, final double _min, final double _max, final String TABLENAME2){
        if( price < 0 ) {
            return updatePriceWithMinByKey(id, price, _min, TABLENAME2);
        } else {
            return updatePriceWithMaxByKey(id, price, _max, TABLENAME2);
        }
    }

    public int updatePriceWithMinMaxInKeys(final List<Integer> keys, final double price, final double _min, final double _max){
        return updatePriceWithMinMaxInKeys(keys, price, _min, _max, TABLENAME);
    }

    public int updatePriceWithMinMaxInKeys(final List<Integer> keys, final double price, final double _min, final double _max, final String TABLENAME2){
        if( price < 0 ) {
            return updatePriceWithMinInKeys(keys, price, _min, TABLENAME2);
        } else {
            return updatePriceWithMaxInKeys(keys, price, _max, TABLENAME2);
        }
    }

    public int updateKbiByKey(final int kbi, final int id){
        return updateKbiByKey(kbi, id, TABLENAME);
    }

    public int updateKbiByKey(final int kbi, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi=kbi+:kbi WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMinByKey(final int id, final int kbi, final int _min){
        return updateKbiWithMinByKey(id, kbi, _min, TABLENAME);
    }

    public int updateKbiWithMinByKey(final int id, final int kbi, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi = (select case when kbi+:kbi<=:_min then :_min else kbi+:kbi end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMinInKeys(final List<Integer> keys, final int kbi, final int _min){
        return updateKbiWithMinInKeys(keys, kbi, _min, TABLENAME);
    }

    public int updateKbiWithMinInKeys(final List<Integer> keys, final int kbi, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi = (select case when kbi+:kbi<=:_min then :_min else kbi+:kbi end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMaxByKey(final int id, final int kbi, final int _max){
        return updateKbiWithMaxByKey(id, kbi, _max, TABLENAME);
    }

    public int updateKbiWithMaxByKey(final int id, final int kbi, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi = (select case when kbi+:kbi>=:_max then :_max else kbi+:kbi end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMaxInKeys(final List<Integer> keys, final int kbi, final int _max){
        return updateKbiWithMaxInKeys(keys, kbi, _max, TABLENAME);
    }

    public int updateKbiWithMaxInKeys(final List<Integer> keys, final int kbi, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET kbi = (select case when kbi+:kbi>=:_max then :_max else kbi+:kbi end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("kbi", kbi);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateKbiWithMinMaxByKey(final int id, final int kbi, final int _min, final int _max){
        return updateKbiWithMinMaxByKey(id, kbi, _min, _max, TABLENAME);
    }

    public int updateKbiWithMinMaxByKey(final int id, final int kbi, final int _min, final int _max, final String TABLENAME2){
        if( kbi < 0 ) {
            return updateKbiWithMinByKey(id, kbi, _min, TABLENAME2);
        } else {
            return updateKbiWithMaxByKey(id, kbi, _max, TABLENAME2);
        }
    }

    public int updateKbiWithMinMaxInKeys(final List<Integer> keys, final int kbi, final int _min, final int _max){
        return updateKbiWithMinMaxInKeys(keys, kbi, _min, _max, TABLENAME);
    }

    public int updateKbiWithMinMaxInKeys(final List<Integer> keys, final int kbi, final int _min, final int _max, final String TABLENAME2){
        if( kbi < 0 ) {
            return updateKbiWithMinInKeys(keys, kbi, _min, TABLENAME2);
        } else {
            return updateKbiWithMaxInKeys(keys, kbi, _max, TABLENAME2);
        }
    }

    public int updateNumByKey(final int num, final int id){
        return updateNumByKey(num, id, TABLENAME);
    }

    public int updateNumByKey(final int num, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num=num+:num WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinByKey(final int id, final int num, final int _min){
        return updateNumWithMinByKey(id, num, _min, TABLENAME);
    }

    public int updateNumWithMinByKey(final int id, final int num, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num<=:_min then :_min else num+:num end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinInKeys(final List<Integer> keys, final int num, final int _min){
        return updateNumWithMinInKeys(keys, num, _min, TABLENAME);
    }

    public int updateNumWithMinInKeys(final List<Integer> keys, final int num, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num<=:_min then :_min else num+:num end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMaxByKey(final int id, final int num, final int _max){
        return updateNumWithMaxByKey(id, num, _max, TABLENAME);
    }

    public int updateNumWithMaxByKey(final int id, final int num, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num>=:_max then :_max else num+:num end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMaxInKeys(final List<Integer> keys, final int num, final int _max){
        return updateNumWithMaxInKeys(keys, num, _max, TABLENAME);
    }

    public int updateNumWithMaxInKeys(final List<Integer> keys, final int num, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num>=:_max then :_max else num+:num end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinMaxByKey(final int id, final int num, final int _min, final int _max){
        return updateNumWithMinMaxByKey(id, num, _min, _max, TABLENAME);
    }

    public int updateNumWithMinMaxByKey(final int id, final int num, final int _min, final int _max, final String TABLENAME2){
        if( num < 0 ) {
            return updateNumWithMinByKey(id, num, _min, TABLENAME2);
        } else {
            return updateNumWithMaxByKey(id, num, _max, TABLENAME2);
        }
    }

    public int updateNumWithMinMaxInKeys(final List<Integer> keys, final int num, final int _min, final int _max){
        return updateNumWithMinMaxInKeys(keys, num, _min, _max, TABLENAME);
    }

    public int updateNumWithMinMaxInKeys(final List<Integer> keys, final int num, final int _min, final int _max, final String TABLENAME2){
        if( num < 0 ) {
            return updateNumWithMinInKeys(keys, num, _min, TABLENAME2);
        } else {
            return updateNumWithMaxInKeys(keys, num, _max, TABLENAME2);
        }
    }

    public int updateStatusByKey(final int status, final int id){
        return updateStatusByKey(status, id, TABLENAME);
    }

    public int updateStatusByKey(final int status, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status=status+:status WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinByKey(final int id, final int status, final int _min){
        return updateStatusWithMinByKey(id, status, _min, TABLENAME);
    }

    public int updateStatusWithMinByKey(final int id, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinInKeys(final List<Integer> keys, final int status, final int _min){
        return updateStatusWithMinInKeys(keys, status, _min, TABLENAME);
    }

    public int updateStatusWithMinInKeys(final List<Integer> keys, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMaxByKey(final int id, final int status, final int _max){
        return updateStatusWithMaxByKey(id, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxByKey(final int id, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMaxInKeys(final List<Integer> keys, final int status, final int _max){
        return updateStatusWithMaxInKeys(keys, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxInKeys(final List<Integer> keys, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinMaxByKey(final int id, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxByKey(id, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxByKey(final int id, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinByKey(id, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxByKey(id, status, _max, TABLENAME2);
        }
    }

    public int updateStatusWithMinMaxInKeys(final List<Integer> keys, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxInKeys(keys, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxInKeys(final List<Integer> keys, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinInKeys(keys, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxInKeys(keys, status, _max, TABLENAME2);
        }
    }

    public int updateLhubidByKey(final int lhubid, final int id){
        return updateLhubidByKey(lhubid, id, TABLENAME);
    }

    public int updateLhubidByKey(final int lhubid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid=lhubid+:lhubid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMinByKey(final int id, final int lhubid, final int _min){
        return updateLhubidWithMinByKey(id, lhubid, _min, TABLENAME);
    }

    public int updateLhubidWithMinByKey(final int id, final int lhubid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid = (select case when lhubid+:lhubid<=:_min then :_min else lhubid+:lhubid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMinInKeys(final List<Integer> keys, final int lhubid, final int _min){
        return updateLhubidWithMinInKeys(keys, lhubid, _min, TABLENAME);
    }

    public int updateLhubidWithMinInKeys(final List<Integer> keys, final int lhubid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid = (select case when lhubid+:lhubid<=:_min then :_min else lhubid+:lhubid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMaxByKey(final int id, final int lhubid, final int _max){
        return updateLhubidWithMaxByKey(id, lhubid, _max, TABLENAME);
    }

    public int updateLhubidWithMaxByKey(final int id, final int lhubid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid = (select case when lhubid+:lhubid>=:_max then :_max else lhubid+:lhubid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMaxInKeys(final List<Integer> keys, final int lhubid, final int _max){
        return updateLhubidWithMaxInKeys(keys, lhubid, _max, TABLENAME);
    }

    public int updateLhubidWithMaxInKeys(final List<Integer> keys, final int lhubid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lhubid = (select case when lhubid+:lhubid>=:_max then :_max else lhubid+:lhubid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lhubid", lhubid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLhubidWithMinMaxByKey(final int id, final int lhubid, final int _min, final int _max){
        return updateLhubidWithMinMaxByKey(id, lhubid, _min, _max, TABLENAME);
    }

    public int updateLhubidWithMinMaxByKey(final int id, final int lhubid, final int _min, final int _max, final String TABLENAME2){
        if( lhubid < 0 ) {
            return updateLhubidWithMinByKey(id, lhubid, _min, TABLENAME2);
        } else {
            return updateLhubidWithMaxByKey(id, lhubid, _max, TABLENAME2);
        }
    }

    public int updateLhubidWithMinMaxInKeys(final List<Integer> keys, final int lhubid, final int _min, final int _max){
        return updateLhubidWithMinMaxInKeys(keys, lhubid, _min, _max, TABLENAME);
    }

    public int updateLhubidWithMinMaxInKeys(final List<Integer> keys, final int lhubid, final int _min, final int _max, final String TABLENAME2){
        if( lhubid < 0 ) {
            return updateLhubidWithMinInKeys(keys, lhubid, _min, TABLENAME2);
        } else {
            return updateLhubidWithMaxInKeys(keys, lhubid, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Boughtkinds> boughtkindss) {
        return updateByKey(boughtkindss, TABLENAME);
    }

    public int[] updateByKey (final List<Boughtkinds> boughtkindss, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(boughtkindss == null || boughtkindss.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET name=:name, customerid=:customerid, kindid=:kindid, price=:price, kbi=:kbi, num=:num, status=:status, createtime=:createtime, validtime=:validtime, lhubid=:lhubid WHERE id=:id");
            return super.batchUpdate2(sql.toString(), boughtkindss);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void createTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`name`  TINYTEXT NOT NULL," +
                "	`customerid`  INT(11) NOT NULL," +
                "	`kindid`  INT(11) NOT NULL," +
                "	`price`  DOUBLE NOT NULL," +
                "	`kbi`  INT(11) NOT NULL," +
                "	`num`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`validtime`  DATE NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `customerid_kind` (`customerid`, `kindid`)," +
                "	KEY `customerid` (`customerid`)," +
                "	KEY `kindid` (`kindid`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void createNoUniqueTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`name`  TINYTEXT NOT NULL," +
                "	`customerid`  INT(11) NOT NULL," +
                "	`kindid`  INT(11) NOT NULL," +
                "	`price`  DOUBLE NOT NULL," +
                "	`kbi`  INT(11) NOT NULL," +
                "	`num`  INT(11) NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`validtime`  DATE NOT NULL," +
                "	`lhubid`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `customerid_kind` (`customerid`, `kindid`)," +
                "	KEY `customerid` (`customerid`)," +
                "	KEY `kindid` (`kindid`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void truncate(){
        try {
            super.truncate(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void repair(){
        try {
            super.repair(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void optimize(){
        try {
            super.optimize(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void dropTable(){
        try {
            super.dropTable(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

}
