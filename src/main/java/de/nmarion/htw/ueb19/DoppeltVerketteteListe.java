package de.nmarion.htw.ueb19;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DoppeltVerketteteListe<E> implements List<E> {

  private Node<E> head;
  private Node<E> tail;

  @Override
  public int size() {
    int size = 0;
    Node<E> cur = head;
    while (cur != null) {
      size++;
      cur = cur.getNext();
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public boolean contains(Object o) {
    boolean found = false;
    if (head != null) {
      Node<E> cur = head;
      while (cur != null && cur.getValue() != o) {
        cur = cur.getNext();
      }
      if (cur != null) {
        found = true;
      }
    }
    return found;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {

      private int currentIndex = 0;

      @Override
      public boolean hasNext() {
        return currentIndex < size();
      }

      @Override
      public E next() {
        return get(currentIndex++);
      }
    };
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T[] toArray(T[] array) {
    int size = size();
    if (array.length < size) {
      array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
    } else if (array.length > size) {
      array[size] = null;
    }

    int i = 0;
    for (E e : this) {
      array[i] = (T) e;
      i++;
    }
    return array;
  }

  @Override
  public boolean add(E e) {
    Node<E> node = new Node<E>(e);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      node.setPrev(tail);
      tail.setNext(node);
      tail = node;
    }
    return true;
  }

  @Override
  public boolean remove(Object o) {
    boolean found = false;
    if (head != null) {
      Node<E> cur = head;
      while (cur != null && cur.getValue() != o) {
        cur = cur.getNext();
      }
      if (cur != null) {
        found = true;
        Node<E> prev = cur.getPrev();
        Node<E> next = cur.getNext();
        if (prev != null) {
          prev.setNext(next);
        } else {
          head = next;
        }
        if (next != null) {
          next.setPrev(prev);
        } else {
          tail = prev;
        }
        cur.setPrev(null);
        cur.setNext(null);
      }
    }
    return found;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    if (c.stream().anyMatch(obj -> Objects.isNull(obj))) {
      throw new NullPointerException();
    }
    c.stream().forEach(obj -> add(obj));
    return true;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    tail = null;
    head = null;
  }

  @Override
  public E get(int index) {
    Node<E> cur = head;
    int curIndex = 0;
    while (cur != null) {
      if (curIndex == index) {
        return cur.getValue();
      }
      cur = cur.getNext();
      curIndex++;
    }
    throw new IndexOutOfBoundsException(index);
  }

  @Override
  public E set(int index, E element) {
    Node<E> cur = head;
    int curIndex = 0;
    while (cur != null) {
      if (curIndex == index) {
        final E value = cur.getValue();
        cur.setValue(element);
        return value;
      }
      cur = cur.getNext();
      curIndex++;
    }
    throw new IndexOutOfBoundsException(index);
  }

  @Override
  public void add(int index, E element) {
    Node<E> cur = head;
    int curIndex = 0;
    while (cur != null) {
      if (curIndex == index) {
        Node<E> newNode = new Node<E>(element);
        newNode.setNext(cur);
        newNode.setPrev(cur.getPrev());
        cur.getPrev().setNext(newNode);
        cur.setPrev(newNode);
        return;
      }
      cur = cur.getNext();
      curIndex++;
    }
    throw new IndexOutOfBoundsException(index);
  }

  @Override
  public E remove(int index) {
    Node<E> cur = head;
    int curIndex = 0;
    while (cur != null) {
      if (curIndex == index) {
        remove(cur.getValue());
        return cur.getValue();
      }
      cur = cur.getNext();
      curIndex++;
    }
    throw new IndexOutOfBoundsException(index);
  }

  @Override
  public int indexOf(Object o) {
    if (head != null) {
      Node<E> cur = head;
      int index = 0;
      while (cur != null && cur.getValue() != o) {
        cur = cur.getNext();
        index++;
      }
      if (cur != null) {
        return index;
      }
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<E> listIterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream<E> stream() {
    return StreamSupport.stream(
        Spliterators.spliteratorUnknownSize(this.iterator(), Spliterator.NONNULL), false);
  }
}
