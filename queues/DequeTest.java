import org.junit.*;
import static org.junit.Assert.*;


public class DequeTest {
    private Deque<Integer> d;

    @Before
    public void setUp() {
        d = new Deque<Integer>();
    }

    @Test
    public void isADeque() {
        assertTrue(this.d instanceof Deque);
    }

    @Test
    public void newDequeIsEmpty() {
        assertTrue(this.d.isEmpty());
        assertTrue(this.d.size() == 0);
    }

    @Test
    public void addsNewItemToFront() {
        d.addFirst(99);
        assertTrue(d.size() == 1);
    }

    @Test
    public void addsNewItemToEnd() {
        d.addLast(11);
        assertTrue(d.size() == 1);
    }

    @Test
    public void firstAndLastBothAddItemsToSameDeque() {
        d.addFirst(99);
        d.addLast(11);
        assertTrue(d.size() == 2);

    }

    @Test
    public void removeFirstReturnsFirstItem() {
        d.addFirst(99);
        assertTrue(d.size() == 1);
        int k = d.removeFirst();
        assertTrue(k == 99);
        assertTrue(d.isEmpty());
    }

    @Test
    public void removeLastReturnsLastItem() {
        d.addLast(11);
        assertTrue(d.size() == 1);
        int k = d.removeLast();
        assertTrue(k == 11);
        assertTrue(d.isEmpty());
    }

    @Test
    public void mixedAddsEndUpWithRightSize() {
        d.addFirst(0);
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        d.addFirst(4);
        d.addLast(5);
        d.addLast(6);
        d.addLast(7);
        d.addLast(8);
        d.addLast(9);
        assertTrue(d.size() == 10);
    }

    @Test(expected = NullPointerException.class)
    public void throwsNullPointerExceptionOnNullAddFirst() {
        d.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void throwsNullPointerExceptionOnNullAddLast() {
        d.addLast(null);
    }
}
