import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Q2 {

	public static int find(int node,List<Integer> list) {
		
	}
	public static void main(String s[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int a[] = new int[n];
		
		for(int i=0;i<n;i++) {
			a[i] = sc.nextInt();
		}
		
		Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
		for(int i=0;i<m;i++) {
			int test = sc.nextInt();
			int dependson = sc.nextInt();
			if(map.containsKey(test)) {
				List<Integer> mapList = map.get(test);
				mapList.add(dependson);
				map.put(test, mapList);
			}else {
				List<Integer> mapList = new ArrayList<Integer>();
				mapList.add(dependson);
				map.put(test, mapList);
			}
		}
		boolean processed[] = new boolean[n];
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
		   
		    if(!processed[entry.getKey()]) {
		    	 List<Integer> list = entry.getValue();
		    	 for (Integer integer : list) {
					
				}
		    }
		}
		
		for(int i=0;i<n;i++) {
			if(a[i]==0)
				System.out.println("NO");
			else
				System.out.println("YES");
		}
	}
}
