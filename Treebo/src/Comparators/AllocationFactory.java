package Comparators;
import java.util.Comparator;
import Comparators.TopToBottom;
import Model.AllocationType;
import Model.Room;

public class AllocationFactory {
	
	public static Comparator<Room> getAllocationComparator( AllocationType type) throws Exception{
		switch(type) {
			case TOP_TO_BOTTOM : 
				return new TopToBottom();
			default:
				throw new Exception("AllocationType not found");
		}
	}
}
