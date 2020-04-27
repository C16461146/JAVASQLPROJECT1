import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class purchasesGUI extends JFrame{
    Database db = new Database();
    private JTable customersInfo;
    private JPanel purchasesPanel;
    private JButton saveToPurchasesButton;
    private JLabel successPurchaseMsg;
    private JLabel Customers;
    private JLabel errorPurchaseMsg;
    DefaultTableModel tableModel;
    String purchaseAmountGlobal;

    public purchasesGUI(String purchaseFromGuiClass){
        purchaseAmountGlobal = purchaseFromGuiClass;
    }

    public void displayPurchaseWindow(String purchaseAmount) {
        JFrame frame = new JFrame("Add to Purchases");
        createUIComponents();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setContentPane(new purchasesGUI(purchaseAmount).purchasesPanel);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        purchaseAmountGlobal = purchaseAmount;
    }

    public void displayCustomerTable(){
        String [][] customerData = {{"ID","Name","Surname","Phone"}};
        String[] customerColumnNames = {"ID","Name", "Surname", "Phone Number"};

        customersInfo = new JTable();
        tableModel = new DefaultTableModel(customerData,customerColumnNames);
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
        saveToPurchasesButton = new JButton();
        saveToPurchasesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = customersInfo.getSelectedRow();
                int column = 0;

                Object customerID = customersInfo.getValueAt(row,column);

                String sql = "INSERT INTO `purchases` (`customerID`,  `PurchaseAmount`, `RefundStatus`) VALUES ('"+customerID+"', '"+purchaseAmountGlobal+"','No')";

                if(db.insert(sql)){       //executes  sql statement and checks if they were successful
                    successPurchaseMsg.setVisible(true);         //displays success message
                } else {
                    errorPurchaseMsg.setVisible(true);
                }
            }
        });
    }
}
