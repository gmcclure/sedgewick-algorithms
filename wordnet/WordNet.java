import java.util.Iterator;
import java.util.HashMap;
import java.util.LinkedList;


public class WordNet {
    public /* private */ Digraph dg;
    public /* private */ HashMap<String, LinkedList<Integer> > nouns;

    public WordNet(String synsets, String hypernyms) {
        In sin = new In(synsets);
        nouns = new HashMap<String, LinkedList<Integer> >();
        int sz = 0;

        while (sin.hasNextLine()) {
            String line = sin.readLine();
            String[] vals = line.split(",");
            String[] words = vals[1].split(" ");
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

    public Iterable<string> nouns() {
        return nouns.keySet();
    }

    public boolean isNoun(String word) {
        return nouns.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
    }

    public String sap(String nounA, String nounB) {
    }
}
