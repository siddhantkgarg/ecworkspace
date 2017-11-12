package Servants;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import Interface.ICourseCRUD;
import Model.CourseDetails;
import Model.CourseOffer;
import Util.Constants;
import Util.FileHandler;

public class CourseServant extends AbstractCRUD<CourseDetails> implements ICourseCRUD{

	public CourseServant() throws RemoteException{
		List<CourseDetails> cdListInit = new ArrayList<CourseDetails>();
		List<List<String>> csvList;
		try {
			csvList = FileHandler.readLineFromCSV(Constants.COURSE_DETAILS_FILE);
			for (List<String> list : csvList) {
				CourseDetails cdInit = new CourseDetails();
				cdInit.buildFromCSV(list);
				
				cdListInit.add(cdInit);
			}
			System.out.println(cdListInit);
			this.t = cdListInit;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	public List<CourseDetails> Upsert(CourseDetails course){
		List<CourseDetails> coList = Retrieve(super.t, CourseDetails::getId,course.getId());
		//check if already exists
		if(coList == null || coList.size()==0) {
				super.t.add(course);
		}else if(coList.size() ==1){
			CourseDetails cd = coList.get(0);
			t.remove(cd);
			t.add(course);
		}
		FileHandler.writeToFile(super.t,Constants.COURSE_DETAILS_FILE, false);
		return super.t;
	}

	@Override
	public String getTitle(int id) {
		CourseDetails cd = Retrieve(t, CourseDetails::getId, id).get(0);
		if(cd!=null ) {
			return cd.getTitle();
		}
		throw new NoSuchElementException();
	}

	@Override
	public void setTitle(String title, int courseId) {
		CourseDetails cd = Retrieve(t,CourseDetails::getId,courseId).get(0);
		if(cd != null) {
			cd.setTitle(title);
			Upsert(cd);
		}
	}

	@Override
	public String getDescription(int courseId) {
		
		return null;
	}

	@Override
	public void setDescription(String description, int courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCredit(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCredit(int credit, int courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CourseDetails getCourse(int id) {
		return Retrieve(t,CourseDetails::getId,id).get(0);
	}

	@Override
	public void setCourse(CourseDetails course) {
		// TODO Auto-generated method stub
		
	}
	

}
