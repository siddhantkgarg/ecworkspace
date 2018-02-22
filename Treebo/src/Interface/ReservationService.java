package Interface;

import Model.Hotel;
import Model.Reservation;

public interface ReservationService {
	Reservation checkIn(Hotel hotel,int roomCount);
	
	void checkOut(); 
}
