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

    public ArrayBlockingQueue<Car> getDryingLine() {
        return dryingLine;
    }

    /**
     * Este método se encarga de agregar un automóvil a la línea de secado express,
     * solo si la línea no está llena y el automóvil no sea null.
     *
     * @param carToAdd Automóvil a agregar a la línea de secado express.
     * @return true si se pudo agregar el automóvil a la línea de secado express,
     * false en caso contrario.
     * */
    public boolean addCar(Car carToAdd){
        if (dryingLine.size() < 5 && carToAdd != null) {
            return dryingLine.offer(carToAdd);
        }
        return false;
    }

    /**
     * Este método se encarga de retirar un automóvil de la línea de secado express,
     * solo si la línea no está vacía y el servicio del automóvil en el peek ya finalizó.
     *
     * @return El automóvil retirado de la línea de secado express.
     * */
    public Car removeCar(){
        if (!dryingLine.isEmpty() && dryingLine.peek().isServiceDone())
            return dryingLine.poll();

        return null;
    }

    /**
     * Con este método se seca el automóvil que se encuentra en el peek
     * de la línea de secado express.
     *
     * Al llegar la variable timeRemaining a 0 se termina el servicio,
     * reinicia el tiempo para el siguiente auto a lavar y se desocupa
     * un lugar en la cola.
     * */
    public void dry() {
        if (dryingLine.peek() != null) {
            if (!machineInUse) {
                machineInUse = true;
                return;
            }

            remainingTime--;

            if (remainingTime == 0) {
                remainingTime = DEFAULT_DRYING_TIME;
                machineInUse = false;
                dryingLine.peek().setServiceDone(true);
            }

        }

    }

    /**
     * Este método indica si la línea de secado express está llena.
     *
     * @return true si la línea de secado express está llena,
     * false en caso contrario.
     * */
    public boolean isLineFull(){
        return dryingLine.size() == 5;
    }
    
}
