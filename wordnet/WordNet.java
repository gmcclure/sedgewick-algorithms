import java.util.HashMap;
import java.util.LinkedList;


public class WordNet {
    private Digraph dg;
    private HashMap<String, LinkedList<Integer>> nouns;
    private HashMap<Integer, String> syns;
    private boolean[] ancestors;

    public WordNet(String synsets, String hypernyms) {
        In sin = new In(synsets);
        nouns = new HashMap<String, LinkedList<Integer>>();
        syns = new HashMap<Integer, String>();
        int sz = 0;

        while (sin.hasNextLine()) {
            String line = sin.readLine();
            String[] vals = line.split(",");
            String[] words = vals[1].split(" ");
            
            syns.put(Integer.parseInt(vals[0]), vals[1]);

            for (String w : words) {
                if (nouns.containsKey(w)) {
                    nouns.get(w).add(Integer.parseInt(vals[0]));
                } else {
                    LinkedList<Integer> v = new LinkedList<Integer>();
                    v.add(Integer.parseInt(vals[0]));
                    nouns.put(w, v);
                }
            }

            sz++;
        }

        sin.close();

        ancestors = new boolean[sz];
        dg = new Digraph(sz);
        In hin = new In(hypernyms);

        while (hin.hasNextLine()) {
            String line = hin.readLine();
            String[] vals = line.split(",");
            int v = Integer.parseInt(vals[0]);
            for (int i = 1; i < vals.length; i++) {
                dg.addEdge(v, Integer.parseInt(vals[i]));
                ancestors[v] = true;
            }
        }

        hin.close();

        if (notRootedDAG(dg)) {
            throw new IllegalArgumentException("not a rooted DAG");
        }
    }

    private boolean notRootedDAG(Digraph d) {
        int c = 0;
        for (boolean e : ancestors) { if (!e) c++; }
        if (c > 1) return true;

        Topological dc = new Topological(d);
        return !dc.hasOrder();
    }

    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    public boolean isNoun(String word) {
        return nouns.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("not a WordNet noun");
        LinkedList<Integer> aSyns = nouns.get(nounA);
        LinkedList<Integer> bSyns = nouns.get(nounB);
        SAP s = new SAP(dg);
        return s.length(aSyns, bSyns);
    }

    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("not a WordNet noun");
        LinkedList<Integer> aSyns = nouns.get(nounA);
        LinkedList<Integer> bSyns = nouns.get(nounB);
        SAP s = new SAP(dg);
        return syns.get(s.ancestor(aSyns, bSyns));
    }

    public static void main(String[] args) {
        WordNet wn = new WordNet(args[0], args[1]);
        while (!StdIn.isEmpty()) {
            String u = StdIn.readString();
            String v = StdIn.readString();
            StdOut.println("distance = " + wn.distance(u, v));
            StdOut.println("sap = " + wn.sap(u, v));
        }
    }
}
