package co.edu.uptc.test;

import java.util.ArrayList;
import java.util.Iterator;

public class IterableTest {



    public void checkArray(){
        ArrayList<String> lista = new ArrayList<String >();
        lista.add("Hola");
        lista.add("Hello");

        Iterator<String> iter = lista.iterator();

        while (iter.hasNext()){
            System.out.println(iter.next() + "");
        }

    }



    public static void main(String[] args) {
        IterableTest iterableTest = new IterableTest();
        new IterableTest().checkArray();
    }
}
