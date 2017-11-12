package Model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;

public class Timings extends Model implements Serializable{
	public Timings() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	int id;
	Date startTime;
	Date endTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return id + ", " + startTime + ", " + endTime;
	}
	
	
}
