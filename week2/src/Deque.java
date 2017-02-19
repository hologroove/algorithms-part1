import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by elsziva on 2/17/17.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

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
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null) {
            oldFirst.prev = first;
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
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (oldLast !=null) {
            oldLast.next = last;
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
        Deque<Integer> deque = new Deque<>();
        System.out.println("TEST1");
        System.out.println("size:" + deque.size());

        for (Integer i = 0; i < 10; i++) {
            deque.addLast(i);
            //System.out.println("AddLast " + i);
        }
        System.out.println("size:" + deque.size());

        System.out.println("Iterator:");
        for (Integer item : deque) {
            System.out.println(item);
        }

        for (Integer i = 0; i < 10; i++) {
            Integer item = deque.removeFirst();
            System.out.println("RemoveFirst " + item);
        }
        System.out.println("size:" + deque.size());

        System.out.println("TEST2");
        System.out.println("size:" + deque.size());

        for (Integer i = 0; i < 10; i++) {
            deque.addLast(i);
            //System.out.println("AddLast " + i);
        }
        System.out.println("size:" + deque.size());

        for (Integer i = 0; i < 10; i++) {
            Integer item = deque.removeLast();
            System.out.println("RemoveLast " + item);
        }
        System.out.println("size:" + deque.size());
    }

}
