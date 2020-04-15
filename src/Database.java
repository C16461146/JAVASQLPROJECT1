//package com.company;
import java.sql.*;

public class Database{
    public static ResultSet checkLogin(String str){
        try {
            System.out.println("sql : " + str);
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pc shop?user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(str);
            if (rs.next()){
                System.out.println("rs : " + rs.getString(1));
                str="SELECT `name`, `manager status` FROM `employees` WHERE `ppsn` = '"+rs.getString(1)+"'";
                rs=statement.executeQuery(str);
                connection.close();
                return rs;
            }
            else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    public static ResultSet getComponent(String str){
        try {
            str="SELECT * FROM `components` WHERE `type`='"+str+"'";
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pc shop?user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(str);
            return rs;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    public static ResultSet updateDatabase(String sSQL){
        try {
            System.out.println("Updated Cell Value " + sSQL);
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pc shop?user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sSQL);
            return rs;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    public static boolean isDuplicate(String sql){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pc shop?user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }
    public static boolean insert(String sql){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pc shop?user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }
}
