package updateForm;

import dbhelper.DatabaseHelper;
import usermodel.UserModel;
import utilities.ValidationHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateFrame extends JFrame implements ActionListener {
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
    JLabel confirmOLDPassword = new JLabel("Confirm OLD Password");
    JButton updateButton = new JButton("Update");
    //text field
    JComboBox<String> regDropdown = new JComboBox<String>();

    JButton viewButton = new JButton("View Details");

    //fields
    JTextField nameField = new JTextField();
    JComboBox<String> deptDropdown = new JComboBox<String>(departments);
    JTextField dobField = new JTextField();
    JTextField genderField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField mobileField = new JTextField();
    JPasswordField oldPassField = new JPasswordField();

    UpdateFrame() {
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
        UpdateFrame frame = new UpdateFrame();
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
        container.add(confirmOLDPassword);

        //TextFiedls
        container.add(nameField);
        container.add(deptDropdown);
        container.add(dobField);
        container.add(genderField);
        container.add(emailField);
        container.add(mobileField);
        container.add(oldPassField);
        container.add(updateButton);
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
        confirmOLDPassword.setBounds(70, 500, 100, 30);
        //fields
        nameField.setBounds(200, 200, 150, 30);

        deptDropdown.setBounds(200, 250, 150, 30);
        dobField.setBounds(200, 300, 150, 30);
        genderField.setBounds(200, 350, 150, 30);
        emailField.setBounds(200, 400, 150, 30);
        mobileField.setBounds(200, 450, 150, 30);
        oldPassField.setBounds(200, 500, 150, 30);
        updateButton.setBounds(150, 550, 150, 30);
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
        updateButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        if (e.getSource() == viewButton) {
            DatabaseHelper dbHelper = new DatabaseHelper();
            UserModel student = dbHelper.viewUser(regDropdown.getItemAt(regDropdown.getSelectedIndex()));
            setState(student);
        }
        if (e.getSource() == updateButton) {
            DatabaseHelper dbHelper = new DatabaseHelper();
            String regno = regDropdown.getItemAt(regDropdown.getSelectedIndex());
            String pass = oldPassField.getText().toString();
            if (!dbHelper.checkUserExists(regno, pass)) {
                JOptionPane.showMessageDialog(this, "Credentials Invalid");
            } else {
                if (validateAll()) {
                    //updated
                    String name = nameField.getText().toString();
                    String dob = dobField.getText().toString();
                    String mobile = mobileField.getText().toString();
                    int dept = deptDropdown.getSelectedIndex();
                    String gender = genderField.getText().toString();
                    String email = emailField.getText().toString();

                    UserModel user = new UserModel(regno, name, dept, dob, gender, email, mobile, pass);
                    if (dbHelper.updateUser(user)) {
                        JOptionPane.showMessageDialog(this, "Updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Updation Failure!");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Please fill all values");
                }
            }
        }
    }

    public void setState(UserModel student) {
        if (student != null) {
            nameField.setText(student.getName());
            deptDropdown.setSelectedIndex(student.getDepartment());
            dobField.setText(student.getDob());
            genderField.setText(student.getGender());
            emailField.setText(student.getEmail());
            mobileField.setText(student.getMobile());
            oldPassField.setText(student.getPassword());

            //non editable
//            nameField.setEditable(false);
//            deptField.setEditable(false);
//            dobField.setEditable(false);
//            genderField.setEditable(false);
//            emailField.setEditable(false);
//            mobileField.setEditable(false);
        }
    }

    public boolean validateAll() {
        ValidationHelper valid = new ValidationHelper();

        if (!(nameField.getText().toString().length() > 0)) {
            JOptionPane.showMessageDialog(this, "Name should not be empty");
            return false;
        }

        if (!(valid.checkDate(dobField.getText().toString()))) {
            JOptionPane.showMessageDialog(this, "Date should not be empty");
            return false;
        }
        if (!(genderField.getText().toString().length() > 0)) {
            JOptionPane.showMessageDialog(this, "Please choose a gender");
            return false;
        }

        if (!valid.checkEmail(emailField.getText().toString())) {
            JOptionPane.showMessageDialog(this, "Enter a valid email");
            return false;
        }
        if (!valid.checkMobile(mobileField.getText().toString())) {
            JOptionPane.showMessageDialog(this, "Enter a valid 10 digit Mobile number");
            return false;
        }

        return true;
    }

}
