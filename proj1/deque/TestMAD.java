package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class TestMAD {
    Comparator<Integer> intc = (i1,i2) -> i1-i2;
    Comparator<Double> dblc = (d1,d2) -> (int) (d1-d2);
    Comparator<String> strc = (s1,s2) -> {
        if (s1.length()>s2.length()) return 1;
        else if (s1.length()==s2.length()) return 0;
        else return -1;
    };
    @Test
    public void addIsEmptySizeTest() {


        MaxArrayDeque<String> lld1 = new MaxArrayDeque<String>(strc);

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        assertEquals(null, lld1.max());
        lld1.addFirst("front");
        assertEquals("front", lld1.max());
        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());
        assertEquals("middle", lld1.max());
        System.out.println("Printing out deque: ");
        lld1.printDeque();

        assertEquals("middle", lld1.get(1));
        assertEquals("middle", lld1.get(1));
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {


        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(intc);
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {


        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<>(intc);

        lld1.addFirst(0);
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.isEmpty();
        lld1.isEmpty();
        lld1.addFirst(8);
        int last= lld1.removeLast();
        assertEquals(8,last);

        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";
        //System.out.println(errorMsg);
        assertEquals(0,size);
    }

    @Test
    /* Check if you can create MaxArrayDeques with different parameterized types*/
    public void multipleParamTest() {


        MaxArrayDeque<String>  lld1 = new MaxArrayDeque<String>(strc);
        MaxArrayDeque<Double>  lld2 = new MaxArrayDeque<Double>(dblc);


        lld1.addFirst("string");
        lld2.addFirst(3.14159);


        String s = lld1.removeFirst();
        double d = lld2.removeFirst();

        assertEquals("string", s);
        assertEquals(3.14159, d, 0);

    }

    @Test
    /* check if null is return when removing from an empty MaxArrayDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(intc);

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {



        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(intc);
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    }
}
