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
    private CarWashServices solicitedService;
    private boolean preferredCustomer;
    private boolean expressService;
    private boolean serviceDone;
    private String entryTime;
    private String exitTime;

    public Car(int id, CarSize size,CarWashServices service, boolean expressService, boolean preferredCustomer) {
        this.id = id;
        this.size = size;
        this.solicitedService = service;
        this.expressService = expressService;
        this.preferredCustomer = preferredCustomer;
        this.serviceDone = false;
        this.entryTime = "";
        this.exitTime = "";
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

    public CarWashServices getSolicitedService() {
        return solicitedService;
    }

    public void setSolicitedService(CarWashServices solicitedService) {
        this.solicitedService = solicitedService;
    }

    public boolean isServiceDone() {
        return serviceDone;
    }

    public void setServiceDone(boolean serviceDone) {
        this.serviceDone = serviceDone;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public int compareTo(Car carToCompare) {
        return Boolean.compare(preferredCustomer, carToCompare.preferredCustomer);
    }

    @Override
    public String toString() {
        return "Vehiculo con " + "id: " + id +
                "\nTamaño: " + size +
                "\nServicio: " + solicitedService +
                "\nCliente preferente: " + preferredCustomer +
                "\nServicio Express: " + expressService;
    }
    
    
}
