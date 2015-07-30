public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        int n = Integer.parseInt(args[0]);
        while (n > 0) {
            StdOut.println(rq.dequeue());
            n--;
        }
    }
}
