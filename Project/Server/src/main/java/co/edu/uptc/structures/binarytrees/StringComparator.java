package co.edu.uptc.structures.binarytrees;

public class StringComparator implements IAvlComparator<String> {
    @Override
    public boolean isEqualTo(String a, String b) {
        return a.compareTo(b) == 0;
    }

    @Override
    public boolean isLessThan(String a, String b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public boolean isLessThanOrEqualTo(String a, String b) {
        return a.compareTo(b) <= 0;
    }

    @Override
    public boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }

    @Override
    public boolean isGreaterThanOrEqualTo(String a, String b) {
        return a.compareTo(b) >= 0;
    }
}