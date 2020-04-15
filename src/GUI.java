import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GUI{
    Database db = new Database();
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JTable table1, table2;
    private JLabel welcome;
    private JTextField NameTextField;
    private JTextField QuantityTextField;
    private JTextField PriceTextField;
    private JTextField ModelTextField;
    private JComboBox TypeComboBox;
    private JButton Save;
    private JTextField newID;
    private JTextField newPPSN;
    private JPasswordField newPsw;
    private JPasswordField newPswRepeat;
    private JTextField newName;
    private JTextField newSurname;
    private JComboBox newStatus;
    private JTextField newSalary;
    private JTextField newYear;
    private JTextField newMonth;
    private JTextField newDay;
    private JButton newSave;
    private JLabel newDateErr;
    private JLabel newSurnameErr;
    private JLabel newNameErr;
    private JLabel newSalaryErr;
    private JLabel newPswRepeatErr;
    private JLabel newPswErr;
    private JLabel newFieldsReqErr;
    private JLabel newSuccessMsg;
    private JPanel addEmployeePanel;
    private JLabel invPriceErr;
    private JLabel invFieldsErr;
    private JLabel invQuantityErr;
    private JScrollPane scoller2;
    private JFrame frameGlobal;
    public static boolean loggedIn = false;
    DefaultTableModel tableModel, tableModel2;
    private static boolean managerStatus=false;

    public void createUIComponents() {
        String[] partList = {"CPU", "GPU", "MotherBoard", "Storage", "PSU"};
        String[] columnNames = {"Name", "Model", "Price", "Quantity", "Type"};
        String[][] data = {{"Name", "Model", "Price", "Quantity", "Type"}};
        tableModel = new DefaultTableModel(data, columnNames);
        TypeComboBox = new JComboBox(partList);
        newStatus = new JComboBox(new String[]{"No", "Yes"});
        tabbedPane1 = new JTabbedPane();
        //tabbedPane1.setEnabledAt(2,false);//returns out of bound error???


        //***************
        //1st tab code
        comboBox1 = new JComboBox(partList);
        comboBox1.addActionListener(new ActionListener() {
            // TODO: Update the table
            public void actionPerformed(ActionEvent e) {//this gets executed whenever you select an item from the drop down menu
                String selectedItem = (String) comboBox1.getSelectedItem();
                ResultSet rs = db.getComponent(selectedItem);//gets the sql data from the selected item and stores it into rs
                //rs contains the sql data. rs.next() is used to access the next row. rs.getString(index) is used to access individual "cells"
                try {
                    displayQuery(selectedItem, rs);

                    while (rs.next()) {
                        //for now i just got all of the data to print. see if you can change it so it gets put into the table instead.
                        System.out.println("Name : " + rs.getString(1));
                        System.out.println("Model : " + rs.getString(2));
                        System.out.println("Price : " + rs.getString(3));
                        System.out.println("Quantity : " + rs.getString(4));
                        System.out.println("Type : " + rs.getString(5));
                        System.out.println();

                        String pname = rs.getString(1);
                        String pmodel = rs.getString(2);
                        String pprice = rs.getString(3);
                        String pquantity = rs.getString(4);
                        String ptype = rs.getString(5);
                    }
                } catch (SQLException ex) {
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
            }
        });

        table1 = new JTable();
        table1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){

                    int row = table1.getSelectedRow();
                    int column = table1.getSelectedColumn();
                    String updatedCellValue = table1.getValueAt(row,column).toString();
                    String sql = "UPDATE components SET ";

                    for(int x=0;x<table1.getColumnCount();x++){
                        if(x!=table1.getColumnCount()-1){
                            sql+="`"+columnNames[x]+"`='"+table1.getValueAt(row,x).toString()+"', ";
                        } else {
                            sql+="`"+columnNames[x]+"`='"+table1.getValueAt(row,x).toString()+"' WHERE ";
                        }
                    }

                    for(int x=0;x<table1.getColumnCount();x++){
                        if(x!=column){
                            sql+="`"+columnNames[x]+"`='"+table1.getValueAt(row,x).toString()+"' AND ";
                        }
                    }
                    sql+="1=1";
                    db.updateDatabase(sql);
                }
            }
        });

        //**********************
        //2nd tab code
        Save = new JButton();
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {

                String nameEntered = NameTextField.getText();
                String quantityEntered = QuantityTextField.getText();
                String  priceEntered = PriceTextField.getText();
                String  modelEntered = ModelTextField.getText();
                String  comboEntered = (String) TypeComboBox.getSelectedItem();
                invFieldsErr.setVisible(false);
                invPriceErr.setVisible(false);
                invQuantityErr.setVisible(false);
                boolean error=false;

                if(nameEntered.length()==0||quantityEntered.length()==0||priceEntered.length()==0||modelEntered.length()==0){
                    error=true;
                    invFieldsErr.setVisible(true);
                }
                if(!quantityEntered.matches("[0-9]+")){//checks if values contain numbers only
                    invQuantityErr.setVisible(true);
                    error=true;
                }
                if(!priceEntered.matches("[0-9]+")){//checks if values contain numbers only
                    invPriceErr.setVisible(true);
                    error=true;
                }
                if(!error){
                    String sSQL = "INSERT INTO `components` (`Name`, `Model Number`, `Price`, `Quantity`, `Type`) VALUES ('"+nameEntered+"', '"+modelEntered+"', '"+priceEntered+"', '"+quantityEntered+"', '"+comboEntered+"')";
                    db.updateDatabase(sSQL);
                }
            }
        });
        //********************************
        //3rd tab code
        while (true){//random id generator
            Random random = new Random();
            int randomNumber = random.nextInt(9999999 + 1 - 1000000) + 9999999;
            String id = "C"+ randomNumber;
            String sql= "SELECT * FROM `employee login` WHERE `ID` = '"+id+"'";
            if(!db.isDuplicate(sql)){//check for duplicates
                newID = new JTextField(id);
                break;
            }
        }

        newSave = new JButton();
        if(managerStatus){//adds button functionality for managers only
            newSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    //disables errors
                    newDateErr.setVisible(false);
                    newSurnameErr.setVisible(false);
                    newNameErr.setVisible(false);
                    newSalaryErr.setVisible(false);
                    newPswRepeatErr.setVisible(false);
                    newPswErr.setVisible(false);
                    newFieldsReqErr.setVisible(false);
                    newSuccessMsg.setVisible(false);
                    //parses values
                    String newIDVal = newID.getText();
                    String newPPSNVal = newPPSN.getText();
                    String newPswVal = newPsw.getText();
                    String newPswRepeatVal = newPswRepeat.getText();
                    String newNameVal = newName.getText();
                    String newSurnameVal = newSurname.getText();
                    String newYearVal = newYear.getText();
                    String newMonthVal = newMonth.getText();
                    String newDayVal = newDay.getText();
                    String newSalaryVal = newSalary.getText();
                    String newStatusVal = (String) newStatus.getSelectedItem();
                    String date=newDayVal+"-"+newMonthVal+"-"+newYearVal;
                    boolean error=false;

                    if(newIDVal.length()==0||newPPSNVal.length()==0||newNameVal.length()==0||newSurnameVal.length()==0||newSalaryVal.length()==0){//empty field check
                        newFieldsReqErr.setVisible(true);
                        error=true;
                    }
                    if(newPswVal.length()<10){//password length check
                        newPswErr.setVisible(true);
                        error=true;
                    }
                    if(!newPswVal.equals(newPswRepeatVal)){//checks if passwords match
                        newPswRepeatErr.setVisible(true);
                        error=true;
                    }
                    if(!newSalaryVal.matches("[0-9]+")){//checks if values contain numbers only
                        newSalaryErr.setVisible(true);
                        error=true;
                    }
                    if(!newNameVal.matches("[a-zA-Z]")){//checks if values contain letters only
                        newNameErr.setVisible(true);
                        error=true;
                    }
                    if(!newNameVal.matches("[a-zA-Z]")){//checks if values contain letters only
                        newSurnameErr.setVisible(true);
                        error=true;
                    }
                    if(newYearVal.length()==0||newMonthVal.length()==0||newDayVal.length()==0){//checks for empty date values
                        error=true;
                        newDateErr.setVisible(true);
                    } else {
                        try{
                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            df.setLenient(false);
                            df.parse(date);//checks if date is valid
                        }
                        catch (Exception ex){
                            error=true;
                            newDateErr.setVisible(true);
                        }
                    }

                    if(!error){//if form is correct
                        //sql statements
                        String sql1="INSERT INTO `employees` (`ppsn`, `name`, `surname`, `dob`, `salary`, `manager status`) VALUES ('"+newPPSNVal+"', '"+newNameVal+"', '"+newSurnameVal+"', '"+date+"', '"+newSalaryVal+"', '"+newStatusVal+"')";
                        String sql2="INSERT INTO `employee login` (`ID`, `password`, `ppsn_FK`) VALUES ('"+newIDVal+"', '"+newPswVal+"', '"+newPPSNVal+"')";
                        if(db.insert(sql1)&&db.insert(sql2)){
                            newSuccessMsg.setVisible(true);
                        }
                    }
                }
            });
        }
    }



    public void displayQuery(String selectedItem, ResultSet rs) throws SQLException {
        System.out.println("" + selectedItem);

        Object[] values = new Object[5];
        boolean changed = false;

        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        while (rs.next()) {
            values = new Object[5];
            values[0] = rs.getString(1);
            values[1] = rs.getString(2);
            values[2] = rs.getString(3);
            values[3] = rs.getString(4);
            values[4] = rs.getString(5);

            tableModel.addRow(values);
        }
        table1.setModel(tableModel);
    }

    //For commit asdasdsaddasdsd
    public static void showUI(ResultSet rs) {

        try {
            rs.next();
            if (rs.getString(2).equals("Yes")) {
                managerStatus=true;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        JFrame frame = new JFrame("PC Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GUI().panel1);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Login login = new Login();
        Login.createAndShowGUI();
    }

}
