package managers;

import models.Trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripManager {

    public Map<String, List<Trip>> getTrips() {
        return trips;
    }

    private Map<String, List<Trip>> trips;
    private RatingManager ratingManager;
    private static TripManager tripManager;

    private TripManager(){
        trips = new HashMap<>();
        ratingManager = RatingManager.getInstance();
    }
    public static TripManager getInstance(){
        if (tripManager == null) tripManager = new TripManager();
        return tripManager;
    }
    public void addTrip(Trip trip){
        addDriverTrip(trip);
        addPassengerTrip(trip);
        ratingManager.updateDriverRating(trip,trips.getOrDefault(trip.getDriverId(),new ArrayList<>()));
        ratingManager.updatePassengerRating(trip,trips.getOrDefault(trip.getPassengerId(),new ArrayList<>()));
    }
    public void addDriverTrip(Trip trip){
        List <Trip> list = trips.get(trip.getDriverId());
        if (list == null) list = new ArrayList<>();
        list.add(trip);
        trips.put(trip.getDriverId(),list);
    }
    public void addPassengerTrip(Trip trip){
        List <Trip> list = trips.get(trip.getPassengerId());
        if (list == null) list = new ArrayList<>();
        list.add(trip);
        trips.put(trip.getPassengerId(),list);
    }
    public void print(){
        ratingManager.printRating();
    }
}
