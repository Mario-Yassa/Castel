package BST;

import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.*;

public class BSTTest {

    @Test
    public void testInsert() {
        BST<Integer,Integer> bst = new BST<Integer,Integer>();
        bst.insert(20,20);
        bst.insert(10,10);
        bst.insert(5,5);
        bst.insert(15,15);
        bst.insert(40,40);
        bst.insert(30,30);
        bst.insert(100,100);
        bst.insert(12,12);
        // Test that we have all the elements in the right order.
        assertEquals("5 10 12 15 20 30 40 100",bst.toString());

        assertNotEquals("15 5 10 12 20 30 100",bst.toString());
    }

    @Test
    public void testFind(){
        BST<Integer,Integer> bst = new BST<Integer,Integer>();
        bst.insert(20,20);
        bst.insert(10,10);
        bst.insert(5,5);
        bst.insert(15,15);
        bst.insert(40,40);
        bst.insert(30,30);
        bst.insert(100,100);
        bst.insert(12,12);
        // Test find element in the bst.
        assertEquals(Optional.ofNullable(bst.find(20)), Optional.of(20));
        // Testing find element that is not the bst.
        assertNull(bst.find(22));

    }

    @Test
    public void testRemove(){
        BST<Integer,Integer> bst = new BST<Integer,Integer>();

        // Test a null bst.
        assertNull(bst.remove(22));
        // Insert items.
        bst.insert(20,20);
        bst.insert(10,10);
        bst.insert(5,5);
        bst.insert(15,15);
        bst.insert(40,40);
        bst.insert(30,30);
        bst.insert(100,100);
        bst.insert(12,12);
        // Test removing one item.
        bst.remove(20);
        assertEquals("5 10 12 15 30 40 100",bst.toString());
        // Testing removing more than one.
        bst.remove(5);
        bst.remove(15);
        assertEquals("10 12 30 40 100",bst.toString());
        // Testing removing item that is not in the node.
        bst.remove(15);
        assertEquals("10 12 30 40 100",bst.toString());

    }

}

// How we go to the other sides in the iterator.

