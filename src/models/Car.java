package models;

public class Car {
    private String carNumber;
    private CarType carType;

    public Car(String carNumber, CarType carType) {
        this.carNumber = carNumber;
        this.carType = carType;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
