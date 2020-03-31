package storepasswords;
import java.util.Map;

/*      Colin Kugler
 * 		gihtub-ColinLouis95
 * 
 * 		This is a class that handles all conversion/security of the Password Vault
 * 		-convertMap will take in a map(this is by <String,Value>) and using StringBuilder create a string represenation of the map
 * 		-encrypt and decrypt work with convertMap since that the map converted to a string, and either encode or decode each letter.
 */

public class PasswordCipher {
	
	public PasswordCipher() {
		
	}
	
	protected String convertMap(Map<String,Values> map) {
		StringBuilder mapToString = new StringBuilder("");
		for(String key: map.keySet()) {
			mapToString.append(key + " " + map.get(key) + ", ");
		}
		mapToString.delete(mapToString.length()-2, mapToString.length());
		return mapToString.toString();
	}
	
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
