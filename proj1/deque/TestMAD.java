package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class TestMAD {
    Comparator<Integer> intc = (i1,i2) -> i1-i2;
    Comparator<Double> dblc = (d1,d2) -> (int) (d1-d2);
    Comparator<String> strc = (s1,s2) -> {
        if (s1.length()>s2.length()) return 1;
        else if (s1.length()==s2.length()) return 0;
        else return -1;
    };
    Comparator<String> strc1 = (s1,s2) -> {
        if (s1.length()>s2.length()) return -1;
        else if (s1.length()==s2.length()) return 0;
        else return 1;
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
        assertEquals("back", lld1.max(strc1));
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

        lld1.addFirst(15);
        // should be empty
        //assertTrue("lld1 should be empty after removal", lld1.isEmpty());
        Iterator<Integer> it = lld1.iterator();
        while (it.hasNext()){
            int item=it.next();
        }
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {


        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<>(intc);
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();

        int N=50;
        for (int i=0; i<N; i++){
            int op=StdRandom.uniform(0,6);
            int num=StdRandom.uniform(-10,10);
            switch (op){
                case 0,4:
                    lld1.addFirst(num);
                    lld2.addFirst(num);
                    break;
                case 1,5:
                    lld1.addLast(num);
                    lld2.addLast(num);
                    break;
                case 2:
                    lld1.removeFirst();
                    lld2.removeFirst();
                    break;
                case 3:
                    lld1.removeLast();
                    lld2.removeLast();
                    break;
            }
            assertTrue(lld1.equals(lld2));
        }
        int size= lld2.size();
        Iterator<Integer> it = lld2.iterator();
        for (int i=0; i<size; i++){
            assertTrue(it.hasNext());
            it.next();
        }
        assertFalse(it.hasNext());
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
