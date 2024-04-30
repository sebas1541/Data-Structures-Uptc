package test;

import models.NumberSimpleList;

public class Main {
    public static void main(String[] args) {

        NumberSimpleList numberSimpleList = new NumberSimpleList();

        //probando método insert, agregando 5 nodos a la lista
        numberSimpleList.insert(3);
        numberSimpleList.insert(5);
        numberSimpleList.insert(9);
        numberSimpleList.insert(11);

        //removiendo segundo nodo con valor entero 5
        numberSimpleList.remove(5);

        //revisando existencia de valor en algún nodo, devuelve false o true
        System.out.println(numberSimpleList.exist(9));
        System.out.println(numberSimpleList.exist(4));

        //imprimiendo el valor actual de la lista enlazada
        System.out.println(numberSimpleList.show());

    }
}
