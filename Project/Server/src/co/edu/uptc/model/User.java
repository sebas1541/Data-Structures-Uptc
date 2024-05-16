package co.edu.uptc.model;

import co.edu.uptc.structures.binarytrees.MyAvlTree;
import co.edu.uptc.structures.stacks.MyStack;
import co.edu.uptc.structures.binarytrees.IAvlComparator;

public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private MyAvlTree<Transaction> transactions;
    private MyStack<Budget> budgetHistory;
    private FamilyGroup familyGroup;

    public User(String userId, String username, String password, String email, IAvlComparator<Transaction> transactionComparator) {
        this.userId = userId;
        this.username = username;
        this.password = password; // Ideally, this should be hashed
        this.email = email;
        this.transactions = new MyAvlTree<>(transactionComparator);
        this.budgetHistory = new MyStack<>();
        this.familyGroup = null; // Optional, can be set later
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MyAvlTree<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(MyAvlTree<Transaction> transactions) {
        this.transactions = transactions;
    }

    public MyStack<Budget> getBudgetHistory() {
        return budgetHistory;
    }

    public void setBudgetHistory(MyStack<Budget> budgetHistory) {
        this.budgetHistory = budgetHistory;
    }

    public FamilyGroup getFamilyGroup() {
        return familyGroup;
    }

    public void setFamilyGroup(FamilyGroup familyGroup) {
        this.familyGroup = familyGroup;
    }

    // Methods for Managing Transactions
    public void addTransaction(Transaction transaction) throws Exception {
        transactions.insert(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        // Implement the removal logic
    }

    public Transaction findTransaction(Transaction transaction) {
        // Implement the search logic
        return null;
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
            familyGroup = new FamilyGroup(generateGroupId());
        }
        familyGroup.addMember(member);
    }

    public void removeFamilyMember(User member) {
        if (familyGroup != null) {
            familyGroup.removeMember(member);
        }
    }

    // Helper method to generate a unique group ID
    private String generateGroupId() {
        return "FG-" + System.currentTimeMillis();
    }
}
