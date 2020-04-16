package com.athenauem.version.gui;
/*			Colin Kugler
 *  
 *  		github-ColinLouis95
 *  
 * 			Athenauem gui logic
 * 
 * 			-This class will create a backbone data layer for the GUI application layer of Athenauem
 * 			-The user will be able to interact with the vault using the functionality of the GUI layer
 * 
 * 			-athenauem_ui_logic controls the athenauem_ui application layer.
 * 			
 * 			-Differences between the UI athenauem and the text-based version include:
 * 				-menu function removed due to not being needed for UI.
 * 				-vaultToFile method has a String argument for file saving(user will have to type in full-path).
 * 				-checkVault method removed(displays empty vault in UI layer).
 * 				-printAllInfo method replaced by returnVaultUI for displaying vault contents.
 * 				
 * 
 * 			-any recommendations to improve code is welcomed via discord message.
 			
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


//Values class to handle two strings(password,site) to be stored in password vault in UI layer
class ValuesUI {
		protected String a;
		protected String b;

		
		public ValuesUI(String a, String b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return this.a + " " + this.b;
		}
	}

// UI layer of password-vault handling storage/updating/deleting vault contents
public class athenauem_ui_logic extends password_controller_GUI{
	private final Map<String,ValuesUI> vaultStorage = new HashMap<String,ValuesUI>();
	protected Scanner in = new Scanner(System.in);
	
// default init	
	public athenauem_ui_logic() {
		
	}

// method for displaying vault for UI	
	protected String returnVaultUI() {
		return new String(convertMapUI(vaultStorage));
	}
	
//when updating password, change the name of the password, do not change anything about site.
	protected String updatePassword(String user, String pass, String newPass) {
		ValuesUI v1 = new ValuesUI(pass,newPass);
		vaultStorage.replace(user,v1);
		return encrypt(new String(convertMapUI(vaultStorage)));
	}
	
// adds the username, password, and site to the vault
	protected String addInfo(String user, String pass, String site) {
		vaultStorage.put(user, new ValuesUI(pass, site));
		return encrypt(new String(convertMapUI(vaultStorage)));
	}
	
// deletes the items from the vault
	protected void deleteInfo(String user) {
		vaultStorage.remove(user);	
	}
	
// vaultToFile method for GUI
	protected void vaultToFileGUI(String filename) {
		String data;
		BufferedWriter file;
		try {
			data = new String(convertMapUI(vaultStorage));
			file = new BufferedWriter(new FileWriter(filename));
			file.write(data);
			file.close();
			System.out.println("File has been stored.");
		}
		catch(IOException e){
			System.out.println("failed to write data to file");
			e.printStackTrace();
		}
		
	}

}
