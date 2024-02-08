package test;

import models.NumberSimpleList;

public class Main {
    public static void main(String[] args) {

        NumberSimpleList numberSimpleList = new NumberSimpleList();

        numberSimpleList.insert(3);
        numberSimpleList.insert(5);
        numberSimpleList.insert(9);
        numberSimpleList.insert(11);
        numberSimpleList.remove(5);
        System.out.println(numberSimpleList.show());


    }
}
