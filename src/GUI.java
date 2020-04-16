import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class GUI{
    Database db = new Database();
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JComboBox componentSelector;
    private JTable componentTable;
    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JTextField priceTextField;
    private JTextField modelTextField;
    private JComboBox addComponentSelector;
    private JButton save;
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
    private JLabel invPriceErr;
    private JLabel invFieldsErr;
    private JLabel invQuantityErr;
    private JLabel successLabel;
    private JLabel errorLabel;
    private JLabel invSuccess;
    DefaultTableModel tableModel;
    private static boolean managerStatus=false;

    public void createUIComponents() {
        String[] partList = {"CPU", "GPU", "MotherBoard", "Storage", "PSU"};
        String[] columnNames = {"Name", "Model Number", "Price", "Quantity", "Type"};
        String[][] data = {{"Name", "Model", "Price", "Quantity", "Type"}};
        //tabbedPane = new JTabbedPane();
        //tabbedPane1.setEnabledAt(2,false);//returns out of bound error???


        //***************
        //1st tab code
        tableModel = new DefaultTableModel(data, columnNames);      //set up a table
        componentSelector = new JComboBox(partList);                //set up drop down menu
        componentSelector.addActionListener(new ActionListener() {  //event listener
            public void actionPerformed(ActionEvent e) {            //executed when an item is selected from the drop down menu
                String selectedItem = (String) componentSelector.getSelectedItem();
                displayQuery(selectedItem);                         //call function to draw table
            }
        });

        componentTable = new JTable();
        componentTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){                     //execute when ENTER is pressed
                    int row = componentTable.getSelectedRow();              //get row
                    int column = componentTable.getSelectedColumn();        //get column
                    String sql = "UPDATE components SET ";                  //create sql statement

                    for(int x = 0; x< componentTable.getColumnCount(); x++){//for loop to extend sql statement
                        if(x!= componentTable.getColumnCount()-1){
                            sql+="`"+columnNames[x]+"`='"+ componentTable.getValueAt(row,x).toString()+"', ";
                        } else {
                            sql+="`"+columnNames[x]+"`='"+ componentTable.getValueAt(row,x).toString()+"' WHERE ";
                        }
                    }

                    for(int x = 0; x< componentTable.getColumnCount(); x++){//for loop to extend sql statement
                        if(x!=column){
                            sql+="`"+columnNames[x]+"`='"+ componentTable.getValueAt(row,x).toString()+"' AND ";
                        }
                    }
                    sql+="1=1";                                             //deals with the leftover AND at the end
                    if (db.updateDatabase(sql)) {                           //updates database and shows error or success message
                        successLabel.setVisible(true);
                        errorLabel.setVisible(false);
                    } else {
                        successLabel.setVisible(false);
                        errorLabel.setVisible(true);
                    }
                }
            }
        });
        displayQuery(partList[0]);                                          //displays table for the first item

        //**********************
        //2nd tab code
        addComponentSelector = new JComboBox(partList);
        save = new JButton();
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                //parse values
                String nameEntered = nameTextField.getText();
                String quantityEntered = quantityTextField.getText();
                String  priceEntered = priceTextField.getText();
                String  modelEntered = modelTextField.getText();
                String  comboEntered = (String) addComponentSelector.getSelectedItem();
                //disable error messages
                invFieldsErr.setVisible(false);
                invPriceErr.setVisible(false);
                invQuantityErr.setVisible(false);
                invSuccess.setVisible(false);
                boolean error=false;

                if(nameEntered.length()==0||quantityEntered.length()==0||priceEntered.length()==0||modelEntered.length()==0){//check for empty fields
                    error=true;
                    invFieldsErr.setVisible(true);                             //display error
                }
                if(!quantityEntered.matches("[0-9]+")){                 //checks if values contain numbers only
                    invQuantityErr.setVisible(true);                           //display error
                    error=true;
                }
                if(!priceEntered.matches("[0-9]+")){                    //checks if values contain numbers only
                    invPriceErr.setVisible(true);                             //display error
                    error=true;
                }
                if(!error){
                    String sql = "INSERT INTO `components` (`Name`, `Model Number`, `Price`, `Quantity`, `Type`) VALUES ('"+nameEntered+"', '"+modelEntered+"', '"+priceEntered+"', '"+quantityEntered+"', '"+comboEntered+"')";
                    db.updateDatabase(sql);
                    invSuccess.setVisible(true);
                }
            }
        });
        //********************************
        //3rd tab code
        newStatus = new JComboBox(new String[]{"No", "Yes"});
        while (true){                                                                   //random id generator
            Random random = new Random();
            int randomNumber = random.nextInt(9999999 + 1 - 1000000) + 9999999; //gets a random number
            String id = "C"+ randomNumber;
            String sql= "SELECT * FROM `employee login` WHERE `ID` = '"+id+"'";         //sql statement
            if(!db.isDuplicate(sql)){                                                   //check if not a duplicate
                newID = new JTextField(id);                                             //displays the new id in the textfield
                break;                                                                  //exit loop
            }
        }

        newSave = new JButton();
        if(managerStatus){                                          //adds button functionality for managers only
            newSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    //disable errors
                    newDateErr.setVisible(false);
                    newSurnameErr.setVisible(false);
                    newNameErr.setVisible(false);
                    newSalaryErr.setVisible(false);
                    newPswRepeatErr.setVisible(false);
                    newPswErr.setVisible(false);
                    newFieldsReqErr.setVisible(false);
                    newSuccessMsg.setVisible(false);
                    //parse values
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
                        newFieldsReqErr.setVisible(true);           //displays error
                        error=true;
                    }
                    if(newPswVal.length()<10){                      //password length check
                        newPswErr.setVisible(true);                 //displays error
                        error=true;
                    }
                    if(!newPswVal.equals(newPswRepeatVal)){         //checks if passwords match
                        newPswRepeatErr.setVisible(true);           //displays error
                        error=true;
                    }
                    if(!newSalaryVal.matches("[0-9]+")){     //checks if values contain numbers only
                        newSalaryErr.setVisible(true);              //displays error
                        error=true;
                    }
                    if(!newNameVal.matches("[a-zA-Z]")){      //checks if values contain letters only
                        newNameErr.setVisible(true);                //displays error
                        error=true;
                    }
                    if(!newNameVal.matches("[a-zA-Z]")){     //checks if values contain letters only
                        newSurnameErr.setVisible(true);             //displays error
                        error=true;
                    }
                    if(newYearVal.length()==0||newMonthVal.length()==0||newDayVal.length()==0){//checks for empty date values
                        error=true;
                        newDateErr.setVisible(true);                //displays error
                    } else {
                        try{
                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            df.setLenient(false);
                            df.parse(date);                         //checks if date is valid
                        }
                        catch (Exception ex){
                            error=true;
                            newDateErr.setVisible(true);            //displays error
                        }
                    }

                    if(!error){//if form is correct
                        //sql statements
                        String sql1="INSERT INTO `employees` (`ppsn`, `name`, `surname`, `dob`, `salary`, `manager status`) VALUES ('"+newPPSNVal+"', '"+newNameVal+"', '"+newSurnameVal+"', '"+date+"', '"+newSalaryVal+"', '"+newStatusVal+"')";
                        String sql2="INSERT INTO `employee login` (`ID`, `password`, `ppsn_FK`) VALUES ('"+newIDVal+"', '"+newPswVal+"', '"+newPPSNVal+"')";
                        if(db.insert(sql1)&&db.insert(sql2)){       //executes both sql statements and checks if they were successful
                            newSuccessMsg.setVisible(true);         //displays success message
                        }
                    }
                }
            });
        }
    }



    public void displayQuery(String selectedItem){
        try {
            ResultSet rs = db.getComponent(selectedItem);   //gets the sql data from the selected item and stores it into rs
            Object[] values = new Object[5];

            while (tableModel.getRowCount() > 0) {          //removes old table
                tableModel.removeRow(0);
            }
            while (rs.next()) {                             //parses new values
                values = new Object[5];
                values[0] = rs.getString(1);
                values[1] = rs.getString(2);
                values[2] = rs.getString(3);
                values[3] = rs.getString(4);
                values[4] = rs.getString(5);

                tableModel.addRow(values);                  //adds new values to the table
            }
            componentTable.setModel(tableModel);
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    //draws the UI
    public static void showUI(ResultSet rs) {
        try {
            rs.next();
            if (rs.getString(2).equals("Yes")) {//checks if user is a manager
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
        frame.setContentPane(new GUI().mainPanel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Login login = new Login();
        Login.createAndShowGUI();
    }

}
