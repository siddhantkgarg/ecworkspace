package Servants;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Interface.ICourseOfferCRUD;
import Model.CourseOffer;
import Model.CourseType;
import Util.Constants;
import Util.FileHandler;

public class CourseOfferServant extends AbstractCRUD<CourseOffer> implements ICourseOfferCRUD{

	public CourseOfferServant() throws RemoteException {
		List<CourseOffer> offerList = new ArrayList<CourseOffer>();
		List<List<String>> allOffers;
		try {
			allOffers = FileHandler.readLineFromCSV(Constants.OFFER_FILE);
			System.out.println(allOffers);
			for (List<String> list : allOffers) {
				CourseOffer offer = new CourseOffer();
				offer.buildFromCSV(list);
				offerList.add(offer);
			}
			System.out.println(offerList);
			this.t   = offerList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<CourseOffer> Upsert(CourseOffer cOffer){
		FileHandler.Log("Upsert", "recieved : "+cOffer.toString(), false);
		List<CourseOffer> coList = Retrieve(super.t, CourseOffer::getCourseId,cOffer.getCourseId());
		//check if already exists
		List<CourseOffer> exists = Retrieve(coList,CourseOffer::getStudent_id,cOffer.getStudent_id());
		if(exists == null || exists.size()==0) {
			if(coList.size()<5) {
				if(t.size()==4) {
					cOffer.setStatus(0);
				}
				super.t.add(cOffer);
			}else {
				System.out.println("Course if full. Not able to assign");
				return Collections.EMPTY_LIST;
			}
		}
		FileHandler.writeToFile("courseId,TimingId,section,faculty,room_no,type,student_id,status",Constants.OFFER_FILE, false);
		FileHandler.writeToFile(super.t,Constants.OFFER_FILE, true);
		return super.t;
		
	}
	

	@Override
	public String getCourse(int id) {
		FileHandler.Log("getCourse", "Request recieved "+ id, false);
		return Retrieve(t, CourseOffer::getCourseId, id).get(0).toString();
	}

	@Override
	public Boolean offerCourse(CourseOffer offer) {
		Boolean updated = false;
		FileHandler.Log("offerCourse", "Request recieved "+offer.toString(), false);
		List<CourseOffer> coList = Retrieve(super.t, CourseOffer::getCourseId,offer.getCourseId());
		//check if already exists
		List<CourseOffer> exists = Retrieve(coList,CourseOffer::getStudent_id,offer.getStudent_id());
		List<CourseOffer> alreadyOffered = Retrieve(coList,CourseOffer::getStatus,1);
		System.out.println("offeredSize:"+alreadyOffered.size());
		if(exists == null || exists.size()==0) {
			if(alreadyOffered.size()<=5) {
				updated = true;
				super.t.add(offer);
			}else {
				System.out.println("Course if full. Not able to assign");
			}
		}
		FileHandler.writeToFile("courseId,TimingId,section,faculty,room_no,type,student_id,status",Constants.OFFER_FILE, false);
		FileHandler.writeToFile(super.t,Constants.OFFER_FILE, true);
		return updated;
	}
	
	@Override
	public Boolean offerCourse() throws RemoteException {
		FileHandler.Log("offerCourse", "Request recieved ", false);
		
		CourseOffer offer = new CourseOffer();
		offer.setCourseId(1);
		offer.setFaculty(1);
		offer.setRoom_no(1);
		offer.setSection(1);
		offer.setStatus(1);
		offer.setStudent_id(3);
		offer.setTimingId(1);
		offer.setType(CourseType.ONLINE);
		
		return Upsert(offer).size() >0;
	}
	
	@Override
	public Boolean offerCourse(String paramString) throws RemoteException {
		FileHandler.Log("offerCourse", "Request recieved ", false);
		List<String> params = Arrays.asList(paramString.split(","));
		CourseOffer offer = new CourseOffer();
		if(params.size()!=8) {
			FileHandler.Log("offerCourse", "Request parameters does not match the given pattern", true);
			return false;
		}
		offer.setCourseId(Integer.parseInt(params.get(0)));
		offer.setTimingId(Integer.parseInt(params.get(1)));
		offer.setSection(Integer.parseInt(params.get(2)));
		offer.setFaculty(Integer.parseInt(params.get(3)));
		offer.setRoom_no(Integer.parseInt(params.get(4)));
		offer.setType(CourseType.valueOf(params.get(5)));
		offer.setStudent_id(Integer.parseInt(params.get(6)));
		offer.setStatus(Integer.parseInt(params.get(7)));
		
	
		
		return offerCourse(offer);
	}

	@Override
	public int getOfferedCount(int courseId) {
		return Retrieve(t, CourseOffer::getCourseId,courseId).size();
	}
	
	
	

}
