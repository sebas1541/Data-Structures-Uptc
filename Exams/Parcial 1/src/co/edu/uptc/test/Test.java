package co.edu.uptc.test;


import co.edu.uptc.persistence.Persistence;
import co.edu.uptc.models.Addit;

public class Test {
    public static void main(String[] args) {
        String numbers = new Persistence().fileToString("src/co/edu/uptc/resources/hola.txt");
        Addit ad = new Addit(numbers);
        Addit ad1 = new Addit("12 12 12 12");

        System.out.println(ad.add());
        System.out.println(ad1.add());
    }
}

