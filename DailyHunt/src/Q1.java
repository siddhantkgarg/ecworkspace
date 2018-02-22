import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q1 {

	public static void main(String s[]) {
	Scanner sc = new Scanner(System.in);
	String str = sc.nextLine();
	Pattern p = Pattern.compile("-?\\d+");
	Matcher m = p.matcher(str);
	while (m.find()) {
	  System.out.println(m.group());
	}
        
		

	}
}
