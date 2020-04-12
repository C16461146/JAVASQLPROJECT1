import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    Database db=new Database();
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTable table1,table2;
    private JLabel welcome;
    private JScrollPane scoller2;
    private JFrame frameGlobal;
    public static boolean loggedIn=false;
    DefaultTableModel tableModel,tableModel2 ;



    public void createUIComponents() {
        String[] partList={"CPU","GPU","MotherBoard","Storage","PSU"};
        String[] columnNames = { "Name", "Model", "Price", "Quantity", "Type"};
        String[][] data = {{ "Name", "Model", "Price", "Quantity", "Type"}};
        tableModel = new DefaultTableModel(data,columnNames);

        //,{ "Name", "Model", "Price", "Quantity", "Type"}
        //  stuff


        //data structure is just used as a test
        comboBox1=new JComboBox(partList);
        comboBox1.addActionListener(new ActionListener(){
            // TODO: Update the table
            public void actionPerformed(ActionEvent e) {//this gets executed whenever you select an item from the drop down menu
                String selectedItem = (String) comboBox1.getSelectedItem();
                ResultSet rs = db.getComponent(selectedItem);//gets the sql data from the selected item and stores it into rs
                //rs contains the sql data. rs.next() is used to access the next row. rs.getString(index) is used to access individual "cells"
                try{

                    displayQuery(selectedItem,rs);

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

        table1=new JTable();
        //table1.setModel(tableModel);
        welcome=new JLabel("test");

        table1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){

                    int row = table1.getSelectedRow();
                    int column = table1.getSelectedColumn();

                    System.out.println("COLUMN >>> "+column);

                    String updatedCellValue = table1.getValueAt(row,column).toString();



                    System.out.println("Row >>> "+row);

                    updateDisplay(updatedCellValue,row,column);

                }
            }
        });
       // table1.setDefaultEditor(Object.class, null);

    }
    public void updateDisplay(String updatedCellValue,int row, int column){
        String sSQL = "";
        if (column==1){=
            sSQL = "UPDATE `components` SET `Name` = ("+updatedCellValue+") WHERE ("+row+") = row";
        }
        if (column==2){
            sSQL =   "UPDATE components SET Model Number=? WHERE ID=("+updatedCellValue+")" ;
        }
        if (column==3){
            sSQL =   "UPDATE components SET Price=? WHERE ID=("+updatedCellValue+")" ;
        }
        if (column==4){
            sSQL =   "UPDATE components SET Quantity=? WHERE ID=("+updatedCellValue+")" ;
        }
        if (column==5){
            sSQL =   "UPDATE components SET Type=? WHERE ID=("+updatedCellValue+")" ;
        }
        System.out.println("Column Number "+column);
        db.updateDatabase(updatedCellValue,sSQL,column);
    }


    public void displayQuery(String selectedItem,ResultSet rs) throws SQLException {
        System.out.println(""+selectedItem);

        Object[] values = new Object[5];
        boolean changed = false;

        while (tableModel.getRowCount()>0)
        {
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

            // clear table if dropdown is changed with existing data
          /*  if(changed = true){
                tableModel.setRowCount(0);
                tableModel.addRow(values.);
                table1.setModel(tableModel);

            }
            else{
                table1.setModel(tableModel);
                changed = true;
            }*/
        }



    //For commit asdasdsaddasdsd
    public static void showUI(ResultSet rs){

        try {
            rs.next();

            if(rs.getString(2).equals("Yes")){
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
        Login login=new Login();
        Login.createAndShowGUI();
    }
}
