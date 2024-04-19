package co.edu.uptc.models;

import java.util.Comparator;

public class MunicipalityComparator implements Comparator<Municipality> {
    @Override
    public int compare(Municipality m1, Municipality m2) {
        return m1.getName().compareTo(m2.getName());
    }
}
