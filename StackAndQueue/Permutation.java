import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<Object> rq = new RandomizedQueue<Object>();
            while(!StdIn.isEmpty()){
                rq.enqueue(StdIn.readString());
            }
        for(int i=0;i<k;i++){
            StdOut.print(rq.dequeue()+"\n");
        }
    }
}
