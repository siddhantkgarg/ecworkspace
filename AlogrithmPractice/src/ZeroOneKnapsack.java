import java.util.Scanner;

//DP
public class ZeroOneKnapsack {

	public static int knapsack(int v[],int wt[],int n,int W) {
		int k[][] = new int[n+1][W+1];
		for(int i=0;i<=n;i++) {
			for(int w = 0;w <=W;w++) {
				if(i==0 || w==0) 
					k[i][w] = 0;
				else if(wt[i-1]<=w) {
					k[i][w] = Math.max(v[i-1] + k[i-1][w-wt[i-1]], k[i-1][w]);
				}else {
					k[i][w] = k[i-1][w];
				}
			}
		}
		return k[n][W];
	}
	
	public static void main(String s[]) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		while(t>0) {
			int n  = sc.nextInt();
			int W = sc.nextInt();
			int v[] = new int[n];
			int wt[] = new int[n];
			for(int i=0;i<n;i++) {
				v[i] = sc.nextInt();
			}
			for(int i=0;i<n;i++) {
				wt[i] = sc.nextInt();
			}		
			System.out.println(knapsack(v, wt, n, W));
			t--;
		}
	}
}
