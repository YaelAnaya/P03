/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yael.ao.uabc.p03;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author yaela
 */
public class WashMachine {
    private int timeRemaining;
    private boolean machineInUse;
    private final ArrayBlockingQueue<Car> carsInWash;
    private final int DEFAULT_WASH_TIME = 3;
    
    public WashMachine(){
        carsInWash = new ArrayBlockingQueue<>(3, true);
        machineInUse = false;
        timeRemaining = DEFAULT_WASH_TIME;
    }

    public ArrayBlockingQueue<Car> getCarsInWash() {
        return carsInWash;
    }

    public boolean addCar(Car carToAdd){
        return carToAdd != null ? carsInWash.offer(carToAdd) : false;
    }
    
    public Car removeCar(){
        if (!carsInWash.isEmpty() && carsInWash.peek().getSolicitedService().equals(CarWashServices.WASH))
            return carsInWash.poll();
        
        return null;
    }
    
    public void wash(){
        if (carsInWash.peek() != null){
            if (machineInUse) {
                timeRemaining--;
                if (timeRemaining == 0) {
                    timeRemaining = DEFAULT_WASH_TIME;
                    machineInUse = false;
                    carsInWash.peek().setSolicitedService(CarWashServices.WASH);
                }
            }
            else
                machineInUse = true;
        }
    }

    public boolean isLineFull(){
        return carsInWash.size() == 3;
    }
}
