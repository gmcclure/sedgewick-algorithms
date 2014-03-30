public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int i = 0, N = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) rq.enqueue(StdIn.readString());
        while (i < N) { StdOut.println(rq.dequeue()); i++; }
    }
}
