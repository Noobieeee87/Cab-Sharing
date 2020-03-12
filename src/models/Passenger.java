package models;

public class Passenger extends User{
    private CarType carPrefrence;

    public CarType getCarPrefrence() {
        return carPrefrence;
    }

    public void setCarPrefrence(CarType carPrefrence) {
        this.carPrefrence = carPrefrence;
    }

    public Passenger(String userId, String userName){
        super(userId, userName);
    }
}
