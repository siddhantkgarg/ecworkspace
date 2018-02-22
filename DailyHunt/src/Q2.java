/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class Q2 {
    
    public static void mergeArrays(int[] arr1, int[] arr2, int n1,
                                int n2, int[] arr3)
    {
        int i = 0, j = 0, k = 0;
        while (i<n1 && j <n2)
        {
           if (arr1[i] < arr2[j])
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }
        
        System.arraycopy(arr1,i,arr3,k,arr1.length-i);
        System.arraycopy(arr2,j,arr3,k,arr2.length-j);
        //while (i < n1)
          //  arr3[k++] = arr1[i++];
        //while (j < n2)
          //  arr3[k++] = arr2[j++];
    }
     
    public static void main (String[] args) 
    {
        //Scanner sc = new Scanner(System.in);
        //int n1 = sc.nextInt();
        
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		int n1 = Integer.parseInt(br.readLine());
        	int[] arr1  = new int[n1];
            for(int i=0;i<n1;i++){
                arr1[i]=Integer.parseInt(br.readLine());
            }
            int n2 = Integer.parseInt(br.readLine());
        	int[] arr2  = new int[n2];
            for(int i=0;i<n2;i++){
                arr2[i]=Integer.parseInt(br.readLine());
            }	
            int[] arr3 = new int[n1+n2];
            
            mergeArrays(arr1, arr2, n1, n2, arr3);
         
            //System.out.println("Array after merging");
            //System.out.println(Arrays.asList(arr3));
            for (int i=0; i < n1+n2; i++)
                System.out.print(arr3[i] + " ");

    	}catch(Exception ex) {
    		
    	}
    	
        
     
     
            }
    
}
