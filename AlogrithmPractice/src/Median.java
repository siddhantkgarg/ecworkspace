import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

//Median of running stream of numbers

class QueueADT{
	PriorityQueue<Integer> minQueue;
	PriorityQueue<Integer> maxQueue;
	int median;
	
	public QueueADT(){
		minQueue = new PriorityQueue<Integer>();
		maxQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
		median = 0;
	}
	public int heightDiff() {
		int left = maxQueue.size();
		int right = minQueue.size();
		if(left == right) return 0;
		return (left>right)?1:-1;
	}
	public void insert(int element) {
		int diff = heightDiff();
		switch(diff) {
		case 0:
				if(element < median)
				{
					maxQueue.add(element);
					if(!maxQueue.isEmpty())
						median = maxQueue.peek();
				}
				else 
				{
					minQueue.add(element);
					if(!minQueue.isEmpty())
						median = minQueue.peek();
				}
					
			break;	
		case 1: 
				if(element < median) {
					int leftTop = maxQueue.poll();
					maxQueue.add(element);
					minQueue.add(leftTop);
					
				}else {
					minQueue.add(element);
				}
				if(!minQueue.isEmpty()) {
					median = (maxQueue.peek() + minQueue.peek() )/2;
				}
				else {
					median = maxQueue.peek();
				}
			break;	
				
		case -1:	
				if(element > median) {
					int rightTop = minQueue.poll();
					minQueue.add(element);
					maxQueue.add(rightTop);
					
				}else {
					maxQueue.add(element);
				}
				if(!maxQueue.isEmpty()) {
					median = (maxQueue.peek() + minQueue.peek() )/2;
				}
				else {
					median = minQueue.peek();
				}
				break;
		}
	}
	public int getMedian() {
		return median;
	}
}
public class Median {
	public static QueueADT qADT = new QueueADT();
	public static int insertAndGetMedian(int element) {
		qADT.insert(element);
		return qADT.getMedian();
	}
	public static void main(String s[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0;i<n;i++) {
			int element = sc.nextInt();
			System.out.println(insertAndGetMedian(element));
		}
		sc.close();
	}
}
