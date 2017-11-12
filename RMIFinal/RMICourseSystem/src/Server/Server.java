package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Interface.ICourseCRUD;
import Interface.ICourseOfferCRUD;
import Servants.CourseOfferServant;
import Servants.CourseServant;
import Util.Constants;
import Util.FileHandler;
/*
 * Server class; 
 * Takes input for course offered.
 * Students opt in for any offered course.
 */
public class Server {

		@SuppressWarnings("deprecation")
		public static void main(String s[]) {
			
 			 try {
		            CourseServant courseServant = new CourseServant();
		            ICourseCRUD courseStub = (ICourseCRUD) UnicastRemoteObject.exportObject(courseServant, 0);
		            
		            CourseOfferServant offerServant = new CourseOfferServant();
		            ICourseOfferCRUD offerStub = (ICourseOfferCRUD) UnicastRemoteObject.exportObject(offerServant, 0);
		            
		            // Bind the remote object's stub in the registry
		            Registry registryCS = LocateRegistry.createRegistry(Constants.HOST);
		            FileHandler.Log("main",registryCS.toString(),false);
		            registryCS.rebind("myCourse", courseStub);
		            registryCS.rebind("myOffer",offerStub);
		            System.out.println("Server ready");
		        } catch (Exception e) {
		            System.err.println("Server exception: " + e.toString());
		            e.printStackTrace();
		        }
			
		}
}
