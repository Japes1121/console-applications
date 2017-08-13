package taxiBookingApp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookTaxi_Bkp implements Properties {

	//private TaxiAppDAO taxiStatus=new TaxiAppDAO();
	//private TaxiEarningsDAO taxiEarnings=new TaxiEarningsDAO();
	
	//private static List<TaxiAppDAO> taxiStatus=new ArrayList<>();
	//	private static List<TaxiEarningsDAO> taxiEarnings=new ArrayList<>();
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
		
		/*List<TaxiEarningsDAO> ted= taxiEarnings.get(1);
		te.setCustomerId(1);
		te.setStartPoint('A');
		te.setEndPoint('B');
		te.setBookingId();
		te.setPickupTime(10);
		te.setFareAmount(200);
		te.setDropTime(11);
		te.setTaxi(1);
		if(null==ted){
			ted = new ArrayList<>();
			taxiEarnings.put(1, ted);
		}
		ted.add(te);
		
		TaxiEarningsDAO te2=new TaxiEarningsDAO();
		List<TaxiEarningsDAO> ted1= taxiEarnings.get(2);
		te2.setCustomerId(2);
		te2.setStartPoint('A');
		te2.setEndPoint('D');
		te2.setBookingId();
		te2.setPickupTime(9);
		te2.setFareAmount(400);
		te2.setDropTime(12);
		te2.setTaxi(2);
		if(null==ted1){
			ted1 = new ArrayList<>();
			taxiEarnings.put(2, ted1);
		}
		ted1.add(te2);
		
		TaxiEarningsDAO te3=new TaxiEarningsDAO();
		te3.setCustomerId(2);
		te3.setStartPoint('C');
		te3.setEndPoint('F');
		te3.setBookingId();
		te3.setPickupTime(13);
		te3.setFareAmount(600);
		te3.setDropTime(16);
		te3.setTaxi(2);
		ted1.add(te3);*/
		
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
	
	public int bookTaxi(int custId, char pickupPoint, char dropPoint, int pickupTime){
		int noOfFreeTaxi=0;
		List<TaxiEarningsDAO> teDAO;
		List<int[]> freeTaxiList=new ArrayList<>();
		int source,dest,earnings=0;
		TaxiAppDAO ta;
		TaxiEarningsDAO te;
		int minDist=0;
		boolean minDistFlag=true;
		for(Map.Entry m:taxiStatus.entrySet()){ 
			earnings=0;
			ta=(TaxiAppDAO) m.getValue();
			source=new String(Properties.endPoints1).indexOf(pickupPoint);
			dest=new String(Properties.endPoints1).indexOf(ta.getCurrentStop());
			
			if(ta.isBooked() == false && ta.isFirstTrip()==true){
				noOfFreeTaxi=noOfFreeTaxi+1;
				int[] taxiStatus={ta.getTaxi(),Math.abs((source-dest)),0};
				
				//System.out.println("Taxi : "+taxiStatus[0]+" Dist : "+taxiStatus[1]+" Earnings : "+taxiStatus[2]);
				freeTaxiList.add(taxiStatus);
			}
			else if(ta.isBooked() == false && ta.getFreeTime() > pickupTime && ta.getPickupTime() < pickupTime && (minDistFlag==true || (Math.abs(source-dest)) < minDist)){				noOfFreeTaxi=noOfFreeTaxi+1;
				teDAO = taxiEarnings.get(ta.getTaxi());
				for(TaxiEarningsDAO teObj:teDAO){
					earnings=earnings+teObj.getFareAmount();
				}
				minDistFlag = false;
				minDist = Math.abs((source-dest));
				int[] taxiStatus={ta.getTaxi(),Math.abs((source-dest)),earnings};
				System.out.println("1111 Taxi : "+taxiStatus[0]+" Dist : "+taxiStatus[1]+" Earnings : "+taxiStatus[2]);
				freeTaxiList.add(taxiStatus);
			}
		}
		
		int minFare=0,taxi=0;
		boolean firstMinFareFlag=true;
		
		
		
		for(int[] tas:freeTaxiList){
			/*System.out.println("Taxi : "+tas[0]+" Dist : "+tas[1]+" Earnings : "+tas[2]);
			System.out.println("=======================================================");
			TaxiAppDAO tdd=taxiStatus.get(tas[0]);
			System.out.println("IsBooked : "+tdd.isBooked());
			System.out.println("First Trip : "+tdd.isFirstTrip());
			System.out.println("Current Stop : "+tdd.getCurrentStop());
			System.out.println("Pick Up Time: "+tdd.getPickupTime());
			System.out.println("Drop Time: "+tdd.getFreeTime());
			System.out.println("=======================================================");*/
		}
		
		
		
		for(int[] tas:freeTaxiList){
			if(tas[1]==0 && tas[2]==0){
				if(taxi > 0){
					TaxiAppDAO taDAO=taxiStatus.get(taxi);
					taDAO.setFirstTrip(false);
					taDAO.setCurrentStop(dropPoint);
					taDAO.setPickupTime(pickupTime);
					taDAO.setFreeTime((pickupTime+calculateFare(pickupPoint, dropPoint)[1]));
					
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
			else if(tas[1] > 0 && tas[2]==0){
				if(taxi > 0){
					TaxiAppDAO taDAO=taxiStatus.get(taxi);
					taDAO.setFirstTrip(false);
					taDAO.setCurrentStop(dropPoint);
					taDAO.setPickupTime(pickupTime);
					taDAO.setFreeTime((pickupTime+calculateFare(pickupPoint, dropPoint)[1]));
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
			else if(tas[1]==0 && tas[2] > 0 && firstMinFareFlag){
				minFare=tas[2];
				taxi = tas[0];
				firstMinFareFlag=false;
			}
			else if(tas[1]==0 && tas[2] > 0 && tas[2]<minFare){
				minFare=tas[2];
				taxi = tas[0];
			}
			
			if(minDistFlag==true && tas[2] < minFare){
				minFare=tas[2];
				taxi = tas[0];
				minDist = tas[1];
			}
			
		}
		
		
		
		if(taxi > 0){
			TaxiAppDAO taDAO=taxiStatus.get(taxi);
			taDAO.setFirstTrip(false);
			taDAO.setCurrentStop(dropPoint);
			taDAO.setPickupTime(pickupTime);
			taDAO.setFreeTime((pickupTime+calculateFare(pickupPoint, dropPoint)[1]));
			
			
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
		
		int distance=((endIndex-startIndex)*15)-5;
		fare=100+distance*10;
		out[0]=fare;
		out[1]=(endIndex-startIndex);
		return out;
	}
	
}
