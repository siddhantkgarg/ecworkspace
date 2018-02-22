
public class StringReverse {

	public static String reverse(String x) {
		char c[] = x.toCharArray();
		int len = x.length();
		for(int i=0,j=len-1;i<j;i++,j--) {
			c[i]= (char) (c[i]+c[j]);
			c[j] = (char) (c[i]-c[j]);
			c[i] = (char)(c[i]-c[j]);
		}
		return new String(c);
	}

	public static void main(String s[]) {
		System.out.println("Hello");
		System.out.println(reverse("kaminak"));
	}
	
}
