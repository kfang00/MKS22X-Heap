public class MyHeap {
//We discussed these 2 methods already:
  private static void pushDown(int[]data,int size,int index) {
    int hold = 0;
    if ((((index * 2) + 1) <= size) && (((index * 2) + 2) <= size)) {
      if ((data[(index * 2) + 1] > data[index]) || ((data[(index * 2) + 2] > data[index]) ) {
        if (data[(index * 2) + 1] > data[(index * 2) + 2]) {
          hold = data[index];
          data[index] = data[(index * 2) + 1];
          data[(index * 2) + 1] = hold;
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
    else if (((data[index] * 2) + 1) <= size) {
      if (((data[index] * 2) + 1) > data[index]) {
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
     - size  is the number of elements in the data array.  
     - push the element at index i downward into the correct position. This will swap with the larger of the child nodes provided thatchild is larger. This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
     - precondition: index is between 0 and size-1 inclusive
     - precondition: size is between 0 and data.length-1 inclusive.

  private static void pushUp(int[]data,int index)
     - push the element at index i up into the correct position. This will swap it with the parent node until the parent node is larger or the root is reached. [ should be O(logn) ]
     - precondition: index is between 0 and data.length-1 inclusive.


//We will discuss this today:
  public static void heapify(int[])
    - convert the array into a valid heap. [ should be O(n) ]

  public static void heapsort(int[])
    - sort the array [ should be O(nlogn) ] :
     converting it into a heap 
     removing the largest value n-1 times (remove places at end of the sub-array). 
}
