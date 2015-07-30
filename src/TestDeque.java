
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestDeque
{
    @Test
    public void TestDequeConstruction()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        Assert.assertTrue(lDeque.isEmpty());
    }

    @Test
    public void TestIsEmpty()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addFirst(1);
        lDeque.removeLast();
        Assert.assertTrue(lDeque.isEmpty());
    }

    @Test
    public void TestSize()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addFirst(2);
        lDeque.addLast(3);
        Assert.assertEquals(2, lDeque.size());
    }

    @Test
    public void TestAddFirst()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addFirst(1);
        Assert.assertTrue(lDeque.removeLast().equals(1));
        lDeque.addFirst(2);
        lDeque.addFirst(3);
        Assert.assertTrue(lDeque.removeFirst().equals(3));
    }

    @Test
    public void TestAddLast()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addLast(1);
        Assert.assertTrue(lDeque.removeFirst().equals(1));
        lDeque.addLast(2);
        lDeque.addLast(3);
        Assert.assertTrue(lDeque.removeLast().equals(3));
    }

    @Test
    public void TestRemoveFirst()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addLast(1);
        lDeque.addLast(2);
        lDeque.addLast(3);
        lDeque.addLast(4);
        lDeque.removeFirst();
        Assert.assertTrue(lDeque.removeFirst().equals(2));
    }

    @Test
    public void TestCombined()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addLast(1);
        lDeque.addFirst(2);
        Assert.assertTrue(lDeque.removeLast().equals(1));
    }
    
    @Test
    public void TestRemoveLast()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addLast(1);
        lDeque.addLast(2);
        lDeque.addLast(3);
        lDeque.addLast(4);
        lDeque.removeLast();
        Assert.assertTrue(lDeque.removeLast().equals(3));
    }

    @Test
    public void TestIterator()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addFirst(1);
        lDeque.addFirst(2);
        lDeque.addFirst(3);
        lDeque.addFirst(4);
        int[] expected = new int[]{4, 3, 2, 1};
        int[] actual = new int[4];
        Iterator<Integer> lIterator = lDeque.iterator();
        int index = 0;
        while (lIterator.hasNext())
        {
            actual[index++] = lIterator.next();
        }
        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void TestIteratorRemove()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addFirst(1);
        Iterator<Integer> lIterator = lDeque.iterator();
        lIterator.next();
        lIterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void TestRemoveFirstFromEmptyDeque()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void TestRemoveLastFromEmptyDeque()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.removeLast();
    }

    @Test(expected = NullPointerException.class)
    public void TestAddFirstNull()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void TestAddLastNull()
    {
        Deque<Integer> lDeque = new Deque<Integer>();
        lDeque.addLast(null);
    }
}