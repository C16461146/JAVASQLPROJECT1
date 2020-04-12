//package com.company;
import java.sql.*;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class Database{
    public static ResultSet checkLogin(String str){
        try {
            System.out.println("sql : " + str);
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pc shop?user=root&password=");
                    //SELECT * FROM `components`
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(str);
            //connection.close();
            //System.out.println("sql : " + str);
            if (rs.next()){
                System.out.println("rs : " + rs.getString(1));
                str="SELECT `name`, `manager status` FROM `employees` WHERE `ppsn` = '"+rs.getString(1)+"'";
                rs=statement.executeQuery(str);
                connection.close();
                return rs;
            }
            else {
                //System.out.println("rs : " + rs.getString(0));
                return null;
            }


            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
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
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    public static ResultSet updateDatabase(String sSQL){
        try {
            //str="SELECT * FROM `components` WHERE `type`='"+str+"'";

            System.out.println("Updated Cell Value " + sSQL);

            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pc shop?user=root&password=");

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sSQL);
            statement.executeQuery(sSQL);
            return rs;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
}
