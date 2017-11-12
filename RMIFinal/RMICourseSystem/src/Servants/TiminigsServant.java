package Servants;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import Model.CourseDetails;
import Model.Timings;
import Util.Constants;
import Util.FileHandler;

public class TiminigsServant extends AbstractCRUD<Timings> implements Serializable{

	public TiminigsServant() throws RemoteException {
		List<Timings> timeInit = new ArrayList<Timings>();
		List<List<String>> csvList;
		try {
			csvList = FileHandler.readLineFromCSV(Constants.COURSE_DETAILS_FILE);
			for (List<String> list : csvList) {
				Timings timing = new Timings();
				timing.buildFromCSV(list);
				timeInit.add(timing);
			}
			this.t = timeInit;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
