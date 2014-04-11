public class SAP {

    private Digraph G;
    private BreadthFirstDirectedPaths bfsV, bfsW;
    private Path shortest;

    public SAP(Digraph G) {
        this.G = new Digraph(G);
        this.shortest = null;
    }

    private void checkPaths(
        int v,
        int w,
        BreadthFirstDirectedPaths dpV,
        BreadthFirstDirectedPaths dpW) {

        this.shortest = null;

        for (int i = 0; i < G.V(); i++) {
            if (dpV.hasPathTo(i) && dpW.hasPathTo(i)) {
                int l = dpV.distTo(i) + dpW.distTo(i);
                if (this.shortest == null || l < this.shortest.length)
                    this.shortest = new Path(v, w, l, i);
            }
        }

        if (shortest == null) this.shortest = new Path(v, w, -1, -1);
    }

    private void checkPaths(
        Iterable<Integer> v,
        Iterable<Integer> w,
        BreadthFirstDirectedPaths dpV,
        BreadthFirstDirectedPaths dpW) {

        this.shortest = null;

        for (int i = 0; i < G.V(); i++) {
            if (dpV.hasPathTo(i) && dpW.hasPathTo(i)) {
                int l = dpV.distTo(i) + dpW.distTo(i);
                if (this.shortest == null || l < this.shortest.length)
                    this.shortest = new Path(v, w, l, i);
            }
        }

        if (shortest == null) this.shortest = new Path(v, w, -1, -1);
    }

    private void bfs(int v, int w) {
        bfsV = new BreadthFirstDirectedPaths(G, v);
        bfsW = new BreadthFirstDirectedPaths(G, w);

        checkPaths(v, w, bfsV, bfsW);
    }

    private void bfs(Iterable<Integer> v, Iterable<Integer> w) {
        bfsV = new BreadthFirstDirectedPaths(G, v);
        bfsW = new BreadthFirstDirectedPaths(G, w);

        checkPaths(v, w, bfsV, bfsW);
    }

    public int length(int v, int w) {
        if (v < 0 || w < 0 || v > G.V() - 1 || w > G.V() - 1)
            throw new IndexOutOfBoundsException();

        if (shortest != null && shortest.V == v && shortest.W == w)
            return shortest.length;

        bfs(v, w);
        return shortest.length;
    }

    public int ancestor(int v, int w) {
        if (v < 0 || w < 0 || v > G.V() - 1 || w > G.V() - 1)
            throw new IndexOutOfBoundsException();

        if (shortest != null && shortest.V == v && shortest.W == w)
            return shortest.ancestor;

        bfs(v, w);
        return shortest.ancestor;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        for (int t : v) { if (t < 0 || t > G.V() - 1) 
            throw new IndexOutOfBoundsException(); }
        
        for (int t : w) { if (t < 0 || t > G.V() - 1) 
            throw new IndexOutOfBoundsException(); }

        if (shortest != null && shortest.iterV == v && shortest.iterW == w)
            return shortest.length;

        bfs(v, w);
        return shortest.length;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        for (int t : v) { if (t < 0 || t > G.V() - 1) 
            throw new IndexOutOfBoundsException(); }

        for (int t : w) { if (t < 0 || t > G.V() - 1) 
            throw new IndexOutOfBoundsException(); }

        if (shortest != null && shortest.iterV == v && shortest.iterW == w)
            return shortest.ancestor;

        bfs(v, w);
        return shortest.ancestor;
    }

    private static class Path {
        private int V, W;
        private Iterable<Integer> iterV, iterW;
        private int length;
        private int ancestor;

        public Path(int v, int w, int l, int a) {
            this.V = v;
            this.W = w;
            this.length = l;
            this.ancestor = a;
        }

        public Path(Iterable<Integer> v, Iterable<Integer> w, int l, int a) {
            this.iterV = v;
            this.iterW = w;
            this.length = l;
            this.ancestor = a;
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
