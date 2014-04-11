import java.util.HashMap;
import java.util.LinkedList;


public class WordNet {
    private Digraph dg;
    private HashMap<String, LinkedList<Integer>> nouns;
    private HashMap<Integer, String> syns;

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

        dg = new Digraph(sz);
        In hin = new In(hypernyms);

        while (hin.hasNextLine()) {
            String line = hin.readLine();
            String[] vals = line.split(",");
            int v = Integer.parseInt(vals[0]);
            for (int i = 1; i < vals.length; i++) {
                dg.addEdge(v, Integer.parseInt(vals[i]));
            }
        }

        hin.close();
    }

    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    public boolean isNoun(String word) {
        return nouns.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        LinkedList<Integer> aSyns = nouns.get(nounA);
        LinkedList<Integer> bSyns = nouns.get(nounB);
        SAP s = new SAP(dg);
        return s.length(aSyns, bSyns);
    }

    public String sap(String nounA, String nounB) {
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
