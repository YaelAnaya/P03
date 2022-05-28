package yael.ao.uabc.p03;

public class Record {
    private Car car;
    private String exitTime;

    public Record(Car car, String exitTime) {
        this.car = car;
        this.exitTime = exitTime;
    }

    public Car getCar() {
        return this.car;
    }

    public String getExitTime() {
        return this.exitTime;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String toString() {
        return "[" + exitTime +"] "+ this.car.toString();
    }


}
