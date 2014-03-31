public class SAP {

    private Digraph G;
    private BreadthFirstDirectedPaths bfs_v, bfs_w;
    private Path shortest;

    public SAP(Digraph G) {
        this.G = G;
        this.shortest = null;
    }

    private void bfs(int v, int w) {
        this.shortest = null;

        bfs_v = new BreadthFirstDirectedPaths(G, v);
        bfs_w = new BreadthFirstDirectedPaths(G, w);

        for (int i = 0; i < G.V(); i++) {
            if (bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i)) {
                int l = bfs_v.distTo(i) + bfs_w.distTo(i);
                if (this.shortest == null || l < this.shortest.length)
                    this.shortest = new Path(v, w, l, i);
            }
        }

        if (shortest == null) this.shortest = new Path(v, w, -1, -1);
    }

    public int length(int v, int w) {
        if (shortest != null && shortest.V == v && shortest.W == w) 
            return shortest.length;
        bfs(v, w);
        return shortest.length;
    }

    public int ancestor(int v, int w) {
        if (shortest != null && shortest.V == v && shortest.W == w) 
            return shortest.ancestor;
        bfs(v, w);
        return shortest.ancestor;
    }

    private static class Path {
        public int V, W;
        public int length;
        public int ancestor;

        public Path(int v, int w, int l, int a) {
            this.V = v;
            this.W = w;
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
