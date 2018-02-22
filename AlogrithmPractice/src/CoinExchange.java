

//Having infinite supply

import java.util.Scanner;

public class CoinExchange {
	
	public static int coinExchange(int a[],int n,int sum) {
		int k[][] = new int[n+1][sum+1];
		
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=sum;j++) {
				
				if(i==0 && j!=0) {
					k[i][j]=0;
				}
				else if(j==0 ) {
					k[i][j]=1;
				}
				else if(a[i-1] <= j) {
					k[i][j] = k[i][j-a[i-1]] + k[i-1][j]; 
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
			for(int i=0;i<n;i++) {
				a[i] = sc.nextInt();
			}
			int sum = sc.nextInt();
			System.out.println(coinExchange(a,n,sum));	
			t--;
		}
	}
}


