package co.edu.uptc.presenters;

import co.edu.uptc.models.MyDictionary;
import co.edu.uptc.views.View;

public class Presenter {
    private MyDictionary spanishEnglishDictionary;
    private MyDictionary spanishFrenchDictionary;
    private View view;

    public Presenter() {
        this.spanishEnglishDictionary = new MyDictionary();
        this.spanishFrenchDictionary = new MyDictionary();
        this.view = new View();
        preloadDictionaries();
    }

    private void preloadDictionaries() {
        spanishEnglishDictionary.insertTranslation("casa", "house", null);
        spanishEnglishDictionary.insertTranslation("libro", "book", null);
        spanishEnglishDictionary.insertTranslation("manzana", "apple", null);

        spanishFrenchDictionary.insertTranslation("casa", null, "maison");
        spanishFrenchDictionary.insertTranslation("libro", null, "livre");
        spanishFrenchDictionary.insertTranslation("manzana", null, "pomme");
    }


    public void run() {
        mainMenu();
    }

    private void mainMenu() {
        int option;
        do {
            view.showMessage("Menú Principal\n1. Traducir del español al inglés\n2. Traducir del español al francés\n3. Agregar traducción al diccionario español-inglés\n4. Agregar traducción al diccionario español-francés\n5. Mostrar tamaños de los diccionarios\n6. Mostrar contenido de los diccionarios\n7. Salir");
            option = view.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    translateToEnglish();
                    break;
                case 2:
                    translateToFrench();
                    break;
                case 3:
                    addTranslationToSpanishEnglish();
                    break;
                case 4:
                    addTranslationToSpanishFrench();
                    break;
                case 5:
                    showDictionarySizes();
                    break;
                case 6:
                    displayDictionaries();
                    break;
                case 7:
                    view.showMessage("Saliendo de la aplicación");
                    break;
                default:
                    view.showMessage("Por favor, intente de nuevo");
            }
        } while (option != 7);
    }


    public void displayDictionaries() {
        view.showMessage("Contenido del Diccionario Español-Inglés:");
        spanishEnglishDictionary.displayDictionaryContents();
        view.showMessage("Contenido del Diccionario Español-Francés:");
        spanishFrenchDictionary.displayDictionaryContents();
    }


    private void translateToEnglish() {
        String spanishWord = view.readString("Ingrese la palabra en español para traducir al inglés: ");
        String translation = spanishEnglishDictionary.getEnglishTranslation(spanishWord);
        view.showMessage("Traducción: " + translation);
    }

    private void translateToFrench() {
        String spanishWord = view.readString("Ingrese la palabra en español para traducir al francés: ");
        String translation = spanishFrenchDictionary.getFrenchTranslation(spanishWord);
        view.showMessage("Traducción: " + translation);
    }

    private void addTranslationToSpanishEnglish() {
        String spanishWord = view.readString("Ingrese la palabra en español: ");
        String englishTranslation = view.readString("Ingrese su traducción al inglés: ");
        spanishEnglishDictionary.insertTranslation(spanishWord, englishTranslation, null);
        view.showMessage("¡Traducción agregada con éxito!");
    }

    private void addTranslationToSpanishFrench() {
        String spanishWord = view.readString("Ingrese la palabra en español: ");
        String frenchTranslation = view.readString("Ingrese su traducción al francés: ");
        spanishFrenchDictionary.insertTranslation(spanishWord, null, frenchTranslation);
        view.showMessage("¡Traducción agregada con éxito!");
    }

    private void showDictionarySizes() {
        int sizeEnglishDict = spanishEnglishDictionary.getTotalWords();
        int sizeFrenchDict = spanishFrenchDictionary.getTotalWords();
        view.showMessage("Tamaño del Diccionario Español-Inglés: " + sizeEnglishDict);
        view.showMessage("Tamaño del Diccionario Español-Francés: " + sizeFrenchDict);
    }
}
