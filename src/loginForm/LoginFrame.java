package loginForm;

import dbhelper.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("Register No");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");

    LoginFrame() {
        setLayoutManager();
        setVisible(true);
        setLocationAndSize();
        addComponentsToContainer();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 370, 600);
        setResizable(true);
        addActionEvent();
    }

    public void setLayoutManager() {

        container.setLayout(null);
    }

    public void setLocationAndSize() {

        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);

        loginButton.setBounds(50, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        //Adding each components to the Container
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);

        container.add(loginButton);

    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText().toString();
            String password = passwordField.getText().toString();

            if (username.length() == 0 || password.length() == 0) {
                JOptionPane.showMessageDialog(this, "Enter a valid username and password");
            } else {
                try {
                    if (new DatabaseHelper().checkUserExists(username, password)) {
                        JOptionPane.showMessageDialog(this, "Login Success");
                    } else {
                        JOptionPane.showMessageDialog(this, "Login failure");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }

        }

    }
}
