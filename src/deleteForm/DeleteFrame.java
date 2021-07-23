package deleteForm;

import dbhelper.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("Register No");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton deleteButton = new JButton("Delete");


    DeleteFrame() {
        setLayoutManager();
        setVisible(true);
        setLocationAndSize();
        addComponentsToContainer();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 370, 600);
        setResizable(true);
        setTitle("Delete");

    }

    public void setLayoutManager() {

        container.setLayout(null);
    }

    public void setLocationAndSize() {

        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);

        deleteButton.setBounds(50, 300, 100, 30);
        deleteButton.addActionListener(this);


    }

    public void addComponentsToContainer() {
        //Adding each components to the Container
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);

        container.add(deleteButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            if (!(userTextField.getText().toString().length() > 0 || passwordField.getText().toString().length() > 0)) {
                JOptionPane.showMessageDialog(this, "Enter all values!");
            } else {
                DatabaseHelper dbHelper = new DatabaseHelper();
                String regno = userTextField.getText().toString();
                String password = passwordField.getText().toString();
                if (!dbHelper.checkUserExists(regno, password)) {
                    JOptionPane.showMessageDialog(this, "User credential doesn't exist");
                } else {
                    dbHelper.deleteUser(regno, password);
                    JOptionPane.showMessageDialog(this, "Deleted Successfully");

                }
            }
        }
    }
}
