import javafx.scene.transform.Scale;
import managers.TripManager;
import managers.UserManager;
import models.*;
import services.CabAllocationService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String []args){

        UserManager userManager = UserManager.getInstance();
        TripManager tripManager = TripManager.getInstance();

        // hardcoded data , take data from user input...

        List <Trip> trips = getTripData();
        for (Trip trip : trips){
            tripManager.addTrip(trip);
        }

        // Taking User Input ...

        Scanner in = new Scanner(System.in);

        while (true){
            System.out.println("Enter Number Of drivers");
            int driverCount = in.nextInt();
            List <User> driversAvailabe = new ArrayList<>();
            Car car1 = new Car("1234",CarType.SUV);

            for (int i=0;i<driverCount;i++){
                String str = in.next();
                String arr[] = str.split(",");
                User  user = new Driver(arr[0],arr[0],car1);
                user.setxCoordinate(Double.parseDouble(arr[1]));
                user.setyCoordinate(Double.parseDouble(arr[2]));

                userManager.addUser(user);
                driversAvailabe.add(user);
            }
            System.out.println("Enter Passenger Details");
            String passenger[] = in.next().split(",");

            User  user = new Passenger(passenger[0],passenger[0]);
            user.setxCoordinate(Double.parseDouble(passenger[1]));
            user.setyCoordinate(Double.parseDouble(passenger[2]));
            CabAllocationService cabAllocationService = new CabAllocationService();
            cabAllocationService.allocateCab(user,driversAvailabe);
        }

    }
    public  static List<Trip> getTripData(){
        List <Trip> trips = new ArrayList<>();
        trips.add(new Trip("c1","d1",5,4));
        trips.add(new Trip("c2","d1",4,5));
        trips.add(new Trip("c3","d1",2,1));
        trips.add(new Trip("c1","d2",1,5));
        trips.add(new Trip("c2","d2",5,5));
        trips.add(new Trip("c3","d2",5,4));
        trips.add(new Trip("c1","d3",2,3));
        trips.add(new Trip("c2","d3",5,4));
        trips.add(new Trip("c3","d3",3,3));
        return trips;
    }
}
