package co.edu.uptc.models;

public class User {
    private int age;
    private String name;
    private String turnType;
    private String turnID;
    private boolean isPregnant;

    public User(int age, String name, String turnID, boolean isPregnant) {
        this.age = age;
        this.name = name;
        this.turnType = turnType;
        this.turnID = turnID;
        this.isPregnant = isPregnant;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTurnType() {
        return turnType;
    }

    public void setTurnType(String turnType) {
        this.turnType = turnType;
    }

    public String getTurnID() {
        return turnID;
    }

    public void setTurnID(String turnID) {
        this.turnID = turnID;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }
}
