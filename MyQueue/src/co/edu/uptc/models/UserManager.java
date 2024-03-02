package co.edu.uptc.models;

public class UserManager {
    private User user;

    public UserManager(User user) {
        this.user = user;
    }
    public UserManager(){

    }

    public boolean checkElder(){
        return user.getAge() >= 65;
    }

    public boolean checkPregnant(){
        return user.isPregnant();
    }


    public String checkTurnType(){
        String turnType = "";

        if (checkElder()){
            turnType = "A";
        } else if (checkPregnant()) {
            turnType = "E";
        }else{
            turnType = "N";
        }
        return turnType;
    }

}
