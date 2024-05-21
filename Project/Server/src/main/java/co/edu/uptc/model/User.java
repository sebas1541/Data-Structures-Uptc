package co.edu.uptc.model;

import co.edu.uptc.structures.binarytrees.MyAvlTree;
import co.edu.uptc.structures.stacks.MyStack;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {
    private String userId;
    private String username;
    private String hashedPassword;
    private String email;
    private MyAvlTree<Transaction> transactions;
    private MyStack<Budget> budgetHistory;
    private FamilyGroup familyGroup;

    public User(String username, String password, String email) throws NoSuchAlgorithmException {
        this.userId = generateUserId();
        this.username = username;
        this.hashedPassword = hashPassword(password);
        this.email = email;
        this.transactions = new MyAvlTree<>(new TransactionComparator());
        this.budgetHistory = new MyStack<>();
        this.familyGroup = null; // Optional, can be set later
    }

    // Generate unique user ID
    private String generateUserId() {
        return Base64.getEncoder().encodeToString((username + System.currentTimeMillis()).getBytes());
    }

    // Hash password using SHA-256
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    // Verify password
    public boolean verifyPassword(String password) throws NoSuchAlgorithmException {
        return this.hashedPassword.equals(hashPassword(password));
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public MyAvlTree<Transaction> getTransactions() {
        return transactions;
    }

    public MyStack<Budget> getBudgetHistory() {
        return budgetHistory;
    }

    public FamilyGroup getFamilyGroup() {
        return familyGroup;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTransactions(MyAvlTree<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setBudgetHistory(MyStack<Budget> budgetHistory) {
        this.budgetHistory = budgetHistory;
    }

    public void setFamilyGroup(FamilyGroup familyGroup) {
        this.familyGroup = familyGroup;
    }

    // Methods for Managing Transactions
    public void addTransaction(Transaction transaction) throws Exception {
        transactions.insert(transaction);
    }

    public void removeTransaction(Transaction transaction) throws Exception {
        transactions.delete(transaction);
    }

    public Transaction findTransaction(Transaction transaction) {
        return transactions.search(transaction);
    }

    // Methods for Managing Budget History
    public void addBudget(Budget budget) {
        budgetHistory.push(budget);
    }

    public Budget undoLastBudget() {
        return budgetHistory.pop();
    }

    // Methods for Managing Family Group
    public void addFamilyMember(User member) {
        if (familyGroup == null) {
            familyGroup = new FamilyGroup(this.userId + "group"); // Use userId as groupId or another unique identifier
        }
        familyGroup.addMember(member);
    }

    public void removeFamilyMember(User member) {
        if (familyGroup != null) {
            familyGroup.removeMember(member);
        }
    }

    // Method for checking budget alerts
    public List<String> checkBudgetAlerts() {
        List<String> alerts = new ArrayList<>();
        Iterator<Budget> budgetIterator = budgetHistory.iterator();
        while (budgetIterator.hasNext()) {
            Budget budget = budgetIterator.next();
            double spent = 0;
            for (Transaction transaction : transactions.inOrder()) {
                if (transaction.getCategory().equals(budget.getCategory().getName())) {
                    spent += transaction.getAmount();
                }
            }
            if (spent > budget.getAmount()) {
                alerts.add("Budget exceeded for category: " + budget.getCategory().getName());
            }
        }
        return alerts;
    }
}
