package co.edu.uptc.test;

import co.edu.uptc.model.Student;
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
        DoubleList<Student> doubleList = new DoubleList<>();
        Student student1 = new Student("Nombre", 12, "Ingeniería");
        Student student2 = new Student("Hola", 12, "Medicina");

        doubleList.insert(student1);
        doubleList.insert(student2);

        System.out.println(doubleList.showInverted());


    }
}
