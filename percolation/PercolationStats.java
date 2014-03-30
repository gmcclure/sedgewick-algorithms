public class PercolationStats {
    private int N, T;
    private double[] siteRatios;

    public PercolationStats(int N, int T) {
        this.N = N;
        this.T = T;
        this.siteRatios = new double[T];
    }

    public double mean() {
        return StdStats.mean(siteRatios);
    }

    public double stddev() { 
        if (siteRatios.length == 1) { return Double.NaN; }
        return StdStats.stddev(siteRatios); 
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(siteRatios.length));
    }

    public double confidenceHi() { 
        return mean() + ((1.96 * stddev()) / Math.sqrt(siteRatios.length));
    }

    public static void main(String[] args) {
        Percolation p;
        int n = Integer.parseInt(args[0]), t = Integer.parseInt(args[1]);

        PercolationStats pstats = new PercolationStats(n, t);

        for (int i = 0; i < pstats.T; i++) {
            p = new Percolation(pstats.N);
            int r, c, s = 0;

            while (!p.percolates()) {
                do {
                    r = StdRandom.uniform(1, pstats.N + 1);
                    c = StdRandom.uniform(1, pstats.N + 1);

                } while (p.isOpen(r, c));

                p.open(r, c);
                s++;
            }

            pstats.siteRatios[i] = (double) s / (double) (pstats.N * pstats.N);
        }

        StdOut.printf("%-23s = %f%n", "mean", pstats.mean());
        StdOut.printf("%-23s = %f%n", "stddev", pstats.stddev());
        StdOut.printf("95%% confidence interval = %f, %f\n", 
                pstats.confidenceLo(), pstats.confidenceHi());
    }
}
