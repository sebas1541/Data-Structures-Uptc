package co.edu.uptc.models;

import java.util.List;
import java.util.ArrayList;

/**
 * Employee management for calculating payment based on hours worked.
 * @version 1.2
 */
public class Employees {

    private int wagePerHour;
    private final List<String> workers = new ArrayList<>();
    private final List<Integer> hoursWorked = new ArrayList<>();

    public List<Integer> getHoursWorked() {
        synchronized (this) {
            return new ArrayList<>(hoursWorked);
        }
    }

    public void setHoursWorked(List<Integer> hoursWorked) {
        synchronized (this) {
            this.hoursWorked.clear();
            this.hoursWorked.addAll(hoursWorked);
        }
    }

    public int getWagePerHour() {
        synchronized (this) {
            return wagePerHour;
        }
    }

    public void setWagePerHour(int wagePerHour) {
        synchronized (this) {
            this.wagePerHour = wagePerHour;
        }
    }

    public List<String> getWorkers() {
        synchronized (this) {
            return new ArrayList<>(workers);
        }
    }

    public void setWorkers(List<String> workers) {
        synchronized (this) {
            this.workers.clear();
            this.workers.addAll(workers);
        }
    }

    public int calculatePayment() {
        synchronized (this) {
            int totalHours = hoursWorked.stream().mapToInt(Integer::intValue).sum();
            return totalHours * wagePerHour;
        }
    }

    public boolean removeWorker(String workerName) {
        synchronized (this) {
            int index = workers.indexOf(workerName);
            if (index != -1) {
                workers.remove(index);
                hoursWorked.remove(index);
                return true;
            }
            return false;
        }
    }

    public boolean updateHoursWorked(String workerName, int hours) {
        synchronized (this) {
            int index = workers.indexOf(workerName);
            if (index != -1) {
                hoursWorked.set(index, hours);
                return true;
            }
            return false;
        }
    }

    public double calculateAverageHoursWorked() {
        synchronized (this) {
            return hoursWorked.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }
    }
}
