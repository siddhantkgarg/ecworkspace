/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/
import java.util.*;
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class Q {
	public static int maxSumSubArray(int a[]) {
		int n = a.length;
		int start = 0;
		int end = 0;
		int s = 0;
		int maxSoFar = 0;
		int maxEndingHere = 0;
		boolean allneg = true;
		int negMax = Integer.MIN_VALUE;
		int negIndex = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] > 0)
				allneg = false;
			maxEndingHere = maxEndingHere + a[i];
			if (maxEndingHere > maxSoFar) {
				maxSoFar = maxEndingHere;
				start = s;
				end = i;
			}
			if (maxEndingHere < 0) {
				maxEndingHere = 0;
				s = i + 1;
			}
			if (negMax < a[i]) {
				negMax = a[i];
			}
		}
		if (allneg) {
			start = negMax;
			end = negMax;
			return negMax;
		}
		return maxSoFar;
	}

	public static int maxNonContiguous(int a[]) {
		int n = a.length;
		int max = 0;
		int allNeg = 0;
		int negMax = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (a[i] > 0) {
				negMax = 0;
				max += a[i];
			} else {
				allNeg++;
				if (negMax < a[i])
					negMax = a[i];
			}

		}
		if (allNeg == n) {
			return negMax;
		}
		return max;
	}

	public static void main(String args[]) throws Exception {

		// Scanner
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int t = sc.nextInt();
			while (t > 0) {
				int n = sc.nextInt();
				int a[] = new int[n];
				for (int i = 0; i < n; i++) {
					a[i] = sc.nextInt();
				}
				System.out.print(maxSumSubArray(a) + " ");
				System.out.println(maxNonContiguous(a));
				t--;
			}
			// Write your code here
		}
	}
}
