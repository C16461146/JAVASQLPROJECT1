import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GUI {
    Database db = new Database();
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTable table1, table2;
    private JLabel welcome;
    private JTextField NameTextField;
    private JTextField QuantityTextField;
    private JTextField PriceTextField;
    private JTextField ModelTextField;
    private JComboBox TypeComboBox;
    private JButton Save;
    private JScrollPane scoller2;
    private JFrame frameGlobal;
    public static boolean loggedIn = false;
    DefaultTableModel tableModel, tableModel2;




    public void createUIComponents() {
        String[] partList = {"CPU", "GPU", "MotherBoard", "Storage", "PSU"};
        String[] columnNames = {"Name", "Model", "Price", "Quantity", "Type"};
        String[][] data = {{"Name", "Model", "Price", "Quantity", "Type"}};
        tableModel = new DefaultTableModel(data, columnNames);

        TypeComboBox = new JComboBox(partList);

        //,{ "Name", "Model", "Price", "Quantity", "Type"}
        //  stuff
        //data structure is just used as a test

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
        //table1.setModel(tableModel);
        welcome = new JLabel("test");

            Save = new JButton();

            Save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {

                    String nameEntered = NameTextField.getText();
                    String quantityEntered = QuantityTextField.getText();
                    String  priceEntered =PriceTextField.getText();
                    String  modelEntered = ModelTextField.getText();
                    String  comboEntered = (String) TypeComboBox.getSelectedItem();

                      String sSQL = "INSERT INTO `components` (`Name`, `Model Number`, `Price`, `Quantity`, `Type`) VALUES ('"+nameEntered+"', '"+modelEntered+"', '"+priceEntered+"', '"+quantityEntered+"', '"+comboEntered+"')";
                        db.updateDatabase(sSQL);
                }
            });



        table1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    int row = table1.getSelectedRow();
                    int column = table1.getSelectedColumn();

                    String[] columnName = {"Name", "Model Number", "Price", "Quantity", "Type"};

                    System.out.println("COLUMN >>> " + column);

                    String updatedCellValue = table1.getValueAt(row, column).toString();
                    String sSQL = "UPDATE components SET `" + columnName[column] + "`='" + updatedCellValue + "' WHERE ";

                    for (int x = 0; x < table1.getColumnCount(); x++) {
                        if (x != column) {
                            sSQL += "`" + columnName[x] + "`='" + table1.getValueAt(row, x).toString() + "' AND ";
                        }
                    }
                    sSQL += "1=1";
                    db.updateDatabase(sSQL);

                }
            }
        });


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
                //if manager
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
        //comboBox1.add();
        //frame.add(comboBox1);
        frame.setSize(800, 200);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Login login = new Login();
        Login.createAndShowGUI();
    }

}
