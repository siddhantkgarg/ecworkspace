import java.util.Arrays;

public class KadanesAlgorithm {
	
	public static int[] maxSumSubArray(int a[]) {
		int n = a.length;
		int start = 0;
		int end = 0;
		int s=0;
		int maxSoFar = 0;
		int maxEndingHere = 0;
		boolean allneg = true;
		int negMax = Integer.MIN_VALUE;
		int negIndex = 0;
		for(int i=0;i<n;i++) {
			maxEndingHere = maxEndingHere + a[i];
			if(maxEndingHere > maxSoFar)
			{
				start = s;
				end = i;
			}
			if(maxEndingHere < 0) {
				maxEndingHere = 0;
				s = i+1;
			}
		}
		if(allneg) {
			start = negMax;
			end = negMax;
		}
		return Arrays.copyOfRange(a, start, end);
	}
	public static void main(String s[]) {
		int a[] = {-1,-2,-3,-4,-5,-6,-7,-8};
		//int a[] = {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println(Arrays.toString(maxSumSubArray(a)));
	}
}
