import java.util.NoSuchElementException;
import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node first;
	private Node last;
	
	private class Node {
		Item item;
		Node next;
		Node prev;
	}
    // construct an empty deque
    public Deque() {
		size = 0;
		first = null;
		last = null;
	}

    // is the deque empty?
    public boolean isEmpty(){
		return size == 0;
	}

    // return the number of items on the deque
    public int size(){
		return size;
	}

    // add the item to the front
    public void addFirst(Item item){
		if(item == null){throw new IllegalArgumentException("Item can not be null."); }
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
		if(last==null){last=first;}
		else{oldfirst.prev = first;}
	}

    // add the item to the back
    public void addLast(Item item){
		if(size==0){this.addFirst(item);}
		else {
			if (item == null) {
				throw new IllegalArgumentException("Item can not be null.");
			}
			Node oldlast = last;
			last = new Node();
			last.item = item;
			oldlast.next = last;
			last.prev = oldlast;
			last.next = null;
			size++;
		}
	}

    // remove and return the item from the front
    public Item removeFirst(){
		if(isEmpty()){
			throw new NoSuchElementException("The Deque is Empty!");
		}
		Item item = first.item;
		if (size==1){
			first=null;
			last=null;
			size--;
		}
		else {
			first = first.next;
			first.prev =null;
			size--;
		}
		return item;
	}

    // remove and return the item from the back
    public Item removeLast(){
		if(isEmpty()){
			throw new NoSuchElementException("The Deque is Empty!");
		}
		Item item = last.item;
		if (size==1){
			first=null;
			last=null;
			size--;
		}
		else{
			last = last.prev;
		    last.next = null;
		    size--;
		}
		return item;
	}

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext(){
			return current.next != null;
		}

		public Item next(){
			if (current == null){
				throw new NoSuchElementException("Iteration end.");
			}
			Item current_item = current.item;
			current = current.next;
			return current_item;
		}

		public void remove(){
			throw new UnsupportedOperationException("Method remove is not surpport.");
		}
	}

    // unit testing (required)
    public static void main(String[] args){
	// unit testing completed otherwhere.

	}

}
