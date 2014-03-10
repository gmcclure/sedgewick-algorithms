public class PercolationStats {
    private int N, T;

    public PercolationStats(int N, int T) { ; }
    abstract double mean();
    abstract double stddev();
    abstract double confidenceLo();
    abstract double confidenceHi();
    abstract void main(String[] args);
}
