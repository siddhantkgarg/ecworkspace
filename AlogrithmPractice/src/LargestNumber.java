import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class LargestNumber implements Comparator<Integer>{
	
	public static String largestNumber(Integer a[]) {
		int n = a.length;
		int processed=0;
		LargestNumber ln = new LargestNumber();
		Arrays.sort(a,ln);
		String output = "";
		for(int i=0;i<n;i++) {
			output+=(a[i] + " ");
		}
		return output;
	}
	public int compare(Integer a,Integer b) {
		String x = a.toString();
		String y = b.toString();
		return (-1)*((x+y).compareTo((y+x)));
	}
	
	
	public static void main(String s[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		while(t>0) {
			int n = sc.nextInt();
			Integer a[] = new Integer[n];
			for(int i=0;i<n;i++)
				a[i] = sc.nextInt();
			System.out.println(largestNumber(a));
			t--;
		}
	}
}
