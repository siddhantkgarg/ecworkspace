
class Tree {
	public int x;
	public Tree l;
	public Tree r;
}

public class MaxPerfectBinTree {
	
	
	public static int solution(int[] A, int X, int Y, int Z) {
		int n = A.length;
		int waitX = 0, waitY = 0, waitZ = 0;
		int wait[] = new int[n];
		for(int i=0;i<n;i++)
			wait[i] = -1;
		for (int i = 0; i < n; i++) {
			int minTime = Integer.MAX_VALUE;
			int timeIndex = 0;
			minTime = waitX;
			if (A[i] <= X) {
				if (waitX < minTime) {
					minTime = waitX;
					timeIndex = 0;
				}
			}
			if (A[i] <= Y) {
				if (waitY < minTime) {
					minTime = waitY;
					timeIndex = 1;
				}
			}
			if (A[i] <= Z) {
				if (waitZ < minTime) {
					minTime = waitZ;
					timeIndex = 2;
				}
			}
			if(timeIndex ==0) {
				waitX=A[i];
				X = X - A[i];
				if(i==0)
					wait[0]=0;
			}
			else if(timeIndex ==1) {
				waitY=A[i];
				Y = Y - A[i];
				if(i==0)
					wait[0]=0;
			}
			else if(timeIndex==2) {
				waitZ = A[i];
				Z = Z - A[i];
				if(i==0)
					wait[0]=0;
			}
			if(i>0)
				wait[i] = wait[i-1]+ minTime;
		}
		int W = 0;
		for(int i=0;i<n;i++)
			W+=wait[i];
		return W;
	}
	public static void main(String s[]) {
		int A[]= {2,8,4,3,2};
		System.out.println(solution(A,7,11,3));
	}
}
