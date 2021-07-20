package utilities;

import java.util.regex.*;

public class ValidationHelper {

private static final String emailRegex =".+\\@.+\\..+";
private static  final String mobileRegex= "^\\d{10}$";
private static  final String regNoRegex="^\\d{2}\\w{4}\\d{3}$";


 public boolean checkEmail(String email){
    Pattern pattern =  Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
    public boolean checkMobile(String mobile){
    Pattern pattern = Pattern.compile(mobileRegex);
    Matcher matcher = pattern.matcher(mobile);
    return matcher.matches();
}
    public  boolean checkRegNo(String regno){
        Pattern pattern = Pattern.compile(regNoRegex);
        Matcher matcher = pattern.matcher(regno);
        return matcher.matches();
    }
     public boolean checkDate(String date){
        return date.length() > 0;
    }

}
