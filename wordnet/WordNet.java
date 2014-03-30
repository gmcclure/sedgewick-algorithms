import java.util.Iterator;


public class WordNet implements Iterable<String> {
    public WordNet(String synsets, String hypernyms) { }

    public Iterable<String> nouns();
    public boolean isNoun(String word);
    public int distance(String nounA, String nounB);
    public String sap(String nounA, String nounB);
    public static void main(String[] args);
}
