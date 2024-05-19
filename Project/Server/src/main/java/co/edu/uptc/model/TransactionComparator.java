package co.edu.uptc.model;

import co.edu.uptc.structures.binarytrees.IAvlComparator;

public class TransactionComparator implements IAvlComparator<Transaction> {
    @Override
    public boolean isEqualTo(Transaction t1, Transaction t2) {
        return t1.getTransactionId().equals(t2.getTransactionId());
    }

    @Override
    public boolean isLessThan(Transaction t1, Transaction t2) {
        return t1.getDateTime().isBefore(t2.getDateTime());
    }

    @Override
    public boolean isLessThanOrEqualTo(Transaction t1, Transaction t2) {
        return isLessThan(t1, t2) || isEqualTo(t1, t2);
    }

    @Override
    public boolean isGreaterThan(Transaction t1, Transaction t2) {
        return t1.getDateTime().isAfter(t2.getDateTime());
    }

    @Override
    public boolean isGreaterThanOrEqualTo(Transaction t1, Transaction t2) {
        return isGreaterThan(t1, t2) || isEqualTo(t1, t2);
    }
}
