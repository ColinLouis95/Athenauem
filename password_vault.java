package storepasswords;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*  Colin Kugler
 *  
 *  gihtub-ColinLouis95
 *  
 *  Password Vault
 *  
 *  -this is a program that was made in computer science 1 class, project assigned by professor Tracy Dobbs
 *  -designed to create a Passwords & Accounts type password holder.
 *  -this is the text-based version.
 *  
 *  -class Values was created to be able to hold two Strings to be placed in a hashmaps value.
 *  -the key is a string, mine is the username, and the Value class holds the password and origin.
 *  
 *  - encryption and decryption methods are used to encode/decode the String versions of the HashMap (using convertMap)
 *  - the methods although work when printing them out, don't touch the original hashmap.
 *   
 */

// Values class to handle two strings(password,site) to be stored in password vault
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

// class to handle the function of the  Vault, creating a hashmap vault
public class password_vault extends PasswordCipher {
	private  final  Map<String,Values> vaultStorage = new HashMap<String,Values>();
	protected Scanner in = new Scanner(System.in);
	
	public password_vault() {
		Menu();
	}
	
// checks the vault to see if its empty
	private boolean checkVault() {
		boolean message;
		if(this.vaultStorage.isEmpty()) {
			message = true; 
			System.out.println("\nYour vault is empty, please add in your info.");
		}
		else {
			message = false;
			System.out.println("\nYour items in the vault, ");
		}
		return message;
	}
	
// prints out all contents of vault if not empty(setting vaultStorage as a string to print nicely
	protected void printAllInfo() {
		if(!checkVault()) {
		System.out.print("\n" + new String(convertMap(vaultStorage)));
		System.out.println();
		}
	}
	
//when updating password, change the name of the password, do not change anything about site.
	public String updatePassword(String user, String pass, String newPass) {
		Values v1 = new Values(pass,newPass);
		vaultStorage.replace(user,v1);
		return encrypt(new String(convertMap(vaultStorage)));
	}
	
// adds the username, password, and site to the vault
	public String addInfo(String user, String pass, String site) {
		vaultStorage.put(user, new Values(pass, site));
		return encrypt(new String(convertMap(vaultStorage)));
	}
	
// deletes the items from the vault
	public void deleteInfo(String user) {
		vaultStorage.remove(user);	
	}
	
// if the user wants to print out the contents of their vault to a file
// WARNING: CONTENTS IN FILE WILL BE DECODED
	private void vaultToFile() {
		try {
			String data = new String(convertMap(vaultStorage));
			System.out.println("Enter in file path/filename.txt you wish to save to:  ");
			String path = in.nextLine();
			BufferedWriter file = new BufferedWriter(new FileWriter(path));
			file.write(data);
			file.close();
			System.out.println("File has been stored.");
		}
		catch(IOException e){
			System.out.println("failed to write data to file");
			e.printStackTrace();
		}
		
	}

// Menu method representing the vault in text form, giving the user options to choose from to interact with vault
// using switch statement to control the user choices, 
// while start = true keeps the menu continuing with choice 6 exiting the system as end.
	public void Menu() {
		boolean start = true;
		int choice;
		
		while(start) {
			System.out.println("\nWelcome to the Password Vault!");
			System.out.println("\nChoose from the options below to get started!");
			System.out.println("\n1. Add to the Vault.");
			System.out.println("2. Update a password.");
			System.out.println("3. Display Vault contents.");
			System.out.println("4. Delete Vault contents.");
			System.out.println("5. Save Vault to file.");
			System.out.println("6. Exit the Vault.");
			System.out.print("\nEnter your option here: ");
			choice = in.nextInt();
			while(choice > 6);
			switch(choice) {
			
			case 1:
				System.out.print("Enter the username you want to add: ");
				String usera = in.next();
				System.out.print("Enter the password you want to add: ");
				String passa = in.next();
				System.out.print("Enter the origin of use you want to add: ");
				String origina = in.next();
				addInfo(usera,passa,origina);
				System.out.println("Thank you");
				break;
				
			case 2:
				System.out.print("Enter the username: ");
				String userb = in.next();
				System.out.print("Enter the password to change: ");
				String passb = in.next();
				System.out.print("Enter the new password: ");
				String originb = in.next();
				updatePassword(userb,passb,originb);
				System.out.println("The password has been updated.");
				break;
				
			case 3:
				printAllInfo();
				break;
				
			case 4:
				System.out.print("Enter the username from field you wish to delete: ");
				String userc = in.next();
				deleteInfo(userc);
				break;
				
			case 5:
				vaultToFile();
				break;
				
			case 6 :
				System.out.println("Thank you for using Password Vault. See ya around!");
				System.exit(0);
				break;
			}
	
		}
		
	}

	public static void main(String[] args) {
		password_vault pv = new password_vault();

	}
}
