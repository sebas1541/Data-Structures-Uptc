package co.edu.uptc.test;
import co.edu.uptc.models.Addition;


public class Test {
    public static void main(String[] args) {
        Addition addition1 = new Addition("1212312234234234234234234234234233 14321534122 11235124562342 1213452345234");
        Addition addition2 = new Addition("12 12");
        System.out.println(addition1.add());
        System.out.println(addition2.add());
    }
}
