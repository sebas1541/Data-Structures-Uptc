package co.edu.uptc.structures.nonlinear;

public class NodeGraph<T> {
    private T value;

    public NodeGraph(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }


    public void setValue(T value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeGraph<?> node = (NodeGraph<?>) obj;
        return value.equals(node.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
