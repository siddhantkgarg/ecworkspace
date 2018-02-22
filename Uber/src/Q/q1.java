package Q;

import java.util.ArrayDeque;
import java.util.Deque;

public class q1 {

	class Interval
	{
	 int start, end;
	};
	Boolean compareInterval(Interval i1, Interval i2)
	{
	 return (i1.start < i2.start);
	}
	int mergeIntervals(Interval arr[], int n)
	{
	 // Test if the given set has at least one interval
	 if (n <= 0)
	     return 0;
	 
	 Deque<Integer> s = new ArrayDeque<Integer>();

	 // Create an empty stack of intervals


	 // sort the intervals in increasing order of start time
	 sort(arr, arr+n, compareInterval);

	 // push the first interval to stack
	 s.push(arr[0]);

	 // Start from the next interval and merge if necessary
	 for (int i = 1 ; i < n; i++)
	 {
	     // get interval from stack top
	     Interval top = s.top();

	     // if current interval is not overlapping with stack top,
	     // push it to the stack
	     if (top.end < arr[i].start)
	         s.push(arr[i]);

	     // Otherwise update the ending time of top if ending of current
	     // interval is more
	     else if (top.end < arr[i].end)
	     {
	         top.end = arr[i].end;
	         s.pop();
	         s.push(top);
	     }
	 }

	 // Print contents of stack
	 //cout << "\n The Merged Intervals are: ";
	 //while (!s.empty())
	 //{
	 //    Interval t = s.top();
	 //    cout << "[" << t.start << "," << t.end << "] ";
	 //    s.pop();
	// }	
	int count = 0;
	int done=0;
	while(!s.empty()){
		Interval t = s.top();
		int len = t.end - t.start; 
		if(t.start <0 && t.start>0 && !done){
			len+=1;
			done=1;
		}
		count+=len;
	}

	 return count;
	}


	
}



//Driver program
int main()
{
// Interval arr[] =  { {4,7}, {-1,5}, {3,6} };
int n = 0;
 cin>>n;
Interval * arr = new Interval[n];
 for(int i=0;i<n;i++)
 {
 	int start ; int end;
 	cin>>start;
 	cin>>end;
 	//Interval a = new Interval();
 	//a.start = start;
 	//a.end = end;
 	arr[i].start = start;
 	arr[i].end = end;
 	
 }
//   int n = sizeof(arr)/sizeof(arr[0]);
int count = mergeIntervals(arr, n);
 cout<<count;

 
 return 0;
}