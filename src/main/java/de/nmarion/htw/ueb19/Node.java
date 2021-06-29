package de.nmarion.htw.ueb19;

public class Node<T> {

    private T value;

    private Node<T> prev;

    private Node<T> next;

    public Node(T t) {
        this.value = t;
    }

    public void setValue(T v) {
        value = v;
    }

    public void setPrev(Node<T> p) {
        prev = p;
    }

    public void setNext(Node<T> n) {
        next = n;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public Node<T> getNext() {
        return next;
    }

}
