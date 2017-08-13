package taxiBookingApp;

public class TaxiAppDAO {

	private int taxi;
	private char currentStop;
	private boolean isBooked;
	private int pickupTime;
	private int freeTime;
	private boolean isFirstTrip=true;
	private int totalEarnings;
	
	public int getTotalEarnings() {
		return totalEarnings;
	}
	public void setTotalEarnings(int totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	public boolean isFirstTrip() {
		return isFirstTrip;
	}
	public void setFirstTrip(boolean isFirstTrip) {
		this.isFirstTrip = isFirstTrip;
	}
	public int getTaxi() {
		return taxi;
	}
	public void setTaxi(int taxi) {
		this.taxi = taxi;
	}
	public char getCurrentStop() {
		return currentStop;
	}
	public void setCurrentStop(char currentStop) {
		this.currentStop = currentStop;
	}
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	public int getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(int pickupTime) {
		this.pickupTime = pickupTime;
	}
	public int getFreeTime() {
		return freeTime;
	}
	public void setFreeTime(int freeTime) {
		this.freeTime = freeTime;
	}
	
	
}
