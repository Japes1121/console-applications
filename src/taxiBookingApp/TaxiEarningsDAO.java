package taxiBookingApp;

public class TaxiEarningsDAO {

	private int bookingId;
	private int customerId;
	private int taxi;
	private String startPoint;
	private String endPoint;
	private int pickupTime;
	private int dropTime;
	private int fareAmount;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
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
