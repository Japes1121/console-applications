package taxi_booking_app;

public class TaxiEarningsDAO {

	private int bookingId;
	private int customerId;
	private int taxi;
	private char startPoint;
	private char endPoint;
	private int pickupTime;
	private int dropTime;
	private int fareAmount;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId() {
		this.bookingId = bookingId+1;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getTaxi() {
		return taxi;
	}
	public void setTaxi(int taxi) {
		this.taxi = taxi;
	}
	public char getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(char startPoint) {
		this.startPoint = startPoint;
	}
	public char getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(char endPoint) {
		this.endPoint = endPoint;
	}
	public int getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(int pickupTime) {
		this.pickupTime = pickupTime;
	}
	public int getDropTime() {
		return dropTime;
	}
	public void setDropTime(int dropTime) {
		this.dropTime = dropTime;
	}
	public int getFareAmount() {
		return fareAmount;
	}
	public void setFareAmount(int fareAmount) {
		this.fareAmount = fareAmount;
	}
	
}
