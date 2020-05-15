package com.oxygen.v1.core;


import com.alibaba.fastjson.JSON;
import com.oxygen.v1.config.DataSourceProperties;
import com.oxygen.v1.domain.Blog;

import java.sql.*;

/**
 * GPExecutor封装底层JDBC执行sql的方法
 * @author oxygen
 * @date 2020/5/14
 **/
public class GPExecutor {

    /**
     * 数据源属性
     */
    private DataSourceProperties datasource;


    /**
     * 构造注入数据库相关配置属性
     * @param datasource
     */
    public GPExecutor(DataSourceProperties datasource) {
        this.datasource = datasource;
    }


    /**
     * 查询方法
     * @param sql
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T query(String sql, Object parameter) {
        Connection conn = null;
        Statement stmt = null;
        Blog blog = new Blog();
        try {
            // 注册 JDBC 驱动
            //Class.forName("com.mysql.jdbc.Driver");
            // 打开连接
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "root");
            conn = DriverManager.getConnection(
                    datasource.getUrl(),
                    datasource.getUsername(),
                    datasource.getPassword());
            // 执行查询
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(sql, parameter));
            System.out.println("执行[查询]操作，sql="+sql);
            // 获取结果集
            while (rs.next()) {
                Integer bid = rs.getInt("bid");
                String name = rs.getString("name");
                Integer authorId = rs.getInt("author_id");
                blog.setAuthorId(authorId);
                blog.setBid(bid);
                blog.setName(name);
            }
            System.out.println("执行[查询]操作，返回结果："+ JSON.toJSON(blog));
            rs.close(); //关闭结果集
            stmt.close(); //关闭语句集
            conn.close(); //关闭连接
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null){stmt.close();}
            } catch (SQLException se2) {
            }
            try {
                if (conn != null){ conn.close();}
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return (T)blog;
    }


    /**
     * update更新
     * @param sql
     * @param parameter
     * @return
     */
    public int update(String sql, Object parameter) {
        Connection conn = null;
        Statement stmt = null;
        int ret=0;
        try {
            conn = DriverManager.getConnection(
                    datasource.getUrl(),
                    datasource.getUsername(),
                    datasource.getPassword());
            // 执行查询
            stmt = conn.createStatement();
            Object[] args=(Object[])parameter;
            String format = String.format(sql, args);
            //String format = String.format(sql, parameter);
            ret= stmt.executeUpdate(format);
            System.out.println("执行[更新/删除]操作，sql="+sql);
            System.out.println("执行[更新/删除]操作，返回结果："+ret);
            stmt.close(); //关闭语句集
            conn.close(); //关闭连接
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null){stmt.close();}
            } catch (SQLException se2) {
            }
            try {
                if (conn != null){ conn.close();}
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return ret;
    }


    /**
     * delete 删除
     * @param sql
     * @param parameter
     * @return
     */
    public int delete(String sql, Object parameter) {
        return update(sql,parameter);
    }


    /**
     * 执行数据插入
     * @param sql
     * @param parameter
     * @return
     */
    public int insert(String sql, Object parameter) {
        Connection conn = null;
        Statement stmt = null;
        int ret=0;
        try {
            conn = DriverManager.getConnection(
                    datasource.getUrl(),
                    datasource.getUsername(),
                    datasource.getPassword());
            // 执行查询
            stmt = conn.createStatement();
            Object[] args=(Object[])parameter;
            String format = String.format(sql, args);
            stmt.executeUpdate(format);
            System.out.println("执行[新增]操作，sql="+sql);
            System.out.println("执行[新增]操作，返回结果："+ret);
            stmt.close(); //关闭语句集
            conn.close(); //关闭连接
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null){stmt.close();}
            } catch (SQLException se2) {
            }
            try {
                if (conn != null){ conn.close();}
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return ret;
    }
}
