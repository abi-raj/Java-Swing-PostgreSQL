package usermodel;

public class UserModel {

    private String regNo;
    private String name;
    private int department;
    private String dob;
    private String gender;
    private String email;
    private String mobile;
    private String password;

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel(String regNo, String name, int department, String dob, String gender, String email, String mobile, String password) {
        this.regNo = regNo;
        this.name = name;
        this.department = department;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }


}
