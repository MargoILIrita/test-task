package com.haulmont.testtask.model.DAO;


import org.hsqldb.jdbcDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by Маргарита on 28.05.2016.
 */
public abstract class DAO {
    Connection con;
public void init(){
    try {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }
        DriverManager.registerDriver(new jdbcDriver());
        con= DriverManager.getConnection("jdbc:hsqldb:mem:test","SA", "");
        con.createStatement().executeUpdate("create table contacts (name varchar(45),email varchar(45),phone varchar(45))");
        con.createStatement().execute("insert into contacts values ('trash', 'trash', 'trash')");
    } catch (SQLException e) {
        e.printStackTrace(System.out);
    }
}
}
