import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int N;

    public RandomizedQueue() { a = (Item[]) new Object[1]; }

    public int size() { return N; }
    public boolean isEmpty() { return size() == 0; }

    public void enqueue(Item item) {
        if (item == null) { throw new NullPointerException(); }
        if (N == a.length) { resize(a.length * 2); }
        a[N++] = item;
    }

    public Item dequeue() { 
        if (isEmpty()) { throw new UnsupportedOperationException(); }
        int i = StdRandom.uniform(N);
        Item item = a[i];
        exch(a, i, N-1);
        a[--N] = null;
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    public Item sample() { 
        if (isEmpty()) { throw new UnsupportedOperationException(); }
        return a[ StdRandom.uniform(N) ]; 
    }

    private void resize(int n) {
        Item[] t = (Item[]) new Object[n];
        for (int i = 0; i < N; i++) { t[i] = a[i]; }
        a = t;
    }

    private void exch(Item[] a, int i, int j) {
        Item t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }

    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private int curr = 0;
        private Item[] c = (Item[]) new Object[a.length];

        public RandomizedQueueIterator() { 
            StdRandom.shuffle(a, curr, N - 1); 
            for (int i = 0; i < N; i++) { c[i] = a[i]; }
        }

        public boolean hasNext() { return curr < N; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = c[curr++];
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 10; i++) rq.enqueue(i);
        for (int e : rq) { StdOut.println(e); }
    }
}
