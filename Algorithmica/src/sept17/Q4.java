package sept17;

import java.util.Arrays;

public class Q4 {

	public static void merge(int a1[],int a2[],int m,int n) {
		int lastIndex = m+n-1;
		int p1 = m-1;
		int p2 = n-1;
		while(lastIndex>=0) {
			if(a1[p1]>a2[p2])
			{
				a1[lastIndex] = a1[p1];
				p1--;
			}else if(a1[p1]<a2[p2]) {
				a1[lastIndex] = a2[p2];
				p2--;
			}
			else {
				a1[lastIndex] = a2[p2];
				a1[lastIndex-1]=a1[p1];
				lastIndex--;
				p1--;
				p2--;
			}
			lastIndex--;
			if(p1<0) {
				//merge
				for(int i=0;i<=lastIndex;i++) {
					a1[i] = a2[i];
				}
				break;
			}
			if(p2<0) break;
		}
	}
	public static void main(String s[]) {
		int a1[] = {0,1,4,6,0,0,0,0,0} ;
		int a2[] = {10,11,14,16,18};
		merge(a1,a2,4,5);
		System.out.println(Arrays.toString(a1));
	}
}
