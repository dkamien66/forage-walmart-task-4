// Max Heap Property - For any given node, its parent is greater than or equal to it

public class powerOfTwoMaxHeap {

    private int[] heapArray;

    private int capacity;

    private int currentSize;

    private int numChildrenPerParent;

    /*
     * Constructor method for this heap implementation.
     * param: capacity, the total size of the heap array
     * param: numChildren, every parent node has 2^numChildren children
     */
    public powerOfTwoMaxHeap(int capacity, int numChildren) {
        this.capacity = capacity;
        this.heapArray = new int[capacity];
        this.currentSize = 0;
        this.numChildrenPerParent = 2^numChildren;
    }

    /*
     * Method for inserting values into the heap.
     */
    public boolean insert(int n) {
        // Check if the heap is full
        if (this.currentSize == this.capacity) {
            return false;
        } 
        else {
            // First, add num to the end of the heap
            int current_index = this.currentSize; // Keep track of changed index
            this.heapArray[currentSize] = n;
            this.currentSize++;
            // Then check if max heap property is violated
            while (current_index != 0 && this.heapArray[current_index] > this.heapArray[parent(current_index)]) {
                // Swap current num with parent
                int temp = this.heapArray[parent(current_index)];
                this.heapArray[parent(current_index)] = this.heapArray[current_index];
                this.heapArray[current_index] = temp;
                current_index = parent(current_index);
            }
            return true;
        }
    }

    /*
     * Returns the parent index of the inputted index
     */
    private int parent(int index) {
        return (index-1)/numChildrenPerParent; // Note - Java does flooring for us with integer division and removes fraction part
    }

    /*
     * Removes the largest number from the heap
     */
    public int popMax() {
        if (this.currentSize <= 0) {
            return -1;
        }
        else if (this.currentSize == 1) {
            int max = this.heapArray[0];
            this.heapArray[0] = 0;
            this.currentSize--;
            return max;
        }
        else {
            int max = this.heapArray[0];
            // replace root with last element in array
            this.heapArray[0] = this.heapArray[this.currentSize-1];
            this.currentSize--;

            // Call recursive heapify method
            this.heapify(0);
            return max;
        }
    }

    /*
     * A method that checks the Max Heap Property for a subtree and recursively calls for swapped child
     * param: index, the index to check children of and swap if property is violated
     */
    private void heapify(int index) {
        int largest = index; // will hold the index of the largest number in this subtree
        int child_index; // will hold the index of each child node

        // Compare the root of this subtree to each of its children using a loop
        for (int i = 1; i <= this.numChildrenPerParent; i++) {
            // Obtain the child index
            child_index = this.numChildrenPerParent * index + i;
            // Compare num at child index to root, save as largest if it is
            if (child_index < this.currentSize && this.heapArray[child_index] > this.heapArray[index]) {
                largest = child_index;
            }
        }

        if (largest != index) {
            // Swap root node with the child node with index of largest
            int temp = this.heapArray[index];
            this.heapArray[index] = this.heapArray[largest];
            this.heapArray[largest] = temp;
            heapify(largest);
        }
    }



    public static void main(String[] args) {
        powerOfTwoMaxHeap q1 = new powerOfTwoMaxHeap(100,3);
        System.out.println(q1.insert(20));
        System.out.println(q1.insert(40));
        System.out.println(q1.insert(100));
        System.out.println(q1.insert(1));
        System.out.println(q1.popMax());
    }
}