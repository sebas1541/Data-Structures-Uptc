package co.edu.uptc.test;

import co.edu.uptc.structures.DoubleList;
import co.edu.uptc.structures.SimpleList;

public class Main {
    public static void main(String[] args) {

        /**
        SimpleList <String> simpleList = new SimpleList();


        simpleList.insert("Hola");
        simpleList.insert("Hallo");
        simpleList.insert("Hello");
        simpleList.insert("Bonjour");

        //removiendo segundo nodo con valor entero 5
        simpleList.remove("Hola");

        //revisando existencia de valor en algún nodo, devuelve false o true
        System.out.println(simpleList.exist("Hello"));
        System.out.println(simpleList.exist("Ni Hao"));

        //imprimiendo el valor actual de la lista enlazada
        System.out.println(simpleList.show());

        */
        DoubleList<String> doubleList = new DoubleList<>();


        doubleList.insert("Hola");
        doubleList.insert("Hola");

        System.out.println(doubleList.show());


    }
}
