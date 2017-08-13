package taxiBookingApp;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BookTaxi bt=new BookTaxi();
		bt.initialTaxiStatus();
		bt.print();
		
		Scanner input=new Scanner(System.in);
		
		System.out.println("Enter Number of Bookings : ");
		int noOfBookings = input.nextInt();
		int custId,pickupTime;
		char from,to;
		System.out.println(" Enter Values one by one 'Customer Id' 'From' 'To' 'Pickup Time'");
		for(int i=0;i<noOfBookings ; i++){
			custId = input.nextInt();
			from = input.next().charAt(0);
			to = input.next().charAt(0);
			pickupTime = input.nextInt();
			
			int taxi=bt.bookTaxi(custId, from, to, pickupTime);
			if(taxi > 0){
				System.out.println("Taxi-"+taxi+" allotted");
			}else{
				System.out.println("Booking Rejected! No taxi's available");
			}
			
		}
		
		bt.printReport();
		
	}

}
