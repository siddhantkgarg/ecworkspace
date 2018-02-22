import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//Job scheduling with deadline and profit

class Interval {
	int start;
	int end;
	
	public Interval(int s,int e) {
		start = s;
		end = e;
	}
	public Interval next() {
		return new Interval(end,end+1);
	}
}
class Job implements Comparable<Job>{
	int title;
	int deadline;
	int profit;
	
	public Job(int title,int deadline,int profit) {
		this.title = title;
		this.deadline = deadline;
		this.profit = profit;
	}

	@Override
	public int compareTo(Job o) {
		
		if(this.profit > o.profit) 
			return 1;
		else if(this.profit < o.profit)
			return -1;
		return 0;
	}
}
public class JobScheduling {
	
	public static int getMaxInterval(Job[] jobs) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < jobs.length; i++) {
			max = jobs[i].deadline > max ? jobs[i].deadline : max; 
		}
		return max;
	}
	public static void scheduleJobForMaxProfit(Job[] jobs) {
		Arrays.sort(jobs,Collections.reverseOrder());
		int n = jobs.length;
		int maxDeadline = getMaxInterval(jobs);
		int maxProfit = 0;
		int scheduledJobs [] = new int[maxDeadline];
		for(int i=0;i<maxDeadline;i++) {
			scheduledJobs[i]= '-';
		}
		scheduledJobs[0]=jobs[0].title;
		maxProfit = jobs[0].profit;
		int k = 1;
		for(int i=1;i<n && k<maxDeadline;i++) {
			if(jobs[i].deadline > k) {
				scheduledJobs[k++] = jobs[i].title;
				maxProfit+=jobs[i].profit;
			}
		}
//		for (char c : scheduledJobs) {
//			System.out.print(c + " ");
//		}
		System.out.println(k + " " + maxProfit);
	}
	
	public static void main(String s[]) {
//		System.out.println("Hello");
//		ArrayList<Job> arr=new ArrayList<Job>();
//        arr.add(new Job('a',2,100));
//        arr.add(new Job('b',1,19));
//        arr.add(new Job('c',2,27));
//        arr.add(new Job('d',1,25));
//        arr.add(new Job('e',3,15));
//        Job jobs [] = new Job[arr.size()];
        //JobScheduling.scheduleJobForMaxProfit(arr.toArray(jobs));
		int t;
		Scanner sc = new Scanner(System.in); 
		t = sc.nextInt();
		while(t>0) {
			int n = sc.nextInt();
			Job jobs[] = new Job[n];
			for(int i=0;i<n;i++) {
				
				int title = sc.nextInt();
				int deadline = sc.nextInt();
				int profit = sc.nextInt();
				jobs[i] = new Job(title,deadline,profit);
			}
//			//System.out.println(JobScheduling.scheduleJobForMaxProfit(jobs));
			JobScheduling.scheduleJobForMaxProfit(jobs);
			t--;
		}
	}
}
