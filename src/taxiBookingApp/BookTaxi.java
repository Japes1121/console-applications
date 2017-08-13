package taxiBookingApp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookTaxi implements Properties {

	private static HashMap<Integer,TaxiAppDAO> taxiStatus=new HashMap<>();
	private static HashMap<Integer,List<TaxiEarningsDAO>> taxiEarnings=new HashMap<>();

	
	public void initialTaxiStatus(){
		for(int i=0;i<Properties.taxi.length;i++){
			TaxiAppDAO taxi=new TaxiAppDAO();
			taxi.setBooked(false);
			taxi.setCurrentStop('A');
			taxi.setTaxi(Properties.taxi[i]);
			taxiStatus.put(taxi.getTaxi(),taxi);
		}
	}
	
	public void print(){
		TaxiAppDAO ta;
		TaxiEarningsDAO te1;
		TaxiEarningsDAO te=new TaxiEarningsDAO();	
		
		for(Map.Entry m:taxiStatus.entrySet()){
			ta=(TaxiAppDAO) m.getValue();
			  // System.out.println(m.getKey()+" "+ta.isBooked());  
			  }
		
		for(Map.Entry m:taxiEarnings.entrySet()){
			List<TaxiEarningsDAO> teDAO = taxiEarnings.get(m.getKey());
			for(TaxiEarningsDAO teObj:teDAO){
				//System.out.println("Taxi - "+teObj.getTaxi()+" Fare : "+teObj.getFareAmount());
			}
		}
			  
		
	}
	
	public void printReport(){
		System.out.println("Taxi No:    Total Earnings:");
		System.out.println("BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
		
		for(Map.Entry m:taxiEarnings.entrySet()){
			List<TaxiEarningsDAO> teDAO = taxiEarnings.get(m.getKey());
			if(null != teDAO){
				System.out.println("Taxi - "+teDAO.get(0).getTaxi() + " Total Earnings : Rs. "+taxiStatus.get(teDAO.get(0).getTaxi()).getTotalEarnings());
				for(TaxiEarningsDAO teObj:teDAO){
					System.out.println(teObj.getBookingId() + " " + teObj.getCustomerId() + " " + teObj.getStartPoint() + " "
							+teObj.getEndPoint() + " " + teObj.getPickupTime() + " " + teObj.getDropTime() + " " + teObj.getFareAmount());
				}
			}
		}
	}
	
	public int bookTaxi(int custId, char pickupPoint, char dropPoint, int pickupTime){
		List<TaxiEarningsDAO> teDAO;
		List<int[]> freeTaxiList=new ArrayList<>();
		int source,dest,earnings=0,distance;
		TaxiAppDAO ta;
		TaxiEarningsDAO te;
		int minDist=0;
		boolean minDistFlag=true;
		for(Map.Entry m:taxiStatus.entrySet()){ 
			earnings=0;
			ta=(TaxiAppDAO) m.getValue();
			distance=this.calculateFare(pickupPoint, ta.getCurrentStop())[1];
			if(ta.isBooked() == false && ta.isFirstTrip()==true && (minDistFlag==true || distance <= minDist)){
				int[] taxiStat={ta.getTaxi(),distance,0};
				minDistFlag = false;
				minDist = distance;
				freeTaxiList.add(taxiStat);
			}
			else if(ta.isBooked() == false && ( (ta.getFreeTime() > pickupTime && ta.getPickupTime() > pickupTime ) || (ta.getFreeTime() < pickupTime && ta.getPickupTime() < pickupTime ))
				&& (minDistFlag==true || distance <= minDist)){
				teDAO = taxiEarnings.get(ta.getTaxi());
				earnings = ta.getTotalEarnings();
				minDistFlag = false;
				minDist = distance;
				int[] taxiStat={ta.getTaxi(),distance,earnings};
				freeTaxiList.add(taxiStat);
			}
		}
		
		int minFare=0,taxi=0;
		boolean firstMinFareFlag=true;
		
		
		
		/*for(int[] tas:freeTaxiList){
			System.out.println("Taxi : "+tas[0]+" Dist : "+tas[1]+" Earnings : "+tas[2]);
			System.out.println("=======================================================");
			TaxiAppDAO tdd=taxiStatus.get(tas[0]);
			System.out.println("IsBooked : "+tdd.isBooked());
			System.out.println("First Trip : "+tdd.isFirstTrip());
			System.out.println("Current Stop : "+tdd.getCurrentStop());
			System.out.println("Pick Up Time: "+tdd.getPickupTime());
			System.out.println("Drop Time: "+tdd.getFreeTime());
			System.out.println("=======================================================");
		}*/
		
		
		minDist=0;
		minDistFlag=true;
		for(int[] tas:freeTaxiList){
			if(tas[1]==0 && tas[2]==0){
				if(tas[0] > 0){
					taxi=tas[0];
					TaxiAppDAO taDAO=taxiStatus.get(taxi);
					taDAO.setFirstTrip(false);
					taDAO.setCurrentStop(dropPoint);
					taDAO.setPickupTime(pickupTime);
					taDAO.setFreeTime((pickupTime+calculateFare(pickupPoint, dropPoint)[1]));
					taDAO.setTotalEarnings(taDAO.getTotalEarnings() + calculateFare(pickupPoint, dropPoint)[0]);
					
					TaxiEarningsDAO tedao=new TaxiEarningsDAO();
					List<TaxiEarningsDAO> ted= taxiEarnings.get(taxi);
					tedao.setCustomerId(custId);
					tedao.setStartPoint(pickupPoint);
					tedao.setEndPoint(dropPoint);
					tedao.setBookingId();
					tedao.setPickupTime(pickupTime);
					tedao.setFareAmount(calculateFare(pickupPoint, dropPoint)[0]);
					tedao.setDropTime((pickupTime+calculateFare(pickupPoint, dropPoint)[1]));
					tedao.setTaxi(taxi);
					if(null==ted){
						ted = new ArrayList<>();
						taxiEarnings.put(taxi, ted);
					}
					ted.add(tedao);
					return tas[0];
				}
				
			}
			else if(tas[1]==0 && tas[2]>0 && (firstMinFareFlag || tas[2] <= minFare)){
				minFare=tas[2];
				taxi = tas[0];
				minDist = tas[1];
				firstMinFareFlag=false;
			}else if(tas[1] > 0 && tas[2]==0 && (minDistFlag == true || minDist <= tas[1])){
				minDistFlag=false;
				minFare=tas[2];
				taxi = tas[0];
				minDist = tas[1];
			}else if (tas[1]>0 && tas[2]>0 && (firstMinFareFlag || tas[2] < minFare) && (minDistFlag == true || minDist < tas[1])){
				minDistFlag=false;
				minFare=tas[2];
				taxi = tas[0];
				minDist = tas[1];
				firstMinFareFlag = false;
			}
			
		}
		
		
		
		if(taxi > 0){
			TaxiAppDAO taDAO=taxiStatus.get(taxi);
			taDAO.setFirstTrip(false);
			taDAO.setCurrentStop(dropPoint);
			taDAO.setPickupTime(pickupTime);
			taDAO.setFreeTime((pickupTime+calculateFare(pickupPoint, dropPoint)[1]));
			taDAO.setTotalEarnings(taDAO.getTotalEarnings() + calculateFare(pickupPoint, dropPoint)[0]);
			
			TaxiEarningsDAO tedao=new TaxiEarningsDAO();
			List<TaxiEarningsDAO> ted= taxiEarnings.get(taxi);
			tedao.setCustomerId(custId);
			tedao.setStartPoint(pickupPoint);
			tedao.setEndPoint(dropPoint);
			tedao.setBookingId();
			tedao.setPickupTime(pickupTime);
			tedao.setFareAmount(calculateFare(pickupPoint, dropPoint)[0]);
			tedao.setDropTime((pickupTime+calculateFare(pickupPoint, dropPoint)[1]));
			tedao.setTaxi(taxi);
			if(null==ted){
				ted = new ArrayList<>();
				taxiEarnings.put(taxi, ted);
			}
			
			ted.add(tedao);
		}
		
		freeTaxiList.clear();
		return taxi;
		
	}
	
	public int[] calculateFare(char pickupPoint,char dropPoint){
		//Drop time and fare calculation
		int[] out=new int[2];
		int fare=0;
		int startIndex=new String(Properties.endPoints1).indexOf(pickupPoint);
		int endIndex=new String(Properties.endPoints1).indexOf(dropPoint);
		
		int distance=Math.abs(((endIndex-startIndex)*15))-5;
		fare=100+distance*10;
		out[0]=fare;
		out[1]=Math.abs(endIndex-startIndex);
		return out;
	}
	
}
