import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Q1 {
	public static Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
	public static int findFrequency( int l, int r, int x) {
		// Find the position of first occurrence of element
		int a = Arrays.binarySearch(map.get(x).toArray(),l);

		// Find the position of last occurrence of element
		int b = upper_bound(store[element].begin(), store[element].end(), right) - store[element].begin();

		return b - a;
	}
	
	public static Integer lowBound(final List<Integer> A,int B,boolean searchFirst){
	    int n = A.size();
	    int low = 0;
	    int high = n-1;
	    int res = -1;   //if element not found
	    int mid ;
	    while(low<=high){
	        mid = low+(high-low)/2;
	        if(A.get(mid)<=B){
	            res=mid;
	            if(searchFirst){high=mid-1;}    //to find first , go left
	            else{low=mid+1;}                // to find last, go right
	        }
	        else if(B>A.get(mid)){low=mid+1;}
	        else{high=mid-1;}
	    }
	    return res;
	}
	public static void main(String s[]) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a[] = new int[n];
		for(int i=0;i<n;i++) {
			a[i] = sc.nextInt();
			if(map.containsKey(a[i])) {
				List<Integer> mapList = map.get(a[i]);
				mapList.add(i);
				map.put(a[i], mapList);
			}else {
				List<Integer> mapList = new ArrayList<Integer>();
				mapList.add(i);
				map.put(a[i], mapList);
			}
		}
		
		
		
		int q = sc.nextInt();
		for(int i=0;i<q;i++) {
			int count = 0;
			int l = sc.nextInt();
			l--;
			int r = sc.nextInt();
			r--;
			int x = sc.nextInt();
			
			
			
			
			
			for(int j = l;j<=r;j++) {
				if(a[j]==x)
					count++;
			}
			System.out.println(count);
		}
	}
}
