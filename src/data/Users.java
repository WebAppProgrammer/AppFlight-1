package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import utilities.Crypto;

public class Users {
	private static final String loginFileName = "WEB-INF\\classes\\data\\users.properties";
	private String path;
	private Properties loginInfo;
	
	public Users(String path) {
		super();
		this.path = path;
    	loginInfo = new Properties();
    	InputStream inStream = null;
		try {
			inStream = new FileInputStream(this.path + loginFileName);
			loginInfo.load(inStream);
		}catch(FileNotFoundException e){
			System.out.println("Unable to read file");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("Unable to load login properties");
			e.printStackTrace();
		}finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int addUser(String username, String password){
		int returnVal = 2;
		boolean isDuplicated = loginInfo.containsKey(username);
		if(isDuplicated){
			System.out.println("Found duplicated");
			returnVal = 1;
		}else{
			String hashPass = Crypto.hash(password);
			loginInfo.setProperty(username, hashPass);
			OutputStream outStream = null;
			try {
				outStream = new FileOutputStream(this.path + loginFileName, true);
				loginInfo.store(outStream, null);
				returnVal = 0;
				System.out.println("Property saved");
			}catch(FileNotFoundException e){
				System.out.println("Unable to create or access file");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Unable to save login properties");
				e.printStackTrace();
			}finally {
				System.out.println("Finally");
				if (outStream != null) {
					try {
						outStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		 
			}
		}
		return returnVal;
	}
	
	public int checkLogin(String username, String password){
		int returnVal = 2;
		if(loginInfo.containsKey(username)){
			String hashPass = Crypto.hash(password);
			if(hashPass.equals(loginInfo.getProperty(username))){
				returnVal = 0;
			}
		}else{
			returnVal = 1;
		}
		return returnVal;
	}	
	
}
