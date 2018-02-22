import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NextGreaterNumber {
	
	//Wrong to search for smaller digit and sort.
	public static int nextGreaterNumber(int N) {
		int copy = N;
		List<Integer> a = new ArrayList<Integer>();
		while(copy>0) {
			a.add(copy%10);
			copy/=10;
		}
		int start=0;
		int end = 1;
		int len = a.size();
		while(start < len-1) {
			if(a.get(end)<a.get(start)) {
				Collections.swap(a, start, end);
				Collections.sort(a.subList(0, end-1));
				break;
			}
			if(end==len-1)
			{
				start++;
				end = start+1;
			}
		}
		
		// again form the number;
		int ans=0;
		for (Integer digit : a) {
			ans=ans*10+digit;
		}
		return ans;
	}
	
	
	//For geeksforgeeks /
	//wrong
	public static Integer[] nextGreaterNumberWrong(Integer a[],int n) {
		//System.out.println(Arrays.asList(a).toString());
		int start=0;
		int end = 1;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if(a[j]<a[i]) {
					a[j] = a[i]+a[j];
					a[i] = a[j] - a[i];
					a[j] = a[j] - a[i];
					Arrays.sort(a, i, j,Collections.reverseOrder());
					List<Integer> rev = Arrays.asList(a);
					Collections.reverse(rev);
					return (Integer[]) rev.toArray(); 
				}
			}
		}
		return a;
	}
	
	public static Integer[] nextGreaterNumber(Integer a[],int n) {
		for(int i=1;i<n;i++) {
			if(a[i]<a[i-1]) {
				//find the smallest digit greater than this digit.
				int min = Integer.MAX_VALUE;
				int minIndex = i;
				for(int j=i-1;j>=0;j--) {
					if(a[j]>a[i] && a[j] < min ) {
						minIndex = j;
						min = a[j];
					}
				}
				//swap
				a[i] = a[i] + a[minIndex];
				a[minIndex] = a[i] - a[minIndex];
				a[i] = a[i] - a[minIndex];
				
				//sort from i-1 to 0
				Arrays.sort(a,0,i,Collections.reverseOrder());
				System.out.println(Arrays.toString(a));
				break;
			}
		}
		List<Integer> rev = Arrays.asList(a);
		Collections.reverse(rev);
		return (Integer[]) rev.toArray();
	}
	
	
	public static void main(String s[]) {
		Scanner sc = new Scanner(System.in);
		int t =sc.nextInt();
		while(t>0) {
			int n = sc.nextInt();
			Integer a[] = new Integer[n];
			for(int i=n-1;i>=0;i--) {
				a[i] = sc.nextInt();
			}
			Integer nextGreater[] = nextGreaterNumber(a, n);
			for(int i=0;i<n;i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println("");
			t--;
		}
	}
}
