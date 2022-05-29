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

    /**
     * Este método nos ayuda a añadir automóviles a la cola de acceso,
     * siempre y cuando la cola no esté llena.
     *
     * @param carToAdd automóvil a añadir a la cola de acceso.
     * @return true si se ha añadido el automóvil a la cola de acceso, false en caso contrario.
     * */
    public boolean addCar(Car carToAdd){
        return access.size() < MAX_CARS_IN_QUEUE ? access.offer(carToAdd) : false; 
    }

    /**
     * Este método nos ayuda a sacar automóviles de la cola de acceso,
     * para que puedan pasar a la línea de lavado.
     *
     * @return el automóvil que ha salido de la cola de acceso.
     * */
    public Car removeCar(){
        return !access.isEmpty() ? access.poll() : null; 
    }
    
}
