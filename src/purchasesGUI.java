import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class purchasesGUI extends JFrame{

      Database db = new Database();
      private JTable customersInfo;
      private JPanel purchasesPanel;
      DefaultTableModel tableModel;




    public void displayPurchaseWindow() {

        JFrame frame = new JFrame("Purchases");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new purchasesGUI().purchasesPanel);
        //Display the window.
        frame.pack();
        frame.setVisible(true);


    }
    public void displayCustomerTable(){
        String [][] customerData = {{"ID","Name","Surname","Phone"}};
        String[] customerColumnNames = {"ID","Name", "Surname", "Phone Number"};

        customersInfo = new JTable();
        tableModel = new DefaultTableModel(customerData,customerColumnNames);

        System.out.println("TEST");

        System.out.println("ROW COUNT>>> "+tableModel.getRowCount());
        ResultSet rs = db.getCustomerData();

        try {
            Object[] values = new Object[4];

            while (tableModel.getRowCount() > 0) {          //removes old table
                tableModel.removeRow(0);
            }
            while (rs.next()) {                             //parses new values
                values = new Object[4];
                values[0] = rs.getString(1);
                values[1] = rs.getString(2);
                values[2] = rs.getString(3);
                values[3] = rs.getString(4);

                System.out.println("data>>> "+ values[0]);
                System.out.println("data>>> " +values[1]);
                System.out.println("data>>> " +values[2]);
                System.out.println("data>>> " +values[3]);


                tableModel.addRow(values);                  //adds new values to the table
            }
            customersInfo.setModel(tableModel);
        } catch (SQLException ex) {                                                 //exception handling
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        displayCustomerTable();
    }
}
