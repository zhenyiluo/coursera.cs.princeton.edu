import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;


public class TestRandomizedQueue {
    @Test
    public void TestRandomizedQueueConstruction()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        Assert.assertTrue(lRandomizedQueue.isEmpty());
    }

    @Test
    public void TestIsEmpty()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        lRandomizedQueue.enqueue(1);
        lRandomizedQueue.dequeue();
        Assert.assertTrue(lRandomizedQueue.isEmpty());
    }

    @Test
    public void TestSize()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        lRandomizedQueue.enqueue(1);;
        lRandomizedQueue.enqueue(3);
        Assert.assertEquals(2, lRandomizedQueue.size());
    }

    @Test
    public void TestEnqueueAndDeque()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        ArrayList<Integer> expectedList = new ArrayList<Integer>();
        HashSet<Integer> expectedSet = new HashSet<Integer>();
        for(int i = 0; i < 20; i++){
            expectedList.add(i);
            expectedSet.add(i);
            lRandomizedQueue.enqueue(i);
        }
        ArrayList<Integer> actualList = new ArrayList<Integer>();
        HashSet<Integer> actualSet = new HashSet<Integer>();
        while(!lRandomizedQueue.isEmpty()){
            int n = lRandomizedQueue.dequeue();
            actualList.add(n);
            actualSet.add(n);
        }
        Assert.assertEquals(expectedSet, actualSet);
        Assert.assertNotEquals(expectedList, actualList);
    }

    @Test
    public void TestRemoveFirst()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i = 0; i < 100; i++){
            lRandomizedQueue.enqueue(i);
            hs.add(i);
        }
        int a = lRandomizedQueue.sample();
        int b = lRandomizedQueue.sample();
        Assert.assertTrue(hs.contains(a));
        Assert.assertTrue(hs.contains(b));
        Assert.assertNotEquals(a, b);
        Assert.assertEquals(100, lRandomizedQueue.size());
    }

    @Test
    public void TestIterator()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        ArrayList<Integer> expectedList = new ArrayList<Integer>();
        HashSet<Integer> expectedSet = new HashSet<Integer>();
        for(int i = 0; i < 20; i++){
            expectedList.add(i);
            expectedSet.add(i);
            lRandomizedQueue.enqueue(i);
        }
        Iterator<Integer> iterator1 = lRandomizedQueue.iterator();
        Iterator<Integer> iterator2 = lRandomizedQueue.iterator();
        ArrayList<Integer> actualList1 = new ArrayList<Integer>();
        HashSet<Integer> actualSet1 = new HashSet<Integer>();
        ArrayList<Integer> actualList2 = new ArrayList<Integer>();
        HashSet<Integer> actualSet2 = new HashSet<Integer>();
        
        while(iterator1.hasNext()){
            int n = iterator1.next();
            actualList1.add(n);
            actualSet1.add(n);
        }
        
        while(iterator2.hasNext()){
            int n = iterator2.next();
            actualList2.add(n);
            actualSet2.add(n);
        }
        
        Assert.assertEquals(expectedSet, actualSet1);
        Assert.assertEquals(expectedSet, actualSet2);
        Assert.assertNotEquals(expectedList, actualList1);
        Assert.assertNotEquals(expectedList, actualList2);
        Assert.assertNotEquals(actualList1, actualList2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void TestIteratorRemove()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        lRandomizedQueue.enqueue(1);
        Iterator<Integer> lIterator = lRandomizedQueue.iterator();
        lIterator.next();
        lIterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void TestDequeueEmptyRandomizedQueue()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        lRandomizedQueue.dequeue();
    }

    @Test(expected = NullPointerException.class)
    public void TestEnqueueNull()
    {
        RandomizedQueue<Integer> lRandomizedQueue = new RandomizedQueue<Integer>();
        lRandomizedQueue.enqueue(null);
    }

}
