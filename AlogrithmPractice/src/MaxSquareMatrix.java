import java.util.Stack;

//1Square Matrix
//Path finding



public class MaxSquareMatrix {
	public static int maxAreaUnderHistogram(int hist[]) {
		Stack<Integer> stack = new Stack<Integer>();
		int n = hist.length;
		int maxArea = 0;
		int i=0;
		while(i<n) {
			if(stack.isEmpty() || hist[stack.peek()] <= hist[i])
				stack.push(i++);
			else {
				
					//right is max in stack
					int rightIndex = stack.pop();
					int leftIndex = rightIndex;
					if(!stack.isEmpty())
						leftIndex = stack.peek();
					int area = (hist[leftIndex]) * (rightIndex - (leftIndex - 1));
					if(area > maxArea)
						maxArea = area;
				
			}
		}
		while(!stack.isEmpty()) {
			        int rightIndex = stack.pop();
					int leftIndex = rightIndex;
					if(!stack.isEmpty())
						leftIndex = stack.peek();
					int area = (hist[leftIndex]) * (rightIndex - (leftIndex - 1));
					if(area > maxArea)
						maxArea = area;
		}
		return maxArea;
	}
	public static void maxSquareMatrix() {
		
	}
	public static void main(String s[]) {
		int a[] = {4,2};
		System.out.println(maxAreaUnderHistogram(a));
	}
}
