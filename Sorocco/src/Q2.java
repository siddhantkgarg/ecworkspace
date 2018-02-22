public class Q2 {

	
	public static void getRange(int[] a) {
		
		int conseq = 0;
		int n = a.length;
		int start =-1,end = -1;
		String s = "";
		
		for(int i=1;i<n;i++) {
			if(a[i]==a[i-1]+1)
			{
 				if(start==-1 && end ==-1) {
					start = i-1;
					end = i;
				}
				conseq++;
				if(conseq>=2) {
					end = i;
				}
			}else {
				if(conseq == 1) {
					s+=","+a[start]+","+a[end];
				}
				else if(conseq >=2){
					s+=","+a[start]+"-"+a[end];
				}
				else {
					s+=","+a[i-1];
				}
				conseq =0;
				start = -1;
				end = -1;
			}
			if(i==n-1) {
				if(conseq == 1) {
					s+=","+a[start]+","+a[end];
				}
				else if(conseq >=2){
					s+=","+a[start]+"-"+a[end];
				}
				else {
					s+=","+a[i-1]+","+a[i];
				}
			}
		}
		s=s.substring(1);
		System.out.println(s);
	}
	public static void main(String s[]) {
		int a [] = {-3,-2,-1,11,12,13,15,16,18,19,22,25,26};
		getRange(a);
	}
}
