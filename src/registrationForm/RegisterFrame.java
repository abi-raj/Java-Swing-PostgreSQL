package registrationForm;
import dbhelper.DatabaseHelper;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import usermodel.UserModel;
import utilities.DateLabelFormatter;
import utilities.ValidationHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class RegisterFrame extends JFrame implements ActionListener {
    final String[] departments = {"CSE", "IT", "EEE", "ECE", "MECH", "MCT", "CSBS"};
    String gender = "";
    Container container = getContentPane();
    //All Labels
    JLabel regNoLabel = new JLabel("Register No.");
    JLabel nameLabel = new JLabel("Name");
    JLabel deptLabel = new JLabel("Department");
    JLabel dobLabel = new JLabel("Date of Birth");
    JLabel genderLabel = new JLabel("Gender");
    JLabel emailLabel = new JLabel("Email Id");
    JLabel mobileLabel = new JLabel("Mobile Number");
    JLabel passwordLabel = new JLabel("Create Password");
    JLabel rePasswordLabel = new JLabel("Re-enter Password");
    JLabel passwordMatcherLabel = new JLabel("");
    JButton registerButton = new JButton("Register");
    //DatePicker
    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    //Dropdown
    JComboBox<String> dropdown = new JComboBox<String>(departments);
    //All Text Fields
    JTextField regNoField = new JTextField();
    JTextField nameField = new JTextField();

    JRadioButton maleRadio = new JRadioButton("Male");
    JRadioButton femaleRadio = new JRadioButton("Female");

    ButtonGroup rbg = new ButtonGroup();

    JTextField emailField = new JTextField();
    JTextField mobileField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField rePasswordField = new JPasswordField();

    RegisterFrame() {

        setVisible(true);
        setBounds(10, 10, 500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setTitle("Register");
        addComponentsToContainer();
        setComponentBounds();
        setComponentActions();
        container.setLayout(null);
    }

    public static void main(String[] args) {
        RegisterFrame frame = new RegisterFrame();
    }

    public void addComponentsToContainer() {
        rbg.add(maleRadio);
        rbg.add(femaleRadio);
        //Adding each components to the Container
        container.add(regNoLabel);
        container.add(nameLabel);
        container.add(deptLabel);
        container.add(dobLabel);
        container.add(genderLabel);
        container.add(emailLabel);
        container.add(mobileLabel);
        container.add(registerButton);
        container.add(passwordLabel);
        container.add(rePasswordLabel);
        container.add(passwordMatcherLabel);
//textfields
        container.add(regNoField);
        container.add(nameField);
        container.add(dropdown);
        container.add(datePicker);
        container.add(maleRadio);
        container.add(femaleRadio);
        container.add(emailField);
        container.add(mobileField);
        container.add(passwordField);
        container.add(rePasswordField);


    }

    public void setComponentBounds() {
        regNoLabel.setBounds(50, 150, 100, 30);
        nameLabel.setBounds(50, 220, 100, 30);
        deptLabel.setBounds(50, 290, 100, 30);
        dobLabel.setBounds(50, 360, 100, 30);
        genderLabel.setBounds(50, 430, 100, 30);
        emailLabel.setBounds(50, 500, 100, 30);
        mobileLabel.setBounds(50, 560, 100, 30);
        passwordLabel.setBounds(50, 600, 100, 30);
        rePasswordLabel.setBounds(200, 600, 150, 30);
        registerButton.setBounds(50, 690, 100, 30);
        //text fields
        regNoField.setBounds(200, 150, 150, 30);
        nameField.setBounds(200, 220, 150, 30);
        dropdown.setBounds(200, 290, 100, 30);
        datePicker.setBounds(200, 360, 200, 30);
        maleRadio.setBounds(200, 430, 100, 30);
        femaleRadio.setBounds(300, 430, 100, 30);
        emailField.setBounds(200, 500, 150, 30);
        mobileField.setBounds(200, 560, 150, 30);
        passwordField.setBounds(50, 630, 120, 30);
        rePasswordField.setBounds(200, 630, 120, 30);
        passwordMatcherLabel.setBounds(350, 630, 120, 30);

    }

    public void setComponentActions() {
        registerButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (maleRadio.isSelected()) {
            gender = "Male";
        } else if (femaleRadio.isSelected()) {
            gender = "Female";
        }
        if (e.getSource() == registerButton) {
            if (!(passwordField.getText().toString().equals(rePasswordField.getText().toString()) && passwordField.getText().toString().length() > 0)) {
                passwordMatcherLabel.setText("Doesn't match");
                passwordMatcherLabel.setForeground(Color.red);

            } else {
                passwordMatcherLabel.setText("Passwords match");
                passwordMatcherLabel.setForeground(Color.blue);
                if (validateAll()) {
                    DatabaseHelper dbhelper = new DatabaseHelper();
                    String reg = regNoField.getText().toString();
                    String name = nameField.getText().toString();
                    String dob = datePicker.getJFormattedTextField().getText();
                    String mobile = mobileField.getText().toString();
                    int dept = dropdown.getSelectedIndex();
                    String email = emailField.getText().toString();
                    String password = passwordField.getText().toString();
                    UserModel user = new UserModel(reg, name, dept, dob, gender, email, mobile, password);
                    if (dbhelper.createUser(user)) {
                        JOptionPane.showMessageDialog(this, "Registered successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, "Registration Failure!");
                    }
                }
            }
        }
    }

    public boolean validateAll() {
        ValidationHelper valid = new ValidationHelper();
        if (!valid.checkRegNo(regNoField.getText().toString())) {
            JOptionPane.showMessageDialog(this, "Enter a Valid Register No\n Example : 19EUCS001");
            return false;
        }
        if (!(nameField.getText().toString().length() > 0)) {
            JOptionPane.showMessageDialog(this, "Name should not be empty");
            return false;
        }

        if (!(valid.checkDate(datePicker.getJFormattedTextField().getText()))) {
            JOptionPane.showMessageDialog(this, "Date should not be empty");
            return false;
        }
        if (!(gender.length() > 0)) {
            JOptionPane.showMessageDialog(this, "Please choose a gender");
            return false;
        }
        System.out.println(gender);
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



