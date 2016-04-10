import java.util.Scanner;

/**
 * Created by khan on 30.03.16. Lab#6
 */

class Test {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\\Z");
		try {
			Parser p = new Parser(in.next());
			p.parse();
			System.out.println("success");
		} catch (SyntaxError e) {
			System.out.println(e.getMessage());
		}
	}
}
