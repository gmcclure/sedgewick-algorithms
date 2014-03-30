import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
        first = null;
        last = first;
    }

    public boolean isEmpty() { return N == 0; }

    public int size() { return N; }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node t = first;
        first = new Node();
        first.item = item;
        first.next = t;
        if (t != null) { t.prev = first; }
        N++;
        if (size() == 1) { last = first; }
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node t = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (t != null) {
            t.next = last;
            last.prev = t;
        }
        N++;
        if (size() == 1) { first = last; }
    }

    public Item removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Item item = last.item;
        last = last.prev;
        if (isEmpty()) first = null;
        N--;
        return item;
    }

    public Iterator<Item> iterator() { return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < 5; i++) { d.addFirst(i); }
        for (int i = 5; i < 10; i++) { d.addLast(i); }
        for (int i : d) { StdOut.println(i); }
    }
}
