import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    @SuppressWarnings("unused")
    private class Node {
        private Item item;

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        private Node prev;
        private Node next;

        public Item getItem() {
            return item;
        }
    }

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node lNode = new Node();
        lNode.item = item;

        if (head != null) {
            head.prev = lNode;
            lNode.prev = null;
            lNode.next = head;
            head = lNode;
        } else {
            head = lNode;
            tail = lNode;
            lNode.prev = null;
            lNode.next = null;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node lNode = new Node();
        lNode.item = item;
        if (tail != null) {
            tail.next = lNode;
            lNode.prev = tail;
            lNode.next = null;
            tail = lNode;
        } else {
            head = lNode;
            tail = lNode;
            lNode.next = null;
            lNode.prev = null;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = head;
        if (size() == 1) {
            head = null;
            tail = null;
        } else {
            head.next.prev = null;
            head = head.next;
        }
        size--;
        node.next = null;
        return node.item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = tail;
        if (size() == 1) {
            head = null;
            tail = null;
        } else {
            tail.prev.next = null;
            tail = tail.prev;
        }
        size--;
        node.prev = null;
        return node.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = head;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node node = current;
                current = current.next;
                return node.item;
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
