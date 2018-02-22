import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Challenge {

	public static void dedup(String input_file_path, String output_file_path) {

		try {
			FileInputStream fin = new FileInputStream(input_file_path);
			BufferedInputStream bin = new BufferedInputStream(fin);
			int i;
			long max=Integer.MIN_VALUE;
			byte barray[] = new byte[1024];
			Map<byte[], List<Integer>> map = new HashMap<byte[], List<Integer>>();
			
			int count = 0;
			while ((i = bin.read(barray, 0, 1024)) != -1) {
				
				
				if (map.containsKey(barray)) {
					List<Integer> list = map.get(barray);
					
					list.add(count);
					map.put(barray, list);
				}else {
					map.put(barray, new ArrayList<Integer>(count));
				}
				count++;
			}
			bin.close();
			fin.close();
			
			int distinctCount = map.size();
			byte[] distinct = new byte[count];
			
			FileOutputStream fout = new FileOutputStream(output_file_path);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			for (Map.Entry<byte[], List<Integer>> arr :map.entrySet() ) {
				byte[] next = new byte[1025];
				List<Integer> list = arr.getValue();
				int arrsize = 1024;
				for (Integer integer : list) {
					if(integer < arrsize)
						next[integer] = 1;
					else {
						int intcount = 1;
						while(integer >=arrsize) {
							next[arrsize] = 1;
							arrsize = intcount*arrsize;
							next = Arrays.copyOf(next, arrsize);
							for(int j=arrsize/intcount+1;j<arrsize;j++) {
								next[j]=0;
							}
							intcount++;
						}
						next[integer] = 1;
						next[next.length-1]=0;
					}
				}
				bout.write(arr.getKey());
				bout.write(next);
			}
			bout.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean redup(String input_file_path, String output_file_path) {
		try {
			FileInputStream fin = new FileInputStream(input_file_path);
			//long finSize =  
			BufferedInputStream bin = new BufferedInputStream(fin);
			int i;
			long max=Integer.MIN_VALUE;
			byte barray[] = new byte[1024];
			Map<byte[], List<Integer>> map = new HashMap<byte[], List<Integer>>();
			
			int count = 0;
			while ((i = bin.read(barray, 0, 1024)) != -1) {

				if (map.containsKey(barray)) {
					List<Integer> list = map.get(barray);
					
					list.add(count);
					map.put(barray, list);
				}else {
					map.put(barray, new ArrayList<Integer>(count));
				}
				count++;
			}
			bin.close();
			fin.close();
			FileOutputStream fout = new FileOutputStream(output_file_path);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			int distinctCount = map.size();
			byte[] distinct = new byte[count];
			
			for (Map.Entry<byte[], List<Integer>> arr :map.entrySet() ) {
				List<Integer> list = arr.getValue();
				for (Integer integer : list) {
					distinct[integer] = 1;
				}
				bout.write(arr.getKey());
				bout.write(distinct);
			}
			bout.close();
			fout.close();
		} catch (Exception e) {
			System.out.println(e);
		} 
		return true;
	}

	public static void main(String s[]) {
		String filename = "C://Users//sigarg//byteinputfile";
		String ofilename = "C://Users//sigarg//binaryoutpufile";
		dedup(filename, ofilename) ;
	}
}
