package mxk170014_sp5;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class BinaryHeap<T extends Comparable<? super T>> {
    Comparable[] pq;
    int size;

    // Constructor for building an empty priority queue using natural ordering of T
    public BinaryHeap(int maxCapacity) {
	pq = new Comparable[maxCapacity];
	size = 0;
    }

    // add method: resize pq if needed
    public boolean add(T x) {
    	if(size + 1 > pq.length)
    	{
    		pq = resize();
    	}
    	pq[size] = x;
    	percolateUp(0);
    	size++;
    	return true;
    }

    public boolean offer(T x) {
	return add(x);
    }

    // throw exception if pq is empty
    public T remove() throws NoSuchElementException {
	T result = poll();
	if(result == null) {
	    throw new NoSuchElementException("Priority queue is empty");
	} else {
	    return result;
	}
    }

    // return null if pq is empty
    public T poll() {
    	if(size == 0)
    	{
    		return null;
    	}
    	T minimum = (T) pq[0];
    	pq[0] = pq[size - 1];
    	size--;
    	percolateDown(0);
    	return minimum;
    }
    
    public T min() { 
	return peek();
    }

    // return null if pq is empty
    public T peek() {
	if(size == 0)
	{
    	return null;
    }
	return (T) pq[0];
    }

    int parent(int i) {
	return (i-1)/2;
    }

    int leftChild(int i) {
	return 2*i + 1;
    }
    
    /** pq[index] may violate heap order with parent */
    void percolateUp(int index) {
    	int first_non_leaf_node = (size - 1 / 2);
    	for(int i = first_non_leaf_node; i >= 0; i--)
    	{
    		percolateDown(i);
    	}
    }

    /** pq[index] may violate heap order with children */
    void percolateDown(int index) {
    	int minimum = index;
    	int left_child = leftChild(index);
    	int right_child = left_child + 1;
    	if(left_child <= size)
    	{	
    		int left = (int) pq[left_child];
    		if(left < (int) pq[minimum])
    		{
    			minimum = left_child;
    		}
    	}
    	if(right_child <= size)
    	{	
    		int right = (int) pq[right_child];
    		if(right < (int) pq[minimum])
    		{
    			minimum = right_child;
    		}
    	}
    	if(index != minimum)
    	{
    		Comparable temp = pq[index];
    		pq[index] = pq[minimum];
    		pq[minimum] = temp;
    		percolateDown(minimum);
    	}
    }


    int compare(Comparable a, Comparable b) {
	return ((T) a).compareTo((T) b);
    }
    
    /** Create a heap.  Precondition: none. */
    void buildHeap() {
	for(int i=parent(size-1); i>=0; i--) {
	    percolateDown(i);
	}
    }

    public boolean isEmpty() {
	return size() == 0;
    }

    public int size() {
	return size;
    }

    // Resize array to double the current size
    Comparable[] resize() {
    	Comparable[] new_pq = new Comparable[2 * size];
        for(int i = 0; i < size; i++)
        {
        	new_pq[i] = pq[i];
        }
    	return new_pq;	
    }
    

    public static void main(String[] args) {
		Integer[] arr = {0,9,7,5,3,1,8,6,4,2};
		BinaryHeap<Integer> h = new BinaryHeap(arr.length);
		System.out.println("Is Empty");
		System.out.println(h.isEmpty());
		System.out.println();
		
		System.out.print("Before:");
		for(Integer x: arr) 
		{
		    h.offer(x);
		    System.out.print(" " + x);
		}
		System.out.println();
		System.out.println("The Heap after adding elements in array is ");
		for(Comparable c : h.pq)
		{
			System.out.println(c);
		}
		
		System.out.println();
		System.out.println("Trying to check if resize works");
		h.offer(2000);
		System.out.println("The Heap after adding elements in array is ");
		for(int i = 0; i < h.size(); i++)
		{
			System.out.println(h.pq[i]);
		}
		
		System.out.println();
		System.out.println("The first element in heap is ");
		Comparable pq_front = h.peek();
		System.out.println(pq_front);
		
		System.out.println();
		System.out.println("Trying to extract min");
		System.out.println("Size before deletion");
		System.out.println(h.size());
		h.poll();
		System.out.println("size after deletion");
		System.out.println(h.size());
		System.out.println();
		
		System.out.println();
		System.out.println("The first element in heap is ");
		Comparable pq_front_1 = h.peek();
		System.out.println(pq_front_1);
		
		System.out.println();
		System.out.println("The size of heap before removing elements");
		System.out.println(h.size());
		for(int i=0; i<arr.length; i++) 
		{
		    arr[i] = h.poll();
		}
		System.out.println("The size of heap after removing elements");
		System.out.println(h.size());
	}
}
