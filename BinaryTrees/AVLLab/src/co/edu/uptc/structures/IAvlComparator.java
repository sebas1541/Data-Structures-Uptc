package co.edu.uptc.structures;

import co.edu.uptc.models.Department;

public interface IAvlComparator<T> {
    boolean isEqualTo(T a, T b);
    boolean isLessThan(T a, T b);
    boolean isLessThanOrEqualTo(T a, T b);
    boolean isGreaterThan(T a, T b);
    boolean isGreaterThanOrEqualTo(T a, T b);
    
}