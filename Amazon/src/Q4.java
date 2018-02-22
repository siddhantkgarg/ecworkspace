import java.util.Scanner;

public class Q4{

	public static int largestRegion(int[][] a) {
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                max = Math.max(max, dfs(a, i, j));
            }
        }
        return max;
    }
    
    private static int dfs(int[][] a, int i, int j) {
        if (i < 0 || j < 0 || i >= a.length || j >= a[0].length) return 0;
        if (a[i][j] == 0) return 0;
        int count = a[i][j]--;
        count += dfs(a, i + 1, j);
        count += dfs(a, i - 1, j);
        count += dfs(a, i, j + 1);
        count += dfs(a, i, j - 1);
        count += dfs(a, i + 1, j + 1);
        count += dfs(a, i - 1, j - 1);
        count += dfs(a, i - 1, j + 1);
        count += dfs(a, i + 1, j - 1);
        return count;
    }
    
     public static void main(String s[]) {
    	 Scanner sc = new Scanner(System.in);
    	 int row = sc.nextInt();
    	 
    	 int col = sc.nextInt();
    	 int a[][] = new int[row][col];
    	 for(int i=0;i<row;i++) {
    		 for(int j=0;j<col;j++) {
    			 a[i][j]= sc.nextInt();
    		 }
    	 }
    	 System.out.println(largestRegion(a));
     }
}


