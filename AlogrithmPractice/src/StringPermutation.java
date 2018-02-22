import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringPermutation {

		private static void permuteUtil(char[] charset,int start,int end) {
			int n = charset.length;
			if(start >=end || start>n ) return;
			
			for(int i=start;i<=end;i++) {
				for(int j=start+1;j<=end;j++) {
					charset[start] = (char) (charset[start] + charset[end]);
					charset[end] = (char) (charset[start] - charset[end]);
					charset[start] = (char) (charset[start] - charset[end]);
					System.out.println(Arrays.toString(charset));
					permuteUtil(charset,i,j);
				}
			}
		}
		
		private static void permuteUtilIterative(char c[]) {
			int n = c.length;
			for(int i=0;i<n-1;i++) {
				for(int j=i+1;j<n;j++) {
					if()
				}
			}
		}
		
		private static void swap(char[] a,int index1,int index2) {
			a[index1] = (char)(a[index1] + a[index2]);
			a[index2] = (char)(a[index1] - a[index2]);
			a[index1] = (char)(a[index1] - a[index2]);
		}
		public static void generatePermutation(String s){
			char[] c = s.toCharArray();
			int n = c.length;
			permuteUtil(c,0,n-1);
		}
		public static void doTest() {
			String s = "abc";
			generatePermutation(s);
		}
		public static void main(String a[]) {
			doTest();
		}
}
