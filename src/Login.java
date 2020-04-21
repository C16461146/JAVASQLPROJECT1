import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

class Login{
    static Database db=new Database();
    static JFrame frame = new JFrame("PC Shop");

    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        //adds username label
        JLabel loginStatus = new JLabel(" ");
        JLabel usernameLabel = new JLabel("ID:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(usernameLabel);
        //adds username field
        JTextField usernameField = new JTextField(20);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(usernameField);
        //adds password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(passwordLabel);
        //adds password field
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(passwordField);

        //for testing purposes
        usernameField.setText("C16461146");
        passwordField.setText("test");
        //login button
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(loginButton);
        //sets up a listener for a button click
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String sql="SELECT `ppsn_FK` FROM `employee login` WHERE ID = '"+usernameField.getText()+"' and password='"+passwordField.getText()+"'";
                ResultSet rs=db.checkLogin(sql);
                if(rs!=null){//if database returns a match
                    frame.dispose();//closes login screen
                    GUI gui=new GUI();
                    gui.showUI(rs);//shows main ui screen
                }
                else{
                    loginStatus.setText("Incorrect login");//if login fails, display an error message
                }
            }
        });
        //adds a login status field
        loginStatus.setForeground(Color.red);
        loginStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(loginStatus);
    }

    public static void createAndShowGUI() {
        //Create and set up the window.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}