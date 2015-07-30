import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] randomQueue;
    private int N;
    private int capacity;

    // construct an empty randomized queue
    
    public RandomizedQueue() {
        N = 0;
        capacity = 1;
        randomQueue = (Item[]) new Object[capacity];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == capacity) {
            expandQueue();
        }
        randomQueue[N++] = item;
    }

    private void expandQueue() {
        capacity *= 2;
        Item[] newRandomQueue = (Item[]) new Object[capacity];
        int index = 0;
        for (Item item : randomQueue) {
            newRandomQueue[index++] = item;
        }
        randomQueue = newRandomQueue;
    }

    private void shrinkQueue() {
        capacity /= 2;
        Item[] newRandomQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            newRandomQueue[i] = randomQueue[i];
        }
        randomQueue = newRandomQueue;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = (int) (Math.random() * N);
        Item item = randomQueue[index];
        randomQueue[index] = randomQueue[--N];
        randomQueue[N] = null;
        if (capacity / 4 > N) {
            shrinkQueue();
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = (int) (Math.random() * N);
        Item item = randomQueue[index];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int[] shuffledIndex = new int[N];
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < N;
            }

            private void shuffle(int[] aInTmpRandomQueue) {
                int len = aInTmpRandomQueue.length;
                for (int i = len - 1; i > 0; i--) {
                    int j = (int) (Math.random() * (i + 1));
                    swap(aInTmpRandomQueue, i, j);
                }
            }

            private void swap(int[] aInTmpRandomQueue, int aInI, int aInJ) {
                int tmp = aInTmpRandomQueue[aInI];
                aInTmpRandomQueue[aInI] = aInTmpRandomQueue[aInJ];
                aInTmpRandomQueue[aInJ] = tmp;
            }

            @Override
            public Item next() {
                if (current >= N || size() == 0) {
                    throw new NoSuchElementException();
                }
                if (current == 0) {
                    for (int i = 0; i < N; i++) {
                        shuffledIndex[i] = i;
                    }
                    shuffle(shuffledIndex);
                }

                return randomQueue[shuffledIndex[current++]];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing
    public static void main(String[] args) {
    }
}