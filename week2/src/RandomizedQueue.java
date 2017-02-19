import java.util.Iterator;
import java.util.stream.IntStream;

import edu.princeton.cs.algs4.StdRandom;
/**
 * Created by hologroove on 19/02/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int n; //number of items

    private void resize(int newLength) {
        Item[] temp = (Item[]) new Object[newLength];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item newItem) {
        if (n == items.length) {
            resize(2*items.length);
        }
        items[n++] = newItem;
    }

    public Item dequeue() {
        int index = StdRandom.uniform(n);
        Item picked = items[index];
        items[index] = items[n-1];
        items[n-1] = null;
        n--;
        return picked;
    }

    public Item sample() {
        int index = StdRandom.uniform(n);
        return items[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        int[] indices;
        int i;

        public RandomizedQueueIterator () {
            indices = IntStream.range(0, n).toArray();
            StdRandom.shuffle(indices);
            i = indices.length - 1;
        }

        public boolean hasNext() {
           return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return items[indices[i--]];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (Integer i = 0; i < 10; i++) {
            rq.enqueue(i*10);
        }
        //for (int i = 0; i < 10; i++) {
        //    System.out.println(rq.dequeue());
        //}
        System.out.println("tc2");
        Iterator<Integer> itr = rq.iterator();
        while (itr.hasNext()) {
            Integer elem = itr.next();
            System.out.println(elem);
        }

    }
}
