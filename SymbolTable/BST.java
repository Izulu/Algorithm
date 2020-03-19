
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

    public Iterable<Key> keys(){
        return new Deque<Key>();
    }
//    @Override
//    public void delete(Object o) {
//
//    }
//

//
//    @Override
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
//    public Object min() {
//        return null;
//    }
//
//    @Override
//    public Object max() {
//        return null;
//    }
//
//    @Override
//    public Object floor(Object o) {
//        return null;
//    }
//
//    @Override
//    public Object ceiling(Object o) {
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
