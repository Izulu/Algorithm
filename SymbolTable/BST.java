import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        private int H;

        public Node (Key key, Value val, int N, int H) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.H = H;
        }
    }
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if (x==null) return 0;
        else return x.N;
    }

    public int height(){
        return height(root);
    }
    private int height(Node x){
        if(x==null) return 0;
        else return x.H;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }
    private Node put(Node x, Key key, Value value){
        if(x==null) {
            return new Node(key, value, 1, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp>0) {x.right = put(x.right, key, value);}
        else if (cmp<0) {x.left = put(x.left, key, value);}
        else x.val = value;
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(height(x.left),height(x.right))+1;
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

        return get(key)!= null;
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
        else if(cmp>0) return rank(key, x.right)+size(x.left)+1;
        else return size(x.left);
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
        LinkedList<Key> queue= new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, LinkedList<Key> deque, Key lo, Key hi)
    {
        if (x==null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo<0) keys(x.left, deque, lo, hi);
        if(cmplo <=0 && cmphi >=0) deque.addLast(x.key);
        if(cmphi>0) keys(x.right, deque, lo, hi);
    }
//Certification:
    private boolean isSizeConsistent(){
        return isSizeConsistent(root);
    }
    private boolean isSizeConsistent(Node x){
        if(x==null) return true;
        if(size(x.left) + size(x.right) + 1 != x.N) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);

    }

    private boolean hasNoDuplicates(Node x){
        if(x==null) return true;
        if (get(x.left, x.key) != null) return false;
        if (get(x.right, x.key) != null) return false;
        return hasNoDuplicates(x.left) && hasNoDuplicates(x.right);
    }

    private boolean isOrdered(){
        return isOrdered(root, null, null);
    }
    private boolean isOrdered(Node x, Key min, Key max){
        if(x == null) return true;
        if (min!=null && x.key.compareTo(min)<=0) return false;
        if (max!=null && x.key.compareTo(max)>=0) return false;
        return isOrdered(x.left, min, x.key) && isOrdered(x.right, x.key, max);
    }

    private boolean isBST(){
        if (!isSizeConsistent()) return false;
        if(!hasNoDuplicates(root)) return false;
        return isOrdered();
    }

    private boolean isRankConsistent(){
        for(int i=0;i<size();i++){
            if(i != rank(select(i) )) return false;
        }
        for(Key key:keys()){
            if( key != select(rank(key))) return false;
        }
        return true;
    }

//    public int height()
//    {
//        return height(root);
//    }
//    private int height(Node x)
//    {
//        int lh;
//        int rh;
//        if( x.left == null && x.right ==null) return 1;
//        if(x.left==null) lh = 0;
//        else lh=height(x.left)+1;
//        if(x.right==null) rh = 0;
//        else rh=height(x.right)+1;
//        return Math.max(lh, rh);
//    }


    public static void main(String[] args)
    {
//        BST<String, Integer> bst = new BST<>();
//        for(int i=0; !StdIn.isEmpty(); i++)
//        {
//            String key = StdIn.readString();
//            bst.put(key, i);
//        }
//
//        for(String s: bst.keys())
//        {
//            StdOut.println(s + " " + bst.get(s));
//        }
        BST<String, Integer> bst = new BST<>();
        String test = "EASYQUESTION";
        char[] cl = test.toCharArray();
        for(int i=0; i<cl.length;i++)
        {
            String s = Character.toString( cl[i] );
            bst.put(s, i);
        }

        System.out.println("keys: ");
        for(String s :bst.keys())
        {
            StdOut.println(s);
        }
        System.out.println("Height: ");
        System.out.println(bst.height());

        bst.delete("S");
        System.out.println("After delete: ");
        for(String s :bst.keys())
        {
            StdOut.println(s);
        }
        System.out.println("Height: ");
        System.out.println(bst.height());

        System.out.println(bst.isBST());
    }

}
