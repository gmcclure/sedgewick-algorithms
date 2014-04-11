public class Outcast {
    private WordNet wn;

    public Outcast(WordNet wordnet) { wn = wordnet; }

    public String outcast(String[] nouns) {
        String o = "";
        int d = -1;

        for (String n : nouns) {
            int a = 0;
            for (String t : nouns) { a += wn.distance(n, t); }
            if (a > d) {
                o = n;
                d = a;
            }
        }

        return o;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
