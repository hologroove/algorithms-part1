import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by elsziva on 2/17/17.
 */
public class Deque<Item> implements Iterable {

    private Node first;
    private Node last;
    int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) {
            oldfirst.prev = first;
        }
        if (last == null) {
            last = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
        if (oldlast !=null) {
            oldlast.next = last;
        }
        if (first == null) {
            first = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (first == null) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (last == null) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {
        Deque deque = new Deque<Integer>();
        System.out.println("Initial size:" + deque.size());

        deque.addLast(1);
        System.out.println("Added 1:" + deque.size());
        deque.addLast(2);
        System.out.println("Added 2:" + deque.size());
        deque.addLast(3);
        System.out.println("Added 3:" + deque.size());
        deque.addLast(4);
        System.out.println("Added 4:" + deque.size());
        deque.addLast(5);
        System.out.println("Added 4:" + deque.size());

        System.out.println(deque.removeFirst());
        System.out.println("size now:" + deque.size());
        System.out.println(deque.removeFirst());
        System.out.println("size now:" + deque.size());
        System.out.println(deque.removeFirst());
        System.out.println("size now:" + deque.size());
        System.out.println(deque.removeFirst());
        System.out.println("size now:" + deque.size());
        System.out.println(deque.removeFirst());
        System.out.println("size now:" + deque.size());



    }

}
