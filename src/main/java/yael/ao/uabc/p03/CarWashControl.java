package yael.ao.uabc.p03;

import java.util.*;

public class CarWashControl {
    private int registerCars;
    private Access accessLine;
    private WashMachine washMachine;
    private Vacuum vacuumLine;
    private ExpressDrying expressDryingLine;
    private ArrayList<Record> carWashRecords;
    private String time;

    public CarWashControl() {
        this.registerCars = 0;
        this.accessLine = new Access();
        this.washMachine = new WashMachine();
        this.vacuumLine = new Vacuum();
        this.expressDryingLine = new ExpressDrying();
        this.carWashRecords = new ArrayList<>();
    }

    public Car makeRandomCar(){
        Random random = new Random();
        CarSize size;
        boolean preferredCustomer, expressService;

        var randomNumber = random.nextInt(20);

        if( randomNumber < 10)
            size = CarSize.SMALL;
        else if( randomNumber > 10 && randomNumber < 17)
            size = CarSize.MEDIUM;
        else
            size = CarSize.LARGE;

        preferredCustomer = random.nextDouble() < 0.15;
        expressService = random.nextDouble() < 0.2;
        registerCars++;
        return new Car(registerCars, size, CarWashServices.getRandomService(), preferredCustomer, expressService);
    }

    public void addToAccessLine(){
        var addedCar = makeRandomCar();
        if (accessLine.addCar(addedCar))
            System.out.println(time + " El Automovil con Id: " + addedCar.getId() + " de tamaño " +
                               addedCar.getSize() + " se ha añadido a la línea de acceso.");
        else
            registerCars--;
    }

    public void addToWashMachine(){

    }
}



