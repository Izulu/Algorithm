public class MaxPQ<Key extends Comparable<Key> >
{
  private Key[] pq;
  private int N;
  
  public MaxPQ(int N) {
    pq = (Key[]) new Comparable[N+1]; //fixed capacity for simplicity. Can use resizing array.
  }
  
  public boolean isEmpty() {
    return N == 0;
  } 
  
  public void insert(Key key){
    pq[++N] = key;
    swim(N);
  }
    
  public Key delMax(){
    Key max = pq[1];
    exch(1,N--);
    sink(1);
    pq[N+1]=null; // prevent loitering.
    return max;
  }
  
  public void swim(int k){
    while(k>1 && less(k/2,k)){
      exch(k/2,k);  // exch with parent node until heap order restored.
      k = k/2;
    }
  }
  
  public void sink(int k){
    while( 2*k <= N) {
      int j = 2*k;
      if(j < N && less(j,j+1) ){
        j++;
      }
      if(!less(k,j)) break;
      exch(k,j);  // exch with son node until heap order retored.
      k = j;
    }
  }
  
  private boolean less(int i,int j){
  return pq[i].compareTo(pq[j]) < 0;
  }
  private void exch(int i,int j){
  Key t = pq[i];pq[i]=pq[j];pq[j]=t;
  }
}
