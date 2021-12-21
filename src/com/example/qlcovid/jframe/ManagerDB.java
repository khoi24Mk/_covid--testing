/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlcovid.jframe;

import com.example.qlcovid.string.UtilsString;
import java.sql.*;

/**
 *
 * @author nhonnhon
 */
public class ManagerDB {
    Connection conn;
    String dburl;
    String user;
    String password;
    String databaseName;
    public ManagerDB(){
        UtilsString ss = new UtilsString();
        dburl = ss.dbURL;
        user = ss.username;
        password = ss.password;
        databaseName = ss.DATABASENAME;
    }
    public Object [][] getdata(String query) throws SQLException{
        System.out.println(query);
        conn = DriverManager.getConnection(dburl+";user=" + user +";password=" + password);
        if (conn != null) {
            System.out.println("Connected DB.");
        }
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int numcols = rsMetaData.getColumnCount();
        int numrows = rs.last() ? rs.getRow() : 0;
        rs.beforeFirst();
        String[][] data = new String[numrows][numcols];
        int i = 0;
        while (rs.next()) {
            for(int j = 0; j<numcols; j++){
                data[i][j] = rs.getString(j+1);
            }
            i++;
        }
        conn.close();
        System.out.println("Disconnected DB.");
        return data;
    }
    public String get(String query) throws SQLException{
        System.out.println(query);
        conn = DriverManager.getConnection(dburl+";user=" + user +";password=" + password);
        if (conn != null) {
            System.out.println("Connected DB.");
        }
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int numcols = rsMetaData.getColumnCount();
        int numrows = rs.last() ? rs.getRow() : 0;
        rs.beforeFirst();
        String[][] data = new String[numrows][numcols];
        int i = 0;
        while (rs.next()) {
            for(int j = 0; j<numcols; j++){
                data[i][j] = rs.getString(j+1);
            }
            i++;
        }
        conn.close();
        System.out.println("Disconnected DB.");
        if(data.length<1) return "";
        return String.valueOf(data[0][0]);
    }
    public int count(String query) throws SQLException{
        System.out.println(query);
        conn = DriverManager.getConnection(dburl+ ";user=" + user +";password=" + password);
        if (conn != null) {
            System.out.println("Connected DB.");
        }
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int numcols = rsMetaData.getColumnCount();
        int numrows = rs.last() ? rs.getRow() : 0;
        rs.beforeFirst();
        String[][] data = new String[numrows][numcols];
        int i = 0;
        while (rs.next()) {
            for(int j = 0; j<numcols; j++){
                data[i][j] = rs.getString(j+1);
            }
            i++;
        }
        conn.close();
        System.out.println("Disconnected DB.");
        return Integer.valueOf(data[0][0]);
    }

    public void update(String query) throws SQLException{
        System.out.println(query);
        conn = DriverManager.getConnection(dburl+ ";user=" + user +";password=" + password);
        if (conn != null) {
            System.out.println("Connected DB.");
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Updated");
        conn.close();
    }
    public void insert(String query) throws SQLException{
        System.out.println(query);
        conn = DriverManager.getConnection(dburl+ ";user=" + user +";password=" + password);
        if (conn != null) {
            System.out.println("Connected DB.");
        }
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        System.out.println("Inserted");
        conn.close();
    }
}
