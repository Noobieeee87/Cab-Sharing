package services;

import managers.RatingManager;
import managers.TripManager;
import models.Passenger;
import models.Trip;
import models.User;

import java.util.*;

public class CabAllocationService {

    Map<String,Double> ratings;
    public void allocateCab(User passegnger, List<User> drivers){
        ratings = RatingManager.getInstance().getRatings();
        System.out.println("Average Rating of the passenger is ---> "+ratings.getOrDefault(passegnger.getUserId(),0.0));

        List <User> eligibleDrivers = getElgibleDriveres(passegnger,drivers);
        if (eligibleDrivers.size() == 0){
            System.out.println("No Matching Drivers Found !!!! ");
            return;
        }

        List <User> ratedDrivers = bestRatedDrivers(eligibleDrivers);
        List <User> nearestDrives = nearestDrivers(passegnger,eligibleDrivers);
        List <User> ratedNearest = ratedNearestDrivers(passegnger,eligibleDrivers);

        printRatedDrivers(ratedDrivers);
        printNearestDrivers(passegnger,nearestDrives);
        printRatedNearestDrivers(passegnger,ratedNearest);
    }
    private List<User> getElgibleDriveres(User passenger, List <User> drivers){
        List <User> eligibleDrivers = new ArrayList<>();
        Map<String,Double> ratings = RatingManager.getInstance().getRatings();
        Map <String,List<Trip>> trips = TripManager.getInstance().getTrips();

        List<Trip> passengerTrips = trips.get(passenger.getUserId());

        for (User driver : drivers){
            if (ratings.getOrDefault(driver.getUserId(),0.0) >= ratings.getOrDefault(passenger.getUserId(),0.0)) eligibleDrivers.add(driver);
        }
        if (eligibleDrivers.size() == 0){

            for (User driver : drivers){

                for (Trip trip : passengerTrips){
                    if (trip.getDriverId().equals(driver.getUserId()) && trip.getDriverRating() > 1.0)eligibleDrivers.add(driver);
                }
            }
        }
        return eligibleDrivers;
    }
    private List <User> bestRatedDrivers(List <User> drivers){
        List <User> driversClone = new ArrayList<>(drivers);
        Collections.sort(driversClone, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                double rating1 = ratings.getOrDefault(o1.getUserId(),0.0);
                double rating2 = ratings.getOrDefault(o2.getUserId(),0.0);
                return rating1 > rating2 ? -1 : rating1 < rating2 ? 1 : 0;
            }
        });
        return driversClone;
    }
    private List <User> nearestDrivers(User passenger,List <User> drivers){
        List <User> driversClone = new ArrayList<>(drivers);

        Collections.sort(driversClone, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                double dist1 = getDistance(passenger.getxCoordinate(),passenger.getyCoordinate(),o1.getxCoordinate(),o1.getyCoordinate());
                double dist2 = getDistance(passenger.getxCoordinate(),passenger.getyCoordinate(),o2.getxCoordinate(),o2.getyCoordinate());
                return dist1 < dist2 ? -1 : dist1 > dist2 ? 1 : 0;
            }
        });
        return driversClone;
    }
    private  double getDistance(double x1,double y1,double x2,double y2){
        return Math.sqrt((x1-x2) * (x1-x2)  + (y1-y2) * (y1-y2));
    }
    private List <User> ratedNearestDrivers(User passenger,List <User> drivers){
        List <User> driversClone = new ArrayList<>(drivers);

        Collections.sort(driversClone, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                double dist1 = getDistance(passenger.getxCoordinate(),passenger.getyCoordinate(),o1.getxCoordinate(),o1.getyCoordinate());
                double dist2 = getDistance(passenger.getxCoordinate(),passenger.getyCoordinate(),o2.getxCoordinate(),o2.getyCoordinate());
                double rating1 = ratings.getOrDefault(o1.getUserId(),0.0);
                double rating2 = ratings.getOrDefault(o2.getUserId(),0.0);
                double passRating = ratings.getOrDefault(passenger.getUserId(),0.0);

                if (rating1 != rating2){
                    return rating1 > rating2 ? -1 : 1;
                }
                else {
                    return dist1 < dist2 ? -1 : 1;
                }
            }
        });
        return driversClone;
    }
    private void printRatedDrivers(List <User> users){
        if (users.size() == 0){
            System.out.println("No Drivers Rating wise sorted found !!! ");
            return;
        }

        System.out.println("---------------");
        for (User user : users){
            System.out.println("Driver Id :: "+user.getUserId()+" --> rating :: "+ratings.getOrDefault(user.getUserId(),0.0));
        }
    }

    private void printNearestDrivers(User passenger, List <User> users){
        if (users.size() == 0){
            System.out.println("No Drivers Nearest Location found !!! ");
            return;
        }

        System.out.println("---------------");
        for (User user : users){
            System.out.println("Driver Id :: "+user.getUserId()+" --> distance :: "+getDistance(passenger.getxCoordinate(),passenger.getyCoordinate(),user.getxCoordinate(),user.getyCoordinate()));
        }
    }

    private void printRatedNearestDrivers(User passenger, List <User> users){
        if (users.size() == 0){
            System.out.println("No Drivers with rated + nearest location found !!! ");
            return;
        }
        System.out.println("---------------");
        for (User user : users){
            System.out.println("Driver Id :: "+user.getUserId()+" --> distance :: "+getDistance(passenger.getxCoordinate(),passenger.getyCoordinate(),user.getxCoordinate(),user.getyCoordinate())+" --> rating :: "+ratings.getOrDefault(user.getUserId(),0.0));
        }
    }
}
