package co.edu.uptc.test;



import co.edu.uptc.models.Addit;
import co.edu.uptc.persistence.Persistence;

public class Test {
    public static void main(String[] args) {

        String numbers = new Persistence().fileToString("src/co/edu/uptc/resources/hola.txt");
        Addit ad1 = new Addit("10101010101010 11001100110011 11110000 111000111");
        Addit ad2 = new Addit(numbers);
        System.out.println(ad1.add());
        System.out.println(ad2.add());

    }
}
