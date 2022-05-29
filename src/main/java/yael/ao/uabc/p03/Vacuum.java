
package yael.ao.uabc.p03;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author yaela
 */
public class Vacuum {
    
    private ArrayBlockingQueue<Car>[] vacuumLines;
    private int [] vacuumTimes;
    private boolean [] vacuumsInUse;

    public Vacuum() {
        createLines();
        vacuumsInUse = new boolean[4];
        Arrays.fill(vacuumsInUse, false);
        vacuumTimes = new int[4];
    }

    public ArrayBlockingQueue<Car>[] getVacuumLines() {
        return vacuumLines;
    }

    /**
     * Este metodo llena el array que guarda las lineas de aspirado con colas vacias.
     * */
    private void createLines(){
        vacuumLines = new ArrayBlockingQueue[4];
        for (int i = 0; i < vacuumLines.length; i++) 
            vacuumLines[i] = new ArrayBlockingQueue<>(4, true);
    }

    /**
     * Este método inserta un auto en la cola de aspirado en la linea que tenga menos autos.
     * @param carToAdd El auto que se desea insertar.
     * @return Si se insertó el auto o no.
     * */
    public boolean addCar(Car carToAdd){
        if (carToAdd != null)
            return vacuumLines[searchAvailableQueue()].offer(carToAdd);
        return false;
    }

    /**
     * Este método remueve un auto de la cola de vacuums dependiendo de la linea en que se encuentra,
     * para ello verifica que la linea no contenga valores nulos y que el auto termino su servicio.
     *
     * @param line El índice correspondiente a la línea en la que se encuentra el auto.
     * @return El auto que se removió de la cola.
     * */
    public Car removeCar(int line){
        if (!vacuumLines[line].isEmpty() &&  vacuumLines[line].peek().isServiceDone())
            return vacuumLines[line].poll();

        return null;
    }

    /**
     * Este método busca la cola dentro del Array vacuumLines que tiene menos autos.
     * @return El indice de la cola que tiene menos autos.
     * */
    public int searchAvailableQueue(){
        int min = 0;
        for (int i = 0; i < vacuumLines.length; i++) {
            if(vacuumLines[i].size() < vacuumLines[min].size())
                min = i;
        }
        return min;
    }
    /**
     * Este método verifica si todas las ArrayBlockingQueue dentro del Array vacuumsLines están llenas.
     * @return Si están llenas o no.
     * */
    public boolean isFull(){
        int fullLines = 0;
        for (int i = 0; i < vacuumLines.length; i++)
            if (vacuumLines[i].size() == 4)
                fullLines++;

        return fullLines == vacuumLines.length;
    }

    /**
     * Este método verifica si todas las ArrayBlockingQueue dentro del Array vacuumsLines están vacías.
     * @return Si están vacías o no.
     * */
    public boolean isEmpty(){
        int emptyLines = 0;
        for (int i = 0; i < vacuumLines.length; i++)
            if (vacuumLines[i].isEmpty())
                emptyLines++;

        return emptyLines == vacuumLines.length;
    }

    /**
     * Este método se encarga de aspirar todos los autos de una determinada linea de aspirado.
     * @param machineIndex El índice de la línea que se desea aspirar.
     * */
    public void vacuumCar(int machineIndex){
        if (vacuumLines[machineIndex].peek() != null){
            /* Si la máquina no está en uso, se ocupa y asigna un tiempo
               de aspirado en base al tamaño del auto. */
            if (!vacuumsInUse[machineIndex]){
                vacuumsInUse[machineIndex] = true;
                setVacuumTimes(machineIndex);
                return;
            }
            /* Se decrementa el tiempo de las líneas ocupadas */
            vacuumTimes[machineIndex]--;

            /* Se desocupa la máquina de lavado */
            if (vacuumTimes[machineIndex] == 0){
                vacuumLines[machineIndex].peek().setServiceDone(true);
                vacuumsInUse[machineIndex] = false;
            }
        }
    }

    /**
     * Este método se encarga de aspirar todos los autos de todas las líneas de aspirado.
     * */
    public void vacuumLines(){
        for (int i = 0; i < vacuumLines.length; i++)
            vacuumCar(i);
    }

    /**
     * Este método se encarga de asignar un tiempo de aspirado a una determinada
     * línea de aspirado en base al tamaño del auto.
     * */
    public void setVacuumTimes(int lines) {
        switch (vacuumLines[lines].peek().getSize()) {
            case SMALL -> vacuumTimes[lines] = 5;
            case MEDIUM -> vacuumTimes[lines] = 7;
            case LARGE -> vacuumTimes[lines] = 10;
            default -> vacuumTimes[lines] = 0;
        }
    }

}

