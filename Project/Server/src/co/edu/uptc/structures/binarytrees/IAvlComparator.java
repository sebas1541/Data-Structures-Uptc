package co.edu.uptc.structures.binarytrees;

public interface IAvlComparator<T> {
    boolean isEqualTo(T a, T b);
    boolean isLessThan(T a, T b);
    boolean isLessThanOrEqualTo(T a, T b);
    boolean isGreaterThan(T a, T b);
    boolean isGreaterThanOrEqualTo(T a, T b);

}