import java.util.Scanner;
import java.util.concurrent.Callable;

public class ArraySearch {
	
	//find an element such that sum of left and right are same;
		public static int midSumIndex(int a[]) {
			int sum = 0;
			int n = a.length;
			for(int i=0;i<n;i++) {
				sum+=a[i];
			}
			int leftSum=0,rightSum=sum;
			for(int i=0;i<n;i++) {
				if(i>=1) {
					leftSum+=a[i-1];
				}
				rightSum-=a[i];
				
				if(leftSum==rightSum)
					return i+1;
			}
			return -1;
		}
		
		public static void main(String s[]) {
			
			Scanner sc = new Scanner(System.in);
			int t = sc.nextInt();
			while(t-->=0) {
				int n = sc.nextInt();
				int a[] = new int[n];
				for(int i=0;i<n;i++)
					a[i] = sc.nextInt();
				System.out.println(midSumIndex(a));
			}
			//int arr[] = { 2, 3, 4, 1, 4, 5 };
			
			
		}
	
	
}
