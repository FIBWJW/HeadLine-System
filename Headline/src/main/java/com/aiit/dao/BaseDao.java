package com.aiit.dao;

import com.aiit.pojo.NewsUser;
import com.aiit.utils.JdbcUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hmxia
 * @date 2024/12/9 15:15
 * 基础类，封装一些公共的查询方法和公共的增删改的方法
 */
public class BaseDao {

    //公共的查询方法 返回单个对象（）
    /*
    @params
    clazz:传入的对象
    sql:sql语句
    args:可变参数，在查询操作需要传入的参数
     */
    public <T>T baseQueryObject(Class<T> clazz,String sql,Object ...args){
        T t = null;
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //select * from NewsUser where username=? and pwd=?
            //准备语句
            preparedStatement = connection.prepareStatement(sql);
            //设置语句上的参数
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            //执行查询操作
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                t = (T) resultSet.getObject(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }
        return t;
    }


    //公共的查询方法，返回的是对象的集合
    public <T> List<T> baseQuery(Class clazz, String sql, Object ...args){
        List<T> list = new ArrayList<>();
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            //select * from NewsUser where username=? and pwd=?
            //准备语句
            preparedStatement = connection.prepareStatement(sql);
            //设置语句上的参数
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }

            //执行查询操作
            resultSet = preparedStatement.executeQuery();

            //结果集
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();  //获取结果集中字段的数量

            //将结果集的数据封装成实体类的对象进行返回
            while (resultSet.next()) {
                //将结果集的数据转化为实体类的对象（反射）
                Object o = clazz.getDeclaredConstructor().newInstance();  //转换后的对象  NewsUser
                for (int i = 1; i <=columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object value = resultSet.getObject(columnName);
                    //单独处理datetime类型的字段和Date数据类型转换问题
                    if(value.getClass().equals(LocalDateTime.class)){
                        value = Timestamp.valueOf((LocalDateTime) value) ;
                    }
                    //将查询的结果设置为list集合进行返回
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(o,value);
                }

                list.add((T) o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }
        return list;
    }

    //通用的增删改的方法
    public int baseUpdate(String sql,Object ...args){
        int rows = 0;
        //获取连接
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        //准备语句对象
        try {
            preparedStatement = connection.prepareStatement(sql);

            //设置语句的参数
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }

            //执行增删改的操作
           rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return  rows;
    }
}
