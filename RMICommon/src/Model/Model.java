package Model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Model extends UnicastRemoteObject implements Serializable{

	public Model() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void buildFromCSV(List<String> fields) throws NumberFormatException, IllegalArgumentException, IllegalAccessException,RemoteException {
		Field[] allFields = this.getClass().getDeclaredFields();
		int i=0;
		if(fields.size() == allFields.length) {
			System.out.println("reading from csv and parsing.");
			for (Field field : allFields) {
				if(field.getType().equals(Integer.class)) {
					field.set(this,Integer.parseInt(fields.get(i)));
				}else if(field.getType().equals(Integer.TYPE)) {
					field.set(this,Integer.parseInt(fields.get(i)));
				}else if(field.getType().equals(CourseType.class)){
					field.set(this,CourseType.valueOf(fields.get(i).toUpperCase()));
				}else {
					field.set(this, fields.get(i).toString());
				}
					
				i++;
			}
			System.out.println(this.toString());
		}
	}
	
}
