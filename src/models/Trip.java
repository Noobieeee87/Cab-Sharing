package models;

public class Trip {
    private String passengerId;
    private String driverId;
    private double passengerRating;
    private double driverRating;

    public Trip(String passenger, String driver, double passengerRating, double driverRating) {
        this.passengerId = passenger;
        this.driverId = driver;
        this.passengerRating = passengerRating;
        this.driverRating = driverRating;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public double getPassengerRating() {
        return passengerRating;
    }

    public void setPassengerRating(double passengerRating) {
        this.passengerRating = passengerRating;
    }

    public double getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(double driverRating) {
        this.driverRating = driverRating;
    }
}
