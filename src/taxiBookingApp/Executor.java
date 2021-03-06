package taxiBookingApp;


public class Executor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BookTaxi bt=new BookTaxi();
		bt.initialTaxiStatus();
		bt.print();
		
		int stat=bt.bookTaxi(1, "Thiruporur", "Kelambakkam", 9);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		
		stat=bt.bookTaxi(2, "Kelambakkam","Tidal", 9);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}

		stat=bt.bookTaxi(3, "Sholinganallur","Tidal", 12);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		
		stat=bt.bookTaxi(4, "Thiruporur","Thoraipakkam", 12);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		
		stat=bt.bookTaxi(5, "Navalur","Tidal", 9);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		
		stat=bt.bookTaxi(5, "Thiruporur","Thoraipakkam", 13);
		if(stat>0){
			System.out.println("Taxi-"+stat+" allotted");
		}else{
			System.out.println("Booking Rejected! No taxi's available");
		}
		bt.printReport();
		
	}

}
