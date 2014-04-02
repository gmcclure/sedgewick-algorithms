import org.junit.*;
import static org.junit.Assert.*;


public class WordNetTest {
    private WordNet wn;

    @Before
    public void setUp() {
        wn = new WordNet("data/synsets.txt", "data/hypernyms.txt");
    }

    @Test
    public void wordnetIsCreated() { assertTrue(this.wn instanceof WordNet); }

    @Test
    public void nounHashReturnsCorrectValues() {
        assertTrue(this.wn.nouns.get("'hood").contains(0));
        assertTrue(this.wn.nouns.get("Communion").contains(4024));
        assertTrue(this.wn.nouns.get("zymolysis").contains(82191));
    }

}
