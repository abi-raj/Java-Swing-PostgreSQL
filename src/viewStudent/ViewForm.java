package viewStudent;

import dbhelper.DatabaseHelper;
import usermodel.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewForm extends JFrame implements ActionListener {
    final String[] departments = {"CSE", "IT", "EEE", "ECE", "MECH", "MCT", "CSBS"};
    Container container = getContentPane();
    //All Labels
    JLabel regNoLabel = new JLabel("Register No.");
    JLabel nameLabel = new JLabel("Name");
    JLabel deptLabel = new JLabel("Department");
    JLabel dobLabel = new JLabel("Date of Birth");
    JLabel genderLabel = new JLabel("Gender");
    JLabel emailLabel = new JLabel("Email Id");
    JLabel mobileLabel = new JLabel("Mobile Number");
    //text field
    JComboBox<String> regDropdown = new JComboBox<String>();

    JButton viewButton = new JButton("View Details");

    //fields
    JTextField nameField = new JTextField();
    JTextField deptField = new JTextField();
    JTextField dobField = new JTextField();
    JTextField genderField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField mobileField = new JTextField();

    ViewForm() {
        setVisible(true);
        setBounds(10, 10, 500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("View");
        container.setLayout(null);
        addComponentsToContainer();
        setContainerBounds();
        setInitialView();
        setComponentActions();
    }

    public static void main(String[] args) {
        ViewForm frame = new ViewForm();
    }

    public void addComponentsToContainer() {
        container.add(regNoLabel);
        container.add(regDropdown);
        container.add(viewButton);
        container.add(nameLabel);
        container.add(deptLabel);
        container.add(dobLabel);
        container.add(genderLabel);
        container.add(emailLabel);
        container.add(mobileLabel);
        //TextFiedls
        container.add(nameField);
        container.add(deptField);
        container.add(dobField);
        container.add(genderField);
        container.add(emailField);
        container.add(mobileField);
    }

    public void setContainerBounds() {
        regNoLabel.setBounds(30, 30, 100, 30);
        regDropdown.setBounds(130, 30, 100, 30);
        viewButton.setBounds(240, 30, 150, 30);
        nameLabel.setBounds(70, 200, 100, 30);
        deptLabel.setBounds(70, 250, 100, 30);
        dobLabel.setBounds(70, 300, 100, 30);
        genderLabel.setBounds(70, 350, 100, 30);
        emailLabel.setBounds(70, 400, 100, 30);
        mobileLabel.setBounds(70, 450, 100, 30);
        //fields
        nameField.setBounds(200, 200, 150, 30);

        deptField.setBounds(200, 250, 150, 30);
        dobField.setBounds(200, 300, 150, 30);
        genderField.setBounds(200, 350, 150, 30);
        emailField.setBounds(200, 400, 150, 30);
        mobileField.setBounds(200, 450, 150, 30);

    }

    public void setInitialView() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        ArrayList<String> list = dbHelper.allUsers();
        for (String str : list) {
            regDropdown.addItem(str);
        }

    }

    void setComponentActions() {
        viewButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        if (e.getSource() == viewButton) {
            DatabaseHelper dbHelper = new DatabaseHelper();
            UserModel student = dbHelper.viewUser(regDropdown.getItemAt(regDropdown.getSelectedIndex()));
            setState(student);
        }
    }

    public void setState(UserModel student) {
        if (student != null) {
            nameField.setText(student.getName());
            deptField.setText(departments[student.getDepartment()]);
            dobField.setText(student.getDob());
            genderField.setText(student.getGender());
            emailField.setText(student.getEmail());
            mobileField.setText(student.getMobile());

            //non editable
            nameField.setEditable(false);
            deptField.setEditable(false);
            dobField.setEditable(false);
            genderField.setEditable(false);
            emailField.setEditable(false);
            mobileField.setEditable(false);
        }
    }
}