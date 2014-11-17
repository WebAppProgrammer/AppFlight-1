package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
	
	public static boolean isInteger(String input){
        try{
            double complete = Double.parseDouble(input);
            double toTest = complete%1;
            if (toTest == 0)
                return true;
        }
        catch (NumberFormatException e){
            return false;
        }
        return false;
    }
	
	public static boolean isValidEmail(String email){
		String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
