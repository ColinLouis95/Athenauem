package com.athenauem.version.gui;

import java.util.Map;


/*      Colin Kugler
 * 
 * 		gihtub-ColinLouis95
 * 
 * 		Anthenauem
 * 
 * 		This is a class that handles all conversion/security of the Athenauem
 * 		-convertMap will take in a map(this is by <String,Value>) and using StringBuilder create a string representation of the map
 * 		-encode and decode work with convertMap since that the map converted to a string, and either encode or decode each letter.
 * 
 * 		-any recommendations to improve code is welcomed via discord message.
 */

public class password_controller_GUI {
	
	public password_controller_GUI() {
		
	}	
	
// convertMap method for vault GUI	
	protected String convertMapUI(Map<String,ValuesUI> map) {
		StringBuilder mapToString = new StringBuilder("");
		for(String key: map.keySet()) {
			mapToString.append(key + " " + map.get(key) + "\n");
		}
		//mapToString.delete(mapToString.length()-2, mapToString.length());
		return mapToString.toString();
	}
	
// method for vault encryption	
	protected String encrypt(String e) {
		String encryptedVault = "";
		int key = 6;
		char letter;
		
		for(int i = 0; i < e.length(); i++) {
			letter = e.charAt(i);
			
			if(letter >= '!' && letter <= 'z') {
				letter = (char)(letter + key - 1);
				
				if(letter > 'z') {
					letter = (char)(letter - 'z' + '!' - 1);
				}
				encryptedVault += letter;
			}
			
			else if(letter >= 'A' && letter <= 'Z') {
				letter = (char)(letter + key - 1);
						
						if(letter > 'Z') {
							letter = (char)(letter - 'Z' + 'A' - 1);
						}
						encryptedVault += letter;
			}
			else {
				encryptedVault += letter;
			}
		}
		return encryptedVault;
	}
	
// method for vault decryption	
	protected String decrypt(String d) {
		String decryptedVault= "";
		int key = 6;
		char letter;
		for(int i = 0; i < d.length(); i++) {
			letter = d.charAt(i);
			
			if(letter >= '!' && letter <= 'z') {
				letter = (char)(letter - key + 1);
				
				if(letter < '!') {
					letter = (char)(letter + 'z' -'!' + 1);
				}
				decryptedVault += letter;
			}
			else if(letter >= 'A' && letter <= 'Z') {
				letter = (char)(letter - key + 1);
				
				if(letter < 'A') {
					letter = (char)(letter + 'Z' - 'A' + 1);
				}
				decryptedVault += letter;
			}
			else {
				decryptedVault += letter;
			}
		}
		return decryptedVault;
	}

}
