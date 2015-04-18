package difftool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Application {

	public static void main(String[] args) {
		if (args.length != 2){
			System.out.println("HIBA: a paraméterlista nem megfelelő.");
			System.out.println();
		}else{
			String file1 = args[0];
			String file2 = args[1];
			System.out.println("A(z) " + file1 + " fájlt hasonlítom össze a(z) " + file2 + " fájllal.");
			BufferedReader br1 = null, br2 = null;
			try	{
				br1 = new BufferedReader(new FileReader(file1));
				
				try {
					br2 = new BufferedReader(new FileReader(file2));
					String line1, line2;
					line1 = br1.readLine();
					line2 = br2.readLine();
					int lines = 1;
					boolean matches = true;
					while (line1 != null && line2 != null) {
						line1 = line1.trim();
						line2 = line2.trim();
						if ( !line1.equals(line2) && !line1.equals("") && !line2.equals("") ) {
							System.out.println("A(z) " + file1 + " a következő sornál nem egyezik: " + lines);
							System.out.println();
							matches = false;
							return;
						}
						line1 = br1.readLine();
						line2 = br2.readLine();
						++lines;
					}
					if (line1 != null) {
						System.out.println("A(z) " + file1 + " a következő sornál nem egyezik: " + lines);
						System.out.println();
					}
					else if (line2 != null) {
						System.out.println("A(z) " + file1 + " a következő sornál nem egyezik: " + lines);
						System.out.println();
					}
					else if(matches){
						System.out.println("A(z) " + file1 + " tesztje sikeres volt.");
						System.out.println();
					}						
				} catch (IOException e) {
					System.out.println("HIBA: " + file2 + " megnyitása sikertelen.");
					System.out.println();
				} finally {
					br2.close();
				}
			} catch (IOException e) {
				System.out.println("HIBA: " + file1 + " megnyitása sikertelen.");
				System.out.println();
			} finally {
				if (br1 != null)
					try {
						br1.close();
					} catch (IOException e) {
						;
					}
			}
		}		
	}

}
