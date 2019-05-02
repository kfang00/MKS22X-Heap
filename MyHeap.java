import java.util.*;
public class MyHeap {
//We discussed these 2 methods already:
/*- size  is the number of elements in the data array.
- push the element at index i downward into the correct position. This will swap with the larger of the child nodes provided thatchild is larger. This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
- precondition: index is between 0 and size-1 inclusive
- precondition: size is between 0 and data.length-1 inclusive. */
  private static void pushDown(int[]data,int size,int index) {
    int hold = 0;
    if ((((index * 2) + 1) < size) && (((index * 2) + 2) < size)) {
      if ((data[(index * 2) + 1] > data[index]) || ((data[(index * 2) + 2] > data[index]) )) {
        if (data[(index * 2) + 1] > data[(index * 2) + 2]) {
          hold = data[index];
          data[index] = data[(index * 2) + 1];
          data[(index * 2) + 1] = hold;
          //System.out.println(toString(data));
          pushDown(data,size,((index * 2) + 1));
        }
        else {
          hold = data[index];
          data[index] = data[(index * 2) + 2];
          data[(index * 2) + 2] = hold;
          pushDown(data,size,((index * 2) + 2));
        }
      }
    }
    else if (((index * 2) + 1) < size) {
      if (((data[index] * 2) + 1) < data[index]) {
          hold = data[index];
          data[index] = data[(index * 2) + 1];
          data[(index * 2) + 1] = hold;
          pushDown(data,size,((index * 2) + 1));
      }
    }
    else {
      return;
    }
  }

  private static void pushUp(int[]data,int index) {
    /*- push the element at index i up into the correct position. This will swap it with the parent node until the parent node is larger or the root is reached. [ should be O(logn) ]
    - precondition: index is between 0 and data.length-1 inclusive.*/
    int hold = 0;
    if (index > 0) {
      if (data[(index - 1)/2] < data[index]) {
        hold = data[index];
        data[index] = data[(index - 1)/2];
        data[(index - 1)/2] = hold;
        pushUp(data,((index - 1)/2));
      }
    }
    else {
      return;
    }
  }






//We will discuss this today:
  public static void heapify(int[] data) {
      //- convert the array into a valid heap. [ should be O(n)
      for (int a = (data.length - 1); a >= 0; a--) {
        pushDown(data, data.length, a);
        //HeapPrinter.print(data);
      }
      //HeapPrinter.print(data);
  }


  public static void heapsort(int[] data) {
    /*- sort the array [ should be O(nlogn) ] :
     converting it into a heap
     removing the largest value n-1 times (remove places at end of the sub-array).*/
     int bound = data.length - 1;
     int hold = 0;
     heapify(data);
     //HeapPrinter.print(data);
     for (int a = 0; a < data.length; a++) {
       hold = data[0];
       data[0] = data[bound];
       data[bound] = hold;
       pushDown(data, bound, 0);
       //HeapPrinter.print(data);
       bound--;
     }
     if (data[0] > data[1]) {
       hold = data[0];
       data[0] = data[1];
       data[1] = hold;
     }
     //System.out.println(toString(data));
  }

  public static String toString(int[] data) {
    String h = "[";
    for (int a = 0; a < data.length; a++) {
      h += data[a] + ", ";
    }
    return h + "]";
  }

  public static void main(String[] args){
    int[] a = {0, 6, 1300, 22,53,34,55,661,38,999999,997849,9985,61,71,61,51};
    heapsort(a);
    System.out.println(toString(a));
    HeapPrinter.print(a);
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        for(int trial = 0 ; trial <=10; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          heapsort(data2);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
  
}



