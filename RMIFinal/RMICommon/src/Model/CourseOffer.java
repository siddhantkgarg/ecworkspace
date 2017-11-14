package Model;

import java.io.Serializable;
import java.rmi.RemoteException;

public class CourseOffer extends Model implements Serializable{
	public CourseOffer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	int courseId;
	int TimingId;
	int section;
	int faculty;
	int room_no;
	CourseType type;
	int student_id;
	int status;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getTimingId() {
		return TimingId;
	}
	public void setTimingId(int timingId) {
		TimingId = timingId;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public int getFaculty() {
		return faculty;
	}
	public void setFaculty(int faculty) {
		this.faculty = faculty;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public CourseType getType() {
		return type;
	}
	public void setType(CourseType type) {
		this.type = type;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return courseId + "," + TimingId + "," + section + "," + faculty + "," + room_no + "," + type + ","
				+ student_id + "," + status;
	}
	
	
}
