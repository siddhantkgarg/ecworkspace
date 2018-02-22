import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class InputGen {

	
	public static void main(String s[]) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("C:/users/sigarg/desktop/inputcf.txt", "UTF-8");
		int t = 150;
		writer.println(t);
		
		
		while(t>0) {
			int n = 300000;
			int q = 300000;
			writer.println(n+" "+q);
			for(int i=1;i<=n-1;i++)
				writer.println(i + " " + (i+1) );
			for(int i=0;i<q-1;i++) {
				writer.println("1 " + (i+1) + " 10 " + "1000000000 1000000000");
			}
//			writer.println("1 2 10 1000000000 1000000000");
//			writer.println("1 3 10 1000000000 1000000000");
//			writer.println("1 10 10 1000000000 1000000000");
//			writer.println("1 299999 10 1000000000 1000000000");
			writer.println("2 10");
			t--;
		}
		
		
		writer.close();
	}
}
