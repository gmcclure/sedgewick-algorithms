public class SAP {

    private Digraph G;
    private BreadthFirstDirectedPaths bfs_v, bfs_w;
    private int[] distances;

    public SAP(Digraph G) {
        this.G = G;
        this.distances = new int[G.V()];
    }

    public int length(int v, int w) {
        bfs_v = new BreadthFirstDirectedPaths(G, v);
        bfs_w = new BreadthFirstDirectedPaths(G, w);

        for (int i = 0; i < G.V(); i++) {
            if (bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i)) {
                distances[i] = bfs_v.distTo(i) + bfs_w.distTo(i);
            } else {

            }
        }

        return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            /* int length   = sap.length(v, w); */
            /* int ancestor = sap.ancestor(v, w); */
            /* StdOut.printf("length = %d, ancestor = %d\n", length, ancestor); */
        }
    }
}
