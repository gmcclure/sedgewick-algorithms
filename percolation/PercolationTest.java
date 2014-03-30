import org.junit.*;
import static org.junit.Assert.*;

/*
 * TEST PREP
 *
 * To test the Percolation class, comment out private access
 * (change to protected) for the following fields and methods
 * in Percolation.java:
 *
 * f: QuickFindUF uf;
 * f: int gridSize;
 * m: int rcToUFIdx(int r, int c);
 *
 */

public class PercolationTest {
    
    private Percolation p;
    
    @Before
    public void setUp() {
        this.p = new Percolation(10);
    }

    @Test
    public void isPercolation() {
        assertTrue(this.p instanceof Percolation);
    }

    @Test
    public void gridSizeIsNPlusTwo() {
		assertTrue(this.p.gridSize == 12);
    }

    @Test
    public void gridTopRowIsConnected() {
		assertTrue (this.p.uf.connected(this.p.rcToUFIdx(0,0), this.p.rcToUFIdx(0,7)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBounds1() {
        this.p.isOpen(11, 6);
    }

    @Test
    public void openSitesAreConnected() {
        this.p.open(1,2);
        this.p.open(2,2);
        assertTrue(this.p.uf.connected(this.p.rcToUFIdx(1,2), this.p.rcToUFIdx(2,2)));
    }

    @Test
    public void openedSiteIsOpen() {
		this.p.open(3,3);
		assertTrue(this.p.isOpen(3, 3));
    }

    @Test
    public void openedSiteIsFull() {
		this.p.open(1,1);
		assertTrue(this.p.isFull(1, 1));
    }

    @Test
    public void isolatedSiteIsNotFull() {
		this.p.open(3,3);
		assertFalse(this.p.isFull(3, 3));
    }

    @Test
    public void insufficientlyOpenedGridDoesNotPercolate() {
		this.p.open(1,1);
		this.p.open(2,2);
		this.p.open(3,3);
		this.p.open(4,3);
		this.p.open(5,3);
		assertFalse(this.p.percolates());
    }

    @Test
    public void sufficientlyOpenedGridPercolates() {
		this.p.open(1,3);
		this.p.open(2,3);
		this.p.open(3,3);
		this.p.open(4,3);
		this.p.open(5,3);
		this.p.open(6,3);
		this.p.open(7,3);
		this.p.open(8,3);
		this.p.open(9,3);
		this.p.open(10,3);
		assertTrue(this.p.percolates());
    }
}
