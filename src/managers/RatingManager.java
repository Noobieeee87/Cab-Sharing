package managers;

import models.Trip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingManager {
    public Map<String, Double> getRatings() {
        return ratings;
    }

    private Map<String,Double> ratings;
    private static RatingManager ratingManager;

    private RatingManager(){
            ratings = new HashMap<>();
    }
    public static RatingManager getInstance(){
        if (ratingManager == null) ratingManager = new RatingManager();
        return ratingManager;
    }
    public void updatePassengerRating(Trip trip,List <Trip> trips){
        double previousRating = ratings.getOrDefault(trip.getPassengerId(),0.0);
        previousRating *= (trips.size() - 1);
        previousRating += trip.getPassengerRating();
        previousRating /= trips.size();
        ratings.put(trip.getPassengerId(),previousRating);
    }

    /*

        trips - > 2
        avgRating -> 4
        rating -> 3

        (4 * 2 + 3 ) / 3
        (4 * (2) + 3 )/ 3
     */
    public void updateDriverRating(Trip trip,List <Trip> trips){
        double previousRating = ratings.getOrDefault(trip.getDriverId(),0.0);
        previousRating *= (trips.size() - 1);
        previousRating += trip.getDriverRating();
        previousRating /= trips.size();
        ratings.put(trip.getDriverId(),previousRating);
    }
    public void printRating(){
        for (String user : ratings.keySet()){
            System.out.println(user+" --- > rating "+ratings.get(user));
        }
    }
}
