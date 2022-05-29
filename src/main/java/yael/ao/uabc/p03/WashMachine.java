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
    /**
     * Con este método se agrega un carro a la cola de la máquina de lavado, siempre
     * y cuando la  cola no este llena y el carro no sea null.
     *
     * @param carToAdd Carro a agregar a la cola.
     * @return true si se pudo agregar el carro a la cola, false en caso contrario.
     * */
    public boolean addCar(Car carToAdd){
        return carToAdd != null ? carsInWash.offer(carToAdd) : false;
    }

    /**
     * Con este método se retira un carro de la cola de la máquina de lavado siempre
     * y cuando haya terminado de lavarse y la cola no este vacía.
     *
     * @return Carro retirado de la cola.
     * */
    public Car removeCar(){
        if (!carsInWash.isEmpty() && carsInWash.peek().isServiceDone()) {
            carsInWash.peek().setServiceDone(false);
            return carsInWash.poll();
        }
        
        return null;
    }

    /**
     * Con este método se lava el auto que se encuentra en el peek de la cola
     * de la máquina de lavado.
     *
     * Al llegar la variable timeRemaining a 0 se termina el lavado del carro,
     * reinicia el tiempo para el siguiente auto a lavar, se desocupa la máquina
     * e indica que el servicio se ha realizado para poder moverse a la siguiente línea.
     * */
    public void washCar(){
        if (carsInWash.peek() != null){
            if (!machineInUse) {
                machineInUse = true;
                return;
            }

            timeRemaining--;

            if (timeRemaining == 0) {
                timeRemaining = DEFAULT_WASH_TIME;
                machineInUse = false;
                carsInWash.peek().setServiceDone(true);
            }
        }
    }

    /**
     * Con este método indicamos si la línea de lavado esta llena o no.
     *
     * @return true si la línea de lavado esta llena, false en caso contrario.
     * */
    public boolean isLineFull(){
        return carsInWash.size() == 3;
    }
}
