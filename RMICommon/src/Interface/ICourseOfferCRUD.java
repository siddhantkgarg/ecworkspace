package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Model.CourseOffer;

public interface ICourseOfferCRUD extends Remote{
	List<CourseOffer> getCourse(int id) throws RemoteException;
	Boolean offerCourse(CourseOffer offer) throws RemoteException;
	int getOfferedCount(int courseId) throws RemoteException;
	Boolean offerCourse() throws RemoteException;
	
}
