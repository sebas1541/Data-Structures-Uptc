package co.edu.uptc.models;

import co.edu.uptc.structures.MyBinaryTree;
import java.util.List;
import java.util.Comparator;

public class Department {
    private MyBinaryTree<Municipality> municipalities;

    public Department(Comparator<Municipality> comparator) {
        this.municipalities = new MyBinaryTree<>(comparator);
    }

    public void insertMunicipality(Municipality municipality) {
        municipalities.insert(municipality);
    }

    public Municipality searchMunicipality(Municipality municipality) {
        return municipalities.search(municipality);
    }

    public Municipality getMunicipalityWithMostInhabitants() {
        List<Municipality> allMunicipalities = municipalities.preOrder();
        Municipality mostPopulous = null;
        int maxInhabitants = 0;
        for (Municipality mun : allMunicipalities) {
            int currentInhabitants = mun.getInhabitantCount();
            if (currentInhabitants > maxInhabitants) {
                maxInhabitants = currentInhabitants;
                mostPopulous = mun;
            }
        }
        return mostPopulous;
    }
}
