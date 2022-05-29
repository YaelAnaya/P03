/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yael.ao.uabc.p03;

/**
 *
 * @author yaela
 */
public class Car implements Comparable<Car>{
    private int id;
    private CarSize size;
    private boolean preferredCustomer;
    private boolean expressService;
    private boolean serviceDone;

    public Car(int id, CarSize size, boolean expressService, boolean preferredCustomer) {
        this.id = id;
        this.size = size;
        this.expressService = expressService;
        this.preferredCustomer = preferredCustomer;
        this.serviceDone = false;
    }

    public int getId() {
        return id;
    }

    public CarSize getSize() {
        return size;
    }

    public boolean isPreferredCustomer() {
        return preferredCustomer;
    }

    public boolean isExpressService() {
        return expressService;
    }

    public boolean isServiceDone() {
        return serviceDone;
    }

    public void setServiceDone(boolean serviceDone) {
        this.serviceDone = serviceDone;
    }

    /**
     * Este método no ayuda a agregar elementos a la linea de acceso, ya que
     * en ella se utiliza una PriorityQueue.
     *
     * Para almacenar elemento en la PriorityQueue se toma como criterio
     * de prioridad el atributo preferredCustomer.
     * */
    @Override
    public int compareTo(Car carToCompare) {
        return Boolean.compare(preferredCustomer, carToCompare.preferredCustomer);
    }
}
