import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node (Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if (x==null) return 0;
        else return x.N;
    }


    public void put(Key key, Value value) {
        root = put(root, key, value);
    }
    private Node put(Node x, Key key, Value value){
        if(x==null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp>0) {x.right = put(x.right, key, value);}
        else if (cmp<0) {x.left = put(x.left, key, value);}
        else x.val = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node x, Key key){
        if (x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp>0) { return get(x.right, key);}
        else if (cmp<0) { return get(x.left, key);}
        else return x.val;
    }

    public boolean contains(Key key){

        return false;
    }

    public Key min()
    {
        return min(root).key;
    }
    private Node min(Node x)
    {
        if(x.left == null) return x;
        return min(x.left);
    }

    public Key max()
    {
        return max(root).key;
    }
    private Node max(Node x)
    {
        if(x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key)
    {
        Node x = floor(root, key);
        if (x==null) return null;
        return x.key;
    }
    private Node floor(Node x,Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t!=null) return t;
        else return x;
    }

    public Key ceiling(Key key)
    {
        Node x = ceiling(root, key);
        if (x==null) return null;
        return x.key;
    }
    private Node ceiling(Node x,Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t!=null) return t;
        else return x;
    }

    public Key select(int k)
    {
        return select(root, k).key;
    }
    private Node select(Node x, int k)
    {
        if(x==null) return null;
        int t = size(x.left);
        if(t>k) return select(x.left, k);
        else if (t<k) return select(x.right, k-t-1);
        else return x;
    }

    public int rank(Key key)
    {
        return rank(key, root);
    }
    private int rank(Key key, Node x)
    {
        if(x==null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp<0) return rank(key, x.left);
        else if(cmp>0) return rank(key, x.right)+x.left.N+1;
        else return x.left.N;
    }

    public void deleteMin()
    {
        root = deleteMin(root);
    }
    private Node deleteMin(Node x)
    {
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }

    public void delete(Key key)
    {
        root = delete(root,key);
    }
    private Node delete(Node x, Key key)
    {
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp>0) x.right = delete(x.right, key);
        else if(cmp<0) x.left = delete(x.left, key);
        else
        {
            if(x.left==null) return x.right;
            if(x.right==null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right); // The right link to the updated right tree after deleteMin()
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) +1;
        return x;
    }

    public Iterable<Key> keys()
    {
        return keys(min(), max());
    }
    private Iterable<Key> keys(Key lo, Key hi)
    {
        Deque<Key> deque= new Deque<>();
        keys(root, deque, lo, hi);
        return deque;
    }

    private void keys(Node x, Deque<Key> deque, Key lo, Key hi)
    {
        if (x==null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo<0) keys(x.left, deque, lo, hi);
        if(cmphi>0) keys(x.right, deque, lo, hi);
        if(cmplo <=0 && cmphi >=0) deque.addLast(x.key);
    }
//    public boolean isEmpty() {
//        return false;
//    }
//
//
//
//    @Override
//    public Iterable keys() {
//        return null;
//    }
//
//    @Override
//    public int rank(Object o) {
//        return 0;
//    }
//
//    @Override
//    public Object select(int k) {
//        return null;
//    }

}
