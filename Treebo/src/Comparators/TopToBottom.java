package Comparators;

import java.util.Comparator;

import Model.Room;

public class TopToBottom implements Comparator<Room>{

	@Override
	public int compare(Room room1, Room room2) {
		if(room1.getFloor()==room2.getFloor())
			return 0;
		return (room1.getFloor()>room2.getFloor())?-1:1;
	}
}
