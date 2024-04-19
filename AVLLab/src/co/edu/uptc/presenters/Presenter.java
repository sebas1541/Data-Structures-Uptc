package co.edu.uptc.presenters;

import co.edu.uptc.models.Municipality;
import co.edu.uptc.models.Department;
import co.edu.uptc.models.MunicipalityComparator;
import co.edu.uptc.views.View;

public class Presenter {
    private Department department;
    private View view;

    public Presenter() {
        this.department = new Department(new MunicipalityComparator());
        this.view = new View();
        preloadData();
    }

    private void preloadData() {
        try {
            insertMunicipalityAndInhabitant("Tunja", "Juan Martinez");
            insertMunicipalityAndInhabitant("Tunja", "Maria Cañón");
            insertMunicipalityAndInhabitant("Duitama", "Carlos Salinas");
            insertMunicipalityAndInhabitant("Duitama", "Ana Torres");
            insertMunicipalityAndInhabitant("Sogamoso", "Luis Morales");
            insertMunicipalityAndInhabitant("Sogamoso", "Carmen Sánchez");
            insertMunicipalityAndInhabitant("Sogamoso", "Jorge Gómez");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    private void insertMunicipalityAndInhabitant(String municipalityName, String inhabitantName) throws Exception {
        Municipality mun = department.searchMunicipality(new Municipality(municipalityName));
        if (mun == null) {
            mun = new Municipality(municipalityName);
            department.insertMunicipality(mun);
        }
        mun.addInhabitant(inhabitantName);
    }

    public void run() {
        mainMenu();
    }

    private void mainMenu() {
        int option;
        do {
            view.showMessage("Menú Principal:\n1. Agregar habitante\n2. Mostrar habitantes de un municipio\n3. Municipio con más habitantes\n4. Salir");
            option = view.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    addInhabitant();
                    break;
                case 2:
                    showInhabitantsOfMunicipality();
                    break;
                case 3:
                    showMunicipalityWithMostInhabitants();
                    break;
                case 4:
                    view.showMessage("Saliendo de la aplicación");
                    break;
                default:
                    view.showMessage("Por favor intente de nuevo");
            }
        } while (option != 4);
    }

    private void addInhabitant() {
        String municipalityName = view.readString("Ingrese el nombre del municipio: ");
        String inhabitantName = view.readString("Ingrese el nombre del habitante: ");
        try {
            insertMunicipalityAndInhabitant(municipalityName, inhabitantName);
            view.showMessage("Habitante agregado con éxito.");
        } catch (Exception e) {
            view.showMessage("Error al agregar habitante: " + e.getMessage());
        }
    }

    private void showInhabitantsOfMunicipality() {
        String municipalityName = view.readString("Ingrese el nombre del municipio para mostrar sus habitantes:");
        Municipality mun = department.searchMunicipality(new Municipality(municipalityName));
        if (mun != null) {
            view.showMessage("Habitantes de " + municipalityName + ":");
            mun.getInhabitantNames().forEach(view::showMessage);
        } else {
            view.showMessage("No se encontró el municipio.");
        }
    }

    private void showMunicipalityWithMostInhabitants() {
        Municipality mostPopulous = department.getMunicipalityWithMostInhabitants();
        if (mostPopulous != null) {
            view.showMessage("Municipio con más habitantes: " + mostPopulous.getName() +
                    " con " + mostPopulous.getInhabitantCount() + " habitantes.");
        } else {
            view.showMessage("No hay municipios registrados.");
        }
    }
}
