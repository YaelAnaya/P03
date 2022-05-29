package yael.ao.uabc.p03;

import java.util.*;

public class CarWashControl {
    private int registerCars;
    private Access accessLine;
    private WashMachine washMachine;
    private Vacuum vacuumLine;
    private ExpressDrying expressDryingLine;
    private ArrayList<CarRecord> carWashRecords;

    private int currentHour;
    private int currentMinute;
    private int currentDay;

    private int nextCarEntryHour;
    private int nextCarEntryMinute;
    private int workDayEndHour;


    public CarWashControl() {
        this.registerCars = 0;
        this.accessLine = new Access();
        this.washMachine = new WashMachine();
        this.vacuumLine = new Vacuum();
        this.expressDryingLine = new ExpressDrying();
        this.workDayEndHour = 18;
        this.carWashRecords = new ArrayList<>();
    }

    /**
     * Con este método se simula una jornada laboral del CarWash.
     * */
    public void startWorkDay(){
        boolean closeAccessLineStatus;
        getNextCarEntry();
        System.out.println("Iniciando el día " + currentDay + "\n");
        for (currentHour = 8; currentHour < workDayEndHour; currentHour++) {
            for (currentMinute = 0; currentMinute < 60; currentMinute++) {
                closeAccessLineStatus = closeAccessLine(currentHour, currentMinute);
                System.out.println(showCurrentTime());
                if (!closeAccessLineStatus && currentHour == nextCarEntryHour && currentMinute == nextCarEntryMinute) {
                    addToAccessLine();
                    getNextCarEntry();
                }
                addToWashMachine();
                washMachine.washCar();
                chooseService();
                vacuumLine.vacuumLines();
                expressDryingLine.dry();
                addCarToRecord();
            }
            extraHourNeeded(currentHour, currentMinute);
        }
    }

     /**
      * Con este método se agregan los autos que termina el servicio  y
      * el tiempo en que salieron al registro.
      * */
    private void addCarToRecord(){
        Car car;
        for (int i = 0; i < 4; i++) {
            if ((car = vacuumLine.removeCar(i)) != null)
                carWashRecords.add(new CarRecord(car, currentHour, currentMinute));
        }

        if ((car = expressDryingLine.removeCar()) != null)
            carWashRecords.add(new CarRecord(car, currentHour, currentMinute));
    }

    /**
     * Este método reinicia las variables necesarias para iniciar una nueva jornada laboral.
     * */
    public void startNewDay(){
        currentDay++;
        currentHour = 8;
        currentMinute = 0;
        workDayEndHour = 18;
        registerCars = 0;
        carWashRecords.clear();
    }

    /**
     * Con este método indicamos el momento en el que se
     * cierra la línea de acceso.
     * */
    private boolean closeAccessLine(int hour, int minute) {
        return (hour >= 17 && minute >= 45);
    }

    /**
     * Con este método se indica si se debe de agregar más tiempo
     * para terminar el servicio de los automóviles.
     * */
    private boolean extraHourNeeded(int hour, int minute) {
        return (hour >= 17 && minute == 60) && !checkIfLinesAreEmpty();
    }

    /**
     * Nos ayuda a saber si las líneas están vacías, esto ayuda a saber si
     * se debe de agregar más tiempo para terminar el servicio de los automóviles.
     * */
    private boolean checkIfLinesAreEmpty() {
        return accessLine.getAccessLine().isEmpty()
                && washMachine.getCarsInWash().isEmpty()
                && vacuumLine.isEmpty() && expressDryingLine.getDryingLine().isEmpty();
    }

    /**
     * Este método nos ayuda a calcular el tiempo en el que se generarán
     * los autos que entran a la línea de acceso.
     * */
    public void getNextCarEntry(){
        Random random = new Random();
        int time = random.nextInt(2, 9);
        if ((currentMinute + time) < 59) {
            nextCarEntryHour = currentHour;
            nextCarEntryMinute = currentMinute + time;
        }
        else {
            nextCarEntryHour = currentHour + 1;
            nextCarEntryMinute = currentMinute + time - 59;
        }

    }

    /**
     * Este método crea un auto con datos aleatorios basados en las
     * características solicitadas en la descripción del problema, para
     * agregarlo a la línea de acceso.
     * */
    public Car makeRandomCar() {
        Random random = new Random();
        CarSize size;
        boolean preferredCustomer, expressService;

        var randomNumber = random.nextInt(20);

        if (randomNumber < 10)
            size = CarSize.SMALL;
        else if (randomNumber > 10 && randomNumber < 17)
            size = CarSize.MEDIUM;
        else
            size = CarSize.LARGE;

        preferredCustomer = random.nextDouble() < 0.15;
        expressService = random.nextDouble() < 0.2;

        return new Car(registerCars + 1, size, expressService, preferredCustomer);
    }

    /**
     * Con este método se inserta el auto generado aleatoriamente
     * en la línea de acceso. Si la línea no está llena, se agrega el auto
     * y se muestra en consola un mensaje indicando si se agregó o no.
     * */
    public void addToAccessLine() {
        var addedCar = makeRandomCar();
        if (accessLine.addCar(addedCar)) {
            registerCars++;
            System.out.println("\t[Línea de Acceso]\n" +
                    "\t\tEl Automóvil con Id: " + addedCar.getId() + " de tamaño " +
                    addedCar.getSize() + " se ha añadido a la línea de acceso.\n");
            return;
        }
        System.out.println("\tLa línea de acceso está llena.\n");
    }

    /**
     * Este método agrega el auto con más prioridad a la línea de lavado.
     *  Si la línea no está llena, se agrega el auto y se muestra
     *  en consola un mensaje indicando si se agregó o no.
     * */
    public void addToWashMachine() {
        var carOnPeek = accessLine.getAccessLine().peek();
        if (washMachine.isLineFull()) {
            System.out.println("\tLa máquina de lavado está llena.\n");
            return;
        }

        else if (washMachine.addCar(carOnPeek)) {
            accessLine.getAccessLine().remove();
            System.out.println("\t[Máquina de lavado]\n" +
                    "\t\tEl Automóvil con Id: " + carOnPeek.getId() + " de tamaño " +
                    carOnPeek.getSize() + " se ha añadido a la máquina de lavado.\n");
        }
    }

    /**
     * Este método manda un vehículo a la línea de lavado a aspirado o secado express,
     * dependiendo del servicio solicitado. Si la línea no está llena, se agrega el auto
     * y se muestra  en consola un mensaje indicando si se agregó o no.
     * */
    public void chooseService() {
        var carOnPeek = washMachine.getCarsInWash().peek();
        if (carOnPeek == null)
            return;

        else if (carOnPeek.isExpressService() && !expressDryingLine.isLineFull()) {
            var addedCar = washMachine.removeCar();
            if (expressDryingLine.addCar(addedCar)) {
                System.out.println("\tEl Automóvil con Id: " + addedCar.getId() + " salio de la máquina de lavado.\n");
                System.out.println("\t[Secado Express]\n" +
                        "\t\tEl Automóvil con Id: " + addedCar.getId() + " de tamaño " +
                        addedCar.getSize() + " se ha añadido a la línea de secado express.\n");
            }
        }
        else if(!vacuumLine.isFull()) {
            var addedCar = washMachine.removeCar();
            if (vacuumLine.addCar(addedCar)) {
                System.out.println("\tEl Automóvil con Id: " + addedCar.getId() + " salio de la máquina de lavado.\n");
                System.out.println("\t[Aspirado]\n" +
                        "\t\tEl Automóvil con Id: " + addedCar.getId() + " de tamaño " +
                        addedCar.getSize() + " se ha añadido a la línea de aspirado.\n");
            }
        }
    }
    /**
     * Este método actualiza el tiempo transcurrido en la simulación, para
     * mostrarlo en consola.
     *
     * @return El tiempo transcurrido en la simulación.
     * */
    private String showCurrentTime(){
        StringBuilder time = new StringBuilder();
        time.append("Hora [" + currentHour).append(":").append((currentMinute < 10) ? "0" + currentMinute : currentMinute).append("]");
        return time.toString();
    }

    /**
     * Este método concatena la información de la simulación en una cadena de texto
     * para mostrarla en consola al terminar la jornada.
     *
     * @return La información de la simulación en una cadena de texto.
     * */
    public String makeFinalRecord(){
        StringBuilder finalRecord = new StringBuilder("\n\t[ Records del Día #" + currentDay + " ]\n\n");
        for (var record : carWashRecords)
            finalRecord.append(record.toString()).append("\n");

        return finalRecord.toString();

    }



}



