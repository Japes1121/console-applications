package taxi_booking_app;


public class Executor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BookTaxi bt=new BookTaxi();
		bt.initialTaxiStatus();
		bt.print();
		
		int stat=bt.bookTaxi(1, 'A', 'B', 9);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		
		stat=bt.bookTaxi(2, 'B', 'D', 9);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}

		stat=bt.bookTaxi(3, 'B', 'C', 12);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		
		stat=bt.bookTaxi(4, 'A', 'C', 12);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		
		stat=bt.bookTaxi(5, 'B', 'C', 9);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		
		stat=bt.bookTaxi(5, 'B', 'C', 13);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		bt.printReport();
		
	}

}
