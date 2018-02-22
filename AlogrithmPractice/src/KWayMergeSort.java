import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class HeapNode implements Comparable<HeapNode>{
	int element;
	int arrayIndex;
	
	@Override
	public int compareTo(HeapNode node) {
		return Integer.compare(element, node.element);
	}
}

//Given K sorted arrays, merge them
public class KWayMergeSort {
	
	//Assuming size of each array is same and n;
	//
	public static Integer[] merge(List<Integer[]> arrays) {
		int k = arrays.size();
		int n  = arrays.get(0).length;
		int iterators[] = new int[k];
		PriorityQueue<HeapNode> minHeap = new PriorityQueue<HeapNode>();
		
		Integer[] output = new Integer[n*k];
		for(int i=0;i<k;i++) {
			HeapNode node = new HeapNode();
			node.element = arrays.get(i)[iterators[i]];
			node.arrayIndex = i;
			minHeap.add(node);
		}
		for(int i=0;i<n*k;i++) {
			HeapNode node = minHeap.poll();
			output[i] = node.element;
			if(iterators[node.arrayIndex] < n-1) {
				int nextIndex = iterators[node.arrayIndex]+1;
				HeapNode nextNode = new HeapNode();
				nextNode.arrayIndex = node.arrayIndex;
				nextNode.element = arrays.get(node.arrayIndex)[nextIndex];
				minHeap.add(nextNode);
				iterators[node.arrayIndex]++;
			}
			else {
				HeapNode largeNode = new HeapNode();
				largeNode.element = Integer.MAX_VALUE;
				largeNode.arrayIndex = -1;
				minHeap.add(largeNode);
			}
		}
		return output;
	}
	public static void main(String s[]) {
		Scanner sc = new Scanner(System.in);	
		int t = sc.nextInt();
		while(t>0) {
			int n = sc.nextInt();
			List<Integer[]> list = new ArrayList<Integer[]>(n);
			for(int i=0;i<n;i++) {
				Integer a[] = new Integer[n];
				for(int j=0;j<n;j++) {
					a[j] = sc.nextInt();
				}
				list.add(a);
			}
			Integer output[]  = merge(list);
			System.out.println(Arrays.toString(output));
			t--;
		}
	}
}
