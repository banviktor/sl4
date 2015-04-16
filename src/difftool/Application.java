package difftool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Application {

	public static void main(String[] args) {
		if (args.length != 2){
			System.out.println("HIBA: a paraméterlista nem megfelelő.");
		}else{
			String file1 = args[0];
			String file2 = args[1];
			System.out.println("A(z) " + file1 + " fájlt hasonlítom össze a(z) " + file2 + " fájllal.");
			try	{
				BufferedReader br1 = new BufferedReader(new FileReader(file1));
				
				try {
					BufferedReader br2 = new BufferedReader(new FileReader(file2));
					String line1, line2;
					int lines = 0;
					boolean matches = true;
					while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
						if ( !line1.equals(line2) && !line1.equals("") && !line2.equals("") ) {
							System.out.println("A(z) " + file1 + " a következő sornál nem egyezik: " + lines);
							matches = false;
							break;
						}
						++lines;
					}
					if(matches){
						System.out.println("A(z) " + file1 + " tesztje sikeres volt.");
					}						
					br2.close();
				} catch (IOException e) {
					System.out.println("HIBA: " + file2 + " megnyitása sikertelen.");
				} 
				
				br1.close();
			} catch (IOException e) {
				System.out.println("HIBA: " + file1 + " megnyitása sikertelen.");
			}
		}		
	}

}
