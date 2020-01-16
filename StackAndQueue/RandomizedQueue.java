import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue(){
        N = 0;
    }

    private class Node{
        Item item;
        Node next;
        Node prev;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    // add the item
    public void enqueue(Item item){
        if(item==null){
            throw new IllegalArgumentException("Item can not be null.");
        }
        if(isEmpty()){
            first = new Node();
            first.item =item;
            last=first;
            last.next = null;
        }
        else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.prev = oldlast;
            oldlast.next = last;
            last.next = null;
        }
        N++;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException("The queue is empty.");
        }
        int k = StdRandom.uniform(0,N);
        Node pick = sample(k);
        Node before;
        Node after;
        if(k!=0 && k!=N-1){
            before =pick.prev;
            after = pick.next;
            before.next = after;
            after.prev = before;
        }
        else {
            if (k == 0) {
                first = pick.next;
            }
            if (k == N - 1) {
                last = pick.prev;
            }
        }
        N--;
        return pick.item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){
            throw new NoSuchElementException("The queue is empty.");
        }
        int k = StdRandom.uniform(0,N);
        Node pick;
        pick = sample(k);
        return pick.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomOrderIterator();
    }

    private class RandomOrderIterator implements Iterator<Item> {
        private final int[] random_order = StdRandom.permutation(N);
        private int i=0;

        public boolean hasNext(){
            return i<N;
        }

        public Item next(){
            if (i==N){
                throw new NoSuchElementException("No next item to iterate.");
            }
            Item item = sample(random_order[i]).item;
            i++;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException("Remove Method cannot be invoke.");
        }
    }

    private Node sample(int n){
        Node current_node = first;
        for(int i=0;i<n;i++){
            current_node = current_node.next;
        }
        return current_node;
    }
    // unit testing (required)
    public static void main(String[] args) {
        // unit testing completed otherwhere.
    }
}
