package co.edu.uptc.models;

import co.edu.uptc.structures.MyQueue;

public class TurnManager {

    private MyQueue<User> queue;
    private UserManager userManager;


    public TurnManager() {
        this.queue = new MyQueue<User>();
        this.userManager = new UserManager();
    }

    public void addUser(User user){
        queue.push(user);
    }

    public User getNextUser(){
        return queue.poll();
    }

    public String buildTurnId(){

        return "";
    }

}