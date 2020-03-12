package models;

public class Driver extends User{
    private Car car;

    public Driver (String userId,String userName, Car car){
        super(userId,userName);
        this.car = car;  // add check for duplicate car....
    }
}
