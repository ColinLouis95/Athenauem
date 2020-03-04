package storepasswords;

import java.util.Map;

public class encryption {
	
	encryption(){
		
	}
	
	protected String convertMap(Map<String,Values> map) {
		StringBuilder mapToString = new StringBuilder("");
		for(String key: map.keySet()) {
			mapToString.append(key + " " + map.get(key) + ", ");
		}
		mapToString.delete(mapToString.length()-2, mapToString.length());
		return mapToString.toString();
	}
	
	protected String encrypt(String s) {
		String encryptedVault = "";
		int key = 6;
		char letter;
		
		for(int i = 0; i < s.length(); i++) {
			letter = s.charAt(i);
			//System.out.println(letter);
			
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
		return encryptedVault.replaceAll("\\s+", "");
		
	}

}
