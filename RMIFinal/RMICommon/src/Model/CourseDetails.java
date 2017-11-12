package Model;

import java.io.Serializable;
import java.rmi.RemoteException;

public class CourseDetails extends Model implements Serializable{
	int id;
	String title;
	String description;
	int credit;

	
	public CourseDetails() throws RemoteException{
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseDetails(int id, String title, String description, int credit) throws RemoteException{
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.credit = credit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "" + id + "," + title + "," + description + "," + credit;
	}
	
	
}
