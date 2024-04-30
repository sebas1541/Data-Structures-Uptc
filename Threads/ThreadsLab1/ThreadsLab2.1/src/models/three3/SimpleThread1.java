package models.three3;

public class SimpleThread1 extends Thread{
    public SimpleThread1(String str) {
        super(str);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + getName()
                    + " Priority = " + getPriority());
        }
        System.out.println("Done! " + getName());
    }
}
