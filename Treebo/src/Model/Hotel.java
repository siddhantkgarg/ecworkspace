package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import Comparators.AllocationFactory;
import Util.Utils;

public class Hotel {
	private int id;
	private int freeRoomCount;
	private AllocationType allocType;
	private Map<Integer, Room> occupiedRooms;
	private PriorityQueue<Room> emptyRooms;

	public Hotel(int id, AllocationType allocType, int roomCount) throws Exception {
		this.id = id;
		this.allocType = allocType;
		this.freeRoomCount = roomCount;
		occupiedRooms = new HashMap<Integer, Room>();
		Comparator<Room> comparator = AllocationFactory.getAllocationComparator(allocType);
		emptyRooms = new PriorityQueue<Room>(comparator);
		for(int i=0;i< freeRoomCount;i++) {
			emptyRooms.add(new Room(this,i,1));
		}
	}

	public synchronized List<Reservation> occupyRooms(int count) throws Exception {
		if (count > freeRoomCount)
			throw new Exception("Sorry! checkin request for too many rooms");
		List<Reservation> reservations = new ArrayList<Reservation>();
		for (int i = 0; i < count; i++) {
			Reservation reservation = new Reservation();
			Room room = emptyRooms.poll();
			if (room!=null && !room.isAllocated()) {
				occupiedRooms.put(room.getRoomId(), room);
				reservation.checkedInOn = new Date();
				reservation.checkedOutOn = Utils.getNextDate(1);
				reservation.hotel = this;
				reservation.room = room;
				reservations.add(reservation);
			}
		}
		
		//Free the allocated rooms
		if (reservations.size() != count) {
			try {
				for (Reservation reservation : reservations) {
					 occupiedRooms.remove(reservation.room.getRoomId());
					 emptyRooms.add(reservation.room);
				}
			}catch(Exception ex) {
				
			}
			throw new Exception("Rooms already full. Cannot checkin with the count requested");
		}
		System.out.println(count+" rooms occupied");
		return reservations;
	}
	
	
	public synchronized void freeRooms(List<Integer> roomIds) {
		for (Integer roomId : roomIds) {
			Room room = occupiedRooms.get(roomId);
			occupiedRooms.remove(roomId);
			emptyRooms.add(room);
		}
	}
}
