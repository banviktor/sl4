package difftool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Application {

	public static void main(String[] args) {
		if (args.length != 2)
			System.out.println("HIBA: a param�terlista nem megfelel�.");
		String file1 = args[0];
		String file2 = args[1];
		System.out.println("A(z) " + file1 + " f�jlt hasonl�tom �ssze a(z) " + file2 + " f�jllal.");
		try (BufferedReader br1 = new BufferedReader(new FileReader(file1))) {
			try (BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
				String line1, line2;
				int lines = 0;
				while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
					if ( !line1.equals(line2) ) {
						System.out.println("A(z) " + file1 + " a k�vetkez� sorn�l nem egyezik: " + lines);
					}
					++lines;
				}
				System.out.println("A(z) " + file1 + " tesztje sikeres volt.");
			} catch (IOException e) {
				System.out.println("HIBA: " + file2 + " megnyit�sa sikertelen.");
			}
		} catch (IOException e) {
			System.out.println("HIBA: " + file1 + " megnyit�sa sikertelen.");
		}
	}

}
