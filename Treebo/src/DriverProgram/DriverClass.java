package DriverProgram;

import java.util.ArrayList;
import java.util.Arrays;

import Model.AllocationType;
import Model.Hotel;

public class DriverClass {
	public static void main(String s[]) throws Exception {
		Hotel hotel = new Hotel(1, AllocationType.TOP_TO_BOTTOM, 5);
		hotel.occupyRooms(5);
		
		hotel.freeRooms(new ArrayList<Integer>(Arrays.asList(1,2,3)));
		hotel.occupyRooms(3);
	}
}
