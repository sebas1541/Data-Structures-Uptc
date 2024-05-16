package co.edu.uptc.model;

import co.edu.uptc.structures.queues.MyQueue;

import java.util.ArrayList;
import java.util.List;

public class FamilyGroup {
    private String groupId;
    private List<User> members;
    private MyQueue<Transaction> transactionsQueue;

    public FamilyGroup(String groupId) {
        this.groupId = groupId;
        this.members = new ArrayList<>();
        this.transactionsQueue = new MyQueue<>();
    }

    // Getters and Setters
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public MyQueue<Transaction> getTransactionsQueue() {
        return transactionsQueue;
    }

    public void setTransactionsQueue(MyQueue<Transaction> transactionsQueue) {
        this.transactionsQueue = transactionsQueue;
    }

    // Methods for Managing Members
    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    // Methods for Managing Transactions
    public void addTransaction(Transaction transaction) {
        transactionsQueue.push(transaction);
    }

    public Transaction processNextTransaction() {
        return transactionsQueue.poll();
    }

    @Override
    public String toString() {
        return "FamilyGroup{" +
                "groupId='" + groupId + '\'' +
                ", members=" + members +
                ", transactionsQueue=" + transactionsQueue +
                '}';
    }
}
