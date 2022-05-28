/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yael.ao.uabc.p03;

import java.util.PriorityQueue;

/**
 *
 * @author yaela
 */
public class Access {
    private PriorityQueue<Car> access;
    private final int MAX_CARS_IN_QUEUE = 10 ;
    
    public Access(){
        access = new PriorityQueue<>(MAX_CARS_IN_QUEUE);
    }

    public PriorityQueue<Car> getAccessLine() {
        return access;
    }
    
    public boolean addCar(Car carToAdd){
        return access.size() < MAX_CARS_IN_QUEUE ? access.offer(carToAdd) : false; 
    }
    
    public Car removeCar(Car carToAdd){
        return !access.isEmpty() ? access.poll() : null; 
    }
    
}
