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
public class ExpressDrying {
    private ArrayBlockingQueue<Car> dryingLine;
    private int remainingTime;
    private boolean machineInUse;
    private final int DEFAULT_DRYING_TIME = 3;

    public ExpressDrying(){
        this.dryingLine = new ArrayBlockingQueue<>(5, true);
        this.remainingTime = DEFAULT_DRYING_TIME;
        this.machineInUse = false;
    }

    public boolean addCar(Car carToAdd){
        return dryingLine.offer(carToAdd);
    }

    public Car removeCar(){
        if (!dryingLine.isEmpty() && dryingLine.peek().getSolicitedService().equals(CarWashServices.EXPRESS_DRYING))
            return dryingLine.poll();

        return null;
    }

    public void dry(){
        if (dryingLine.peek() != null) {
            if (machineInUse){
                remainingTime --;
                if (remainingTime == 0){
                    remainingTime = DEFAULT_DRYING_TIME;
                    machineInUse = false;
                    dryingLine.peek().setSolicitedService(CarWashServices.EXPRESS_DRYING);
                }
                else
                    machineInUse = true;
            }
        }
    }

    public boolean isLineFull(){
        return dryingLine.size() == 5;
    }
    
}
