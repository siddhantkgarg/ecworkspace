package Model;

public class Room {
	private Hotel hotel;
	private int roomId;
	private boolean isAllocated;
	private int floor;
	private ViewType viewType;
	
	public Room(Hotel hotel,int id,int floor) {
		this.roomId = id;
		this.hotel = hotel;
		this.floor = floor;
	}
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public boolean isAllocated() {
		return isAllocated;
	}

	public void setAllocated(boolean isAllocated) {
		this.isAllocated = isAllocated;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

}
