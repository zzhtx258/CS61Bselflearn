package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void TestThreeAddThreeRemove(){
        AListNoResizing<Integer> l1 = new AListNoResizing();
        BuggyAList<Integer> l2 = new BuggyAList();
        l1.addLast(1);
        l2.addLast(1);
        assertEquals(l1.getLast(),l2.getLast());
        l1.addLast(2);
        l2.addLast(2);
        assertEquals(l1.getLast(),l2.getLast());
        l1.addLast(3);
        l2.addLast(3);
        assertEquals(l1.getLast(),l2.getLast());
        assertEquals(l1.removeLast(),l2.removeLast());
        assertEquals(l1.removeLast(),l2.removeLast());
        assertEquals(l1.removeLast(),l2.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
                //System.out.println("size: " + size);
                assertEquals(size,size2);
            } else if (operationNumber ==2 && L.size()>0 && L2.size()>0){
                //System.out.println("getLast()="+L.getLast());
                assertEquals(L.getLast(),L2.getLast());
            } else if (operationNumber ==3 && L.size()>0 && L2.size()>0){
                //System.out.println("removeLast()="+L.getLast());
                assertEquals(L.removeLast(),L2.removeLast());
            }
        }
    }
}
