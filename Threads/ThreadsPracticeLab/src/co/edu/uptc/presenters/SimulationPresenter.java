package co.edu.uptc.presenters;

import co.edu.uptc.models.Cashier;
import co.edu.uptc.models.Customer;
import co.edu.uptc.models.QueueFeeder;
import co.edu.uptc.structures.MyQueue;
import co.edu.uptc.views.View;

public class SimulationPresenter {
    private View view;
    private int totalCustomersEntered = 0;
    private long startTime;
    private long endTime;

    public SimulationPresenter() {
        this.view = new View();
    }

    public void run() {
        mainMenu();
    }

    private void mainMenu() {
        int option;
        do {
            view.showMessage("Menú Principal\n1. Ejecutar Simulación\n2. Salir");
            option = view.readInt("Elige una opción: ");
            switch (option) {
                case 1:
                    runSimulation();
                    break;
                case 2:
                    view.showMessage("Saliendo del programa");
                    break;
                default:
                    view.showMessage("Por favor, inténtalo de nuevo");
            }
        } while (option != 2);
    }

    private void runSimulation() {
        Cashier.resetTotalServed();
        Cashier.resetNextId();

        int numCashiers = view.readInt("Ingrese el número de cajeros: ");
        int simulationDuration = view.readInt("Cuántos minutos quiere que dure la simulación?");


        MyQueue<Customer> queue = new MyQueue<>();
        QueueFeeder feeder = new QueueFeeder(queue, simulationDuration);

        this.startTime = System.currentTimeMillis();
        this.totalCustomersEntered = simulationDuration;

        Cashier[] cashiers = new Cashier[numCashiers];
        for (int i = 0; i < numCashiers; i++) {
            cashiers[i] = new Cashier(queue);
            cashiers[i].start();
        }
        feeder.start();

        try {
            feeder.join();
            if (feeder.shouldStopSimulation()) {
                for (Cashier cashier : cashiers) {
                    cashier.interrupt();
                }

                displayStatistics(numCashiers, queue);
            }

            Thread.sleep(100);
            for (Cashier cashier : cashiers) {
                cashier.interrupt();
                cashier.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void displayStatistics(int numCashiers, MyQueue<Customer> queue) {
        this.endTime = System.currentTimeMillis() - startTime;
        view.showMessage("\nDatos de la simulación:\n\n" +
                "Número de puntos de atención habilitados: " + numCashiers + "\n" +
                "Cantidad de usuarios que ingresaron al establecimiento: " + totalCustomersEntered + "\n" +
                "Cantidad de usuarios atendidos: " + Cashier.getTotalServed() + "\n" +
                "Cantidad de usuarios que estaban siendo atendidos por los cajeros: " + (totalCustomersEntered - Cashier.getTotalServed()) + "\n" +
                "Cantidad de usuarios que quedaron sin atender en la cola: " + queue.size() + "\n" +
                "Tiempo de ejecución: " + endTime + " milisegundos\n");
    }
}
