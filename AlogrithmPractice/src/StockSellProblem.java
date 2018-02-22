import java.util.Scanner;

public class StockSellProblem {

	public static int maxProfit(int price[]) {
		int profit=0;
		int buyAt = 0;
		int sellAt = 0;
		int n = price.length;
		if(price[0]<price[1]) {
			buyAt=0;
		}else {
			buyAt=1;
		}
		for(int i=1;i<n-1;i++) {
			if(price[i] < price[i-1]  && price[i] < price[i+1]) {
				buyAt = i;
			}
			else if(price[i] > price[i-1]  && price[i] > price[i+1]) {
				sellAt = i;
				System.out.print("("+buyAt+" "+sellAt+") ");
				profit+=(price[sellAt] - price[buyAt]);
			}
		}
		if(price[n-1]>price[n-2]) {
			sellAt = n-1;
			System.out.print("("+buyAt+" "+sellAt+") ");
			profit+=(price[sellAt] - price[buyAt]);
		}
		if(sellAt==0) {
			System.out.print("No Profit");
		}
		System.out.println("");
		return profit;
	}
	public static void main(String s[]) {
		  int t;
		  Scanner sc = new Scanner(System.in);
		  t = sc.nextInt();
		  while(t>0) {
			  int n = sc.nextInt();
			  int price[] = new int[n];
			  for(int i=0;i<n;i++) {
				  price[i] = sc.nextInt();
			  }
			  //System.out.println(maxProfit(price));
			  maxProfit(price);
			  t--;
		  }
	}
}
