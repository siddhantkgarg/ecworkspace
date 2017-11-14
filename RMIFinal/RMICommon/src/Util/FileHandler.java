package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.CourseDetails;

public class FileHandler {
	
	private static String CSVSEPERATOR = ",";
	public static String APPEND_MODE = "appendmode";
	public static String WRITE_MODE = "writemode";
	
	
	
	
	public static List<List<String>> readLineFromCSV(String filename){
		System.out.println(filename);
		String csvFile = filename;
        BufferedReader br = null;
        String line = "";
        List<List<String>> csvList = new ArrayList<List<String>>();
        List<String> props = new ArrayList<String>();
       
        try {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            
            if(line !=null) {
            	props = Arrays.asList(line.split(CSVSEPERATOR));	
            }
            
            while ((line = br.readLine()) != null) {
            	System.out.println(line);
                // use comma as separator
            	List<String> fieldValues = Arrays.asList(line.split(CSVSEPERATOR));
            	csvList.add(fieldValues);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
            	try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return csvList;
	}
//	public static<T> List<T> readModel(String filename,Class clazz) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
//		String csvFile = filename;
//        BufferedReader br = null;
//        String line = "";
//        
//        List<String> props = new ArrayList<String>();
//       
//        List<T> tList = new ArrayList<T>();
//        try {
//            br = new BufferedReader(new FileReader(csvFile));
//            line = br.readLine();
//            
//            if(line !=null) {
//            	props = Arrays.asList(line.split(CSVSEPERATOR));	
//            }
//            
//            while ((line = br.readLine()) != null) {
//            	 T t = (T)clazz.newInstance();
//                // use comma as separator
//            	int i=0;
//            	String[] fields = line.split(CSVSEPERATOR);
//            	if(clazz.getName().equalsIgnoreCase(CourseDetails.class.getName()))){
//            		CourseDetails cd = new CourseDetails(Integer.parseInt(fields[0]),fields[1],fields[2],Integer.parseInt(fields[3]));
//            		tList.add(cd);
//            	}
//            		
//            	tList.add(t);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//            	try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return tList;
//	}
	public static<T> void writeToFile(List<T> tList,String fileName,Boolean append) {
		String props = null;
		
		try(FileWriter fw = new FileWriter(fileName, append);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			for (T t : tList) {
					out.println(t.toString());
			}
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		}
	public static<T> void writeToFile(String text,String fileName,Boolean append) {
		String props = null;
		
		try(FileWriter fw = new FileWriter(fileName, append);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			StringBuffer line = new StringBuffer(text);
			
			out.println(line.toString());
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		}
  private static String capitalize(String word) {
	  if(word!=null && word.length()>0) {
		  char[] array = word.toCharArray();
		  array[0] = Character.toUpperCase(array[0]);
		  return new String(array);
	  }
	  return word;
  }
  
  public static void Log(String function,String text,boolean error) {
	  if(!error)
		  System.out.println("["+function+" : ]"+ text);
	  else{
		  	System.out.println("Error occured at :");
		  	System.out.println("["+function+" : ]"+ text);
	  }
  
  }
}
