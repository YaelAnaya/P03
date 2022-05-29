package yael.ao.uabc.p03;

import java.util.Locale;

public class CarWash {
    /**
     * Se crea una instancia de la clase CarWashControl
     * para poder realizar el proceso de simulación en el método
     * main.
     * */
    private static CarWashControl control = new CarWashControl();

    public static void main(String[] args) {
        String choice = "";
        /**
         * Con este bucle se realizarán las simulaciones que
         * el usuario desee. Una vez terminada la simulación
         * se mostrará el contenido del registro de la jornada.
         * */
        do {
        control.startNewDay();
        control.startWorkDay();
        System.out.println(control.makeFinalRecord());

        System.out.println("Quiere iniciar un nuevo día? (s/n)");
        choice = new java.util.Scanner(System.in).nextLine();

        } while (choice.toLowerCase(Locale.ROOT).equals("s"));

    }
}
