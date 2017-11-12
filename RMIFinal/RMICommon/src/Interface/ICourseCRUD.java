package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Model.CourseDetails;

public interface ICourseCRUD extends Remote{
	String getTitle(int courseId) throws RemoteException;
	void setTitle(String title,int courseId) throws RemoteException;
	
	String getDescription(int courseId) throws RemoteException;
	void setDescription(String description,int courseId) throws RemoteException;
	
	String getCredit(int courseId) throws RemoteException;
	void setCredit(int credit,int courseId) throws RemoteException;
	
	CourseDetails getCourse(int id) throws RemoteException;
	void setCourse(CourseDetails course) throws RemoteException;
}
