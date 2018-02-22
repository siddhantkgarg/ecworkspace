package sept17;

public class Q3 {
	//with destroying array
	public static int FindDub(int[] a) {
		int n = a.length;
		for(int i=0;i<n;i++) {
			if(a[a[i]-1]==0)return a[i];
			a[a[i]-1]=0;
		}
		return -1;
	}
	//sum method
	public static int FindDub2(int[] a) {
		int n = a.length;
		long sum=0;
		for (int i : a) {
			sum+=i;
		}
		long perfectSum = ((n-1)*(n))/2;
		return (int)(sum - perfectSum);
	}
	public static void main(String s[]) {
		int a[] = {1,2,3,4,1,5};
		System.out.println(FindDub2(a));
		System.out.println(FindDub(a));
		
	}
}
