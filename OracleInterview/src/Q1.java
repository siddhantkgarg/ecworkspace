
class Node{
	Node next;
	int data;
	public Node(int data) {
		this.data = data;
		next = null;
	}
	public  void addNext(Node n) {
		this.next = n;
	}
}
public class Q1 {

	public static Node findInteractionPoint(Node node1,Node node2) {
		int len1=0,len2=0;
		Node p = node1;
		if(node1==null || node2==null) 
			return null;
		while(p!=null) {
			len1++;
			p=p.next;
		}
		p = node2;
		while(p!=null) {
			len2++;
			p=p.next;
		}
		Node it1 = node1;
		Node it2 = node2;
		int diff = Math.abs(len1-len2);
		if(len1<len2) {
			while(diff>0) {
				it2 = it2.next;
				diff--;
			}
		}
		else {
			while(diff>0) {
				it1 = it1.next;
				diff--;
			}
		}
		while(it1!=null || it2!=null) {
			if(it1==it2)
				return it1;
			it1 = it1.next;
			it2 = it2.next;
		}
		return null;
	}
	
	//
//	20 , 10 , 15 , 60 , 25 , 35 , 80
	
	public int getMaxProfit(int a[]) {
		int n= a.length;
		int profit = 0;
		for(int k=0;k<n;k++) {
			profit = Math.max(profit, maxPriceUtil(a,0,k) + maxPriceUtil(a,k+1,n));
		}
		return profit;
	}
	
	public int maxPriceUtil(int a[],int start,int end) {
		int profit = 0;
		if(start>=end && end < a.length)
			return 0;
		for(int i=start;i<=end;i++) {
			if((i==start && a[i] < a[i+1]) || (a[i] < a[i-1]  && a[i] <a[i+1])) {
				for(int j=start+1;j<=end;j++) {
					if(a[j]> a[j+1] && a[j]>a[j-1]) {
						profit = Math.max(profit, a[j]-a[i]);
					}
				}
			}
		}
		return profit;
	}
	
	
	
	
	
}
