package yael.ao.uabc.p03;

public class CarRecord {
    private Car registeredCar;
    private int exitHour;
    private int exitMinute;

    public CarRecord(Car registeredCar, int exitHour, int exitMinute) {
        this.registeredCar = registeredCar;
        this.exitHour = exitHour;
        this.exitMinute = exitMinute;
    }

    /**
     * Con este método se recopila información de la salida del vehículo que se registró.
     *
     * @return String con la información de la salida del vehículo.
     * */
    @Override
    public String toString() {
        StringBuilder recordInfo = new StringBuilder();
        String exitTime = exitHour + ":" + ((exitMinute < 10) ? "0" + exitMinute : exitMinute);
        String serviceType = (registeredCar.isExpressService() ? "Secado Express" : "Aspirado");

        recordInfo.append("\t\tAutomóvil con ID = " + registeredCar.getId() + "\n")
                  .append("\t\tTiempo de Salida = [" + exitTime + "]\n")
                  .append("\t\t[" + serviceType + "]\n")
                  .append("\t\tTamaño = " + registeredCar.getSize() + "\n")
                  .append("\t\tCliente Preferente = " + registeredCar.isPreferredCustomer() + "\n");

        return recordInfo.toString();
    }
}
