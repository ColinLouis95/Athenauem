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
	private  Map<String,Values> vaultStorage = new HashMap<String,Values>();
	
	public fff() {
		
	}
	
	
	public void printAllInfo() {
		System.out.println(Arrays.asList(vaultStorage));
	}
	
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
		f.addInfo("username", "pass", "site");
		f.printAllInfo();
		System.out.println();
		f.addInfo("colin", "kugler", "ADAF");
		f.printAllInfo();
		f.updatePassword("username", "pass", "lfklfgknjnag");
		System.out.println();
		f.printAllInfo();
		System.out.println();
		f.updatePassword("colin", "kugler", "louis");
		System.out.println();
		f.printAllInfo();

	}


}
