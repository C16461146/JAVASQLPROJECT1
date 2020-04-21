import java.sql.*;

public class Database{
    static String url="jdbc:mariadb://localhost:3306/pc shop?user=root&password=";  //db address
    public static ResultSet checkLogin(String sql){                                 //login check
        try {
            Connection connection = DriverManager.getConnection(url);               //connect to the database
            Statement statement = connection.createStatement();                     //creates an sql statement
            ResultSet rs = statement.executeQuery(sql);                             //executes sql query
            if (rs.next()){                                                         //if login matches
                sql="SELECT `name`, `manager status` FROM `employees` WHERE `ppsn` = '"+rs.getString(1)+"'";//sql statement
                rs=statement.executeQuery(sql);                                     //executes sql query
                connection.close();                                                 //closes connection
                return rs;                                                          //returns sql result set
            }
            else {
                return null;                                                        //if login doesn't match return null
            }
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    public static ResultSet getComponent(String str){
        try {
            str="SELECT * FROM `components` WHERE `type`='"+str+"'";
            Connection connection = DriverManager.getConnection(url);               //connect to the database
            Statement statement = connection.createStatement();                     //creates an sql statement
            ResultSet rs = statement.executeQuery(str);                             //executes sql query
            return rs;                                                              //returns sql result set
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    public static boolean updateDatabase(String sql){
        try {
            Connection connection = DriverManager.getConnection(url);               //connect to the database
            Statement statement = connection.createStatement();                     //creates an sql statement
            ResultSet rs = statement.executeQuery(sql);                             //executes sql query
            return true;                                                              //returns sql result set
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }
    public static boolean isDuplicate(String sql){                                  //checks for duplicates
        try {
            Connection connection = DriverManager.getConnection(url);               //connect to the database
            Statement statement = connection.createStatement();                     //creates an sql statement
            ResultSet rs = statement.executeQuery(sql);                             //executes sql query
            if (rs.next()){                                                         //if duplicate found returns true, otherwise false
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }
    public static boolean insert(String sql){
        try {
            Connection connection = DriverManager.getConnection(url);               //connect to the database
            Statement statement = connection.createStatement();                     //creates an sql statement
            statement.executeQuery(sql);                                            //executes sql query
            return true;                                                            //returns true if successful
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }
    public static ResultSet getCustomerData(){
        try {
            String str="SELECT * FROM `customers` ";
            Connection connection = DriverManager.getConnection(url);               //connect to the database
            Statement statement = connection.createStatement();                     //creates an sql statement
            ResultSet rs = statement.executeQuery(str);                             //executes sql query
            return rs;                                                              //returns sql result set
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    public static ResultSet getPurchasesData(){
        try {
            String str="SELECT * FROM `purchases` ";
            Connection connection = DriverManager.getConnection(url);               //connect to the database
            Statement statement = connection.createStatement();                     //creates an sql statement
            ResultSet rs = statement.executeQuery(str);                             //executes sql query
            return rs;                                                              //returns sql result set
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    public static ResultSet getSpecificPurchasesData(String customerSelected){
        try {
            String str="SELECT * FROM `purchases` WHERE `FK_customerID`='"+customerSelected+"'";
            Connection connection = DriverManager.getConnection(url);               //connect to the database
            Statement statement = connection.createStatement();                     //creates an sql statement
            ResultSet rs = statement.executeQuery(str);                             //executes sql query
            return rs;                                                              //returns sql result set
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
}