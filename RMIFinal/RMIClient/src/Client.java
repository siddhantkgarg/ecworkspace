import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Interface.ICourseCRUD;
import Interface.ICourseOfferCRUD;
import Util.Constants;

public class Client {
	static ICourseCRUD courseServant;
	static ICourseOfferCRUD offerServant;

	public void makeInteractive() throws RemoteException {
		Scanner sc = new Scanner(System.in);
		System.out.println("This is an infinite loop of inputs. Press exit to stop the process.");
		System.out.println("Enter the index of function to be executed on remote");
		int cont = 1;
		while (cont != 0) {
			int len = Constants.functionList.length;
			for (int i = 0; i < len; i++) {
				System.out.println((i + 1) + "> " + Constants.functionList[i]);
			}
			int function = sc.nextInt();
			definedFunctions(function);
			System.out.println("Continue? [0/1]");
			cont = sc.nextInt();
			if (cont == 0) {
				System.exit(1);
			}
		}
		if (cont == 0) {
			System.exit(1);
		}
	}

	private void definedFunctions(int function) throws RemoteException {
		switch (function) {
		// getcourseoffered
		case 1:
			System.out.println("CourseId : ");
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			String offerList = offerServant.getCourse(id);
			if (offerList != null) {
				System.out.println(offerList);
			} else {
				System.out.println("No record present");
			}

			break;
		case 2:
			break;
		case 3:
			System.out.println(
					"Course offer entry (Comma seperated) [courseId,TimingId,section,faculty,room_no,type,student_id,status]: ");
			sc = new Scanner(System.in);
			String line = sc.nextLine();
			
			Boolean offered = offerServant.offerCourse(line);
			if (offered) {
				System.out.println("Course Offered Successfully");
			} else {
				System.out.println("Cannot offer this course");
			}

			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			break;
		}
	}

	private Client() {
	}

	public static void main(String[] args) {
		Client c = new Client();
		try {

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new java.rmi.RMISecurityManager());
			}

			// CRUD<CourseDetails> cOfferStub = (CRUD<CourseDetails>)
			// UnicastRemoteObject.exportObject(courseServant, 0);
			System.out.println("#########################Client Started############################################");

			// Bind the remote object's stub in the registry
			Registry registryCS = LocateRegistry.getRegistry(Constants.HOST);
			courseServant = (ICourseCRUD) Naming.lookup("myCourse");
			offerServant = (ICourseOfferCRUD) Naming.lookup("myOffer");

			// User input begins
			try {
				new Client().makeInteractive();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
