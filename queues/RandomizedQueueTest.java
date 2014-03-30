import org.junit.*;
import static org.junit.Assert.*;


public class RandomizedQueueTest {
    private RandomizedQueue rq;

    @Before
    public void setUp() {
        rq = new RandomizedQueue<Integer>();
    }

    @Test
    public void isARandomizedQueue() { assertTrue(this.rq instanceof RandomizedQueue); }

    @Test
    public void newRQIsHasSizeZero() { assertTrue(this.rq.size() == 0); }

    @Test
    public void newRQIsEmpty() { assertTrue(this.rq.isEmpty()); }

    @Test
    public void canAddOneElementToNewRQ() {
        this.rq.enqueue(1);
        assertTrue(this.rq.size() == 1);
    }

    @Test(expected = NullPointerException.class)
    public void addingNullThrowsException() { this.rq.enqueue(null); }

    @Test(expected = UnsupportedOperationException.class)
    public void samplingEmptyThrowsException() { this.rq.sample(); }

    @Test(expected = UnsupportedOperationException.class)
    public void dequeingEmptyThrowsException() { this.rq.dequeue(); }
}
