import java.util.Scanner;

public class SubsetSumProblem {
	
	public static boolean subsetSum(int a[],int n,int sum) {
		boolean k[][] = new boolean[n+1][sum+1];
		
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=sum;j++) {
				
				if(i==0 && j!=0) {
					k[i][j]=false;
				}
				else if(j==0 ) {
					k[i][j]=true;
				}
				else if(a[i-1] <= j) {
					k[i][j] = k[i-1][j-a[i-1]] || k[i-1][j]; 
				}
				else {
					k[i][j] = k[i-1][j];
				}
			}
		}
		return k[n][sum];
	}
	public static void main(String s[]) {
Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		while(t>0) {
			int n  = sc.nextInt();
			int a[] = new int[n];
			int sum = 0;
			for(int i=0;i<n;i++) {
				a[i] = sc.nextInt();
				sum +=a[i];
			}
			if(sum%2!=0) {
				System.out.println("false");
			}else {
				System.out.println(subsetSum(a,n,sum/2));	
			}
			
			t--;
		}
	}
}
