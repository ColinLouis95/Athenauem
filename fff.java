package storepasswords;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

// values class to handle two strings(password,site) to be stored in password vault
class Values {
	String a;
	String b;

	
	public Values(String a, String b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public String toString() {
		return this.a + " " + this.b;
	}
}

// class to handle the function of the  Vault
public class fff {
	private static Map<String,Values> vaultStorage = new HashMap<String,Values>();
	
	public fff() {
		
	}
	
	public boolean checkVault() {
		boolean message;
		if(vaultStorage.isEmpty()) {
			message = true; 
			System.out.println("Your vault is empty, please add in your info.");
		}
		else {
			message = false;
			System.out.println("You have items in your vault!");
		}
		return message;
	}
	
	public void printAllInfo() {
		if(!checkVault()) {
		System.out.println(Arrays.asList(vaultStorage));
		}
	}
	
//when updating password, change the name of the password, do not change anything about site.
	public void updatePassword(String user, String pass, String newPass) {
		Values v1 = new Values(pass,newPass);
		vaultStorage.replace(user,v1);
	}
	
	public void addInfo(String user, String pass, String site) {
		vaultStorage.put(user, new Values(pass, site));
	}
	
	
	public void deleteInfo(String user) {
		//TODO:
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		fff f = new fff();
		f.checkVault();
		f.addInfo("username", "pass", "site@.com");
		f.printAllInfo();
		System.out.println();
		f.addInfo("colin", "kugler", "ADAF@.com");
		f.printAllInfo();
		f.updatePassword("username", "newpass","site@.com");
		System.out.println();
		f.printAllInfo();
		System.out.println();
		f.updatePassword("colin", "louis", "ADAF@.com");
		System.out.println();
		f.printAllInfo();
		System.out.println();
		
		encryption e = new encryption();
		String asd = e.convertMap(vaultStorage);
		System.out.println(asd);
		System.out.println();
		String en = e.encrypt(asd);
		System.out.println(en);
		System.out.println('a' + 5);
	
		
		
		
	}


}
