package co.edu.uptc.model;

import co.edu.uptc.structures.binarytrees.IAvlComparator;

import java.io.Serializable;

public class TransactionComparator implements IAvlComparator<Transaction>, Serializable {
    @Override
    public boolean isEqualTo(Transaction t1, Transaction t2) {
        return t1.getTransactionId().equals(t2.getTransactionId());
    }

    @Override
    public boolean isLessThan(Transaction t1, Transaction t2) {
        return t1.getTransactionId().compareTo(t2.getTransactionId()) < 0;
    }

    @Override
    public boolean isLessThanOrEqualTo(Transaction t1, Transaction t2) {
        return t1.getTransactionId().compareTo(t2.getTransactionId()) <= 0;
    }

    @Override
    public boolean isGreaterThan(Transaction t1, Transaction t2) {
        return t1.getTransactionId().compareTo(t2.getTransactionId()) > 0;
    }

    @Override
    public boolean isGreaterThanOrEqualTo(Transaction t1, Transaction t2) {
        return t1.getTransactionId().compareTo(t2.getTransactionId()) >= 0;
    }
}
