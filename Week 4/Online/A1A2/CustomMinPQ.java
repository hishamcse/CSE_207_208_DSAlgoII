/*
  Implementation of indexed min priority queue/ minHeap
 */

import java.util.Arrays;

public class CustomMinPQ<Key extends Comparable<Key>> {

    private final int[] customPQ;     // storing index of the key in heap position
    private final int[] inversePQ;    // storing heap position of the key with index
    private final Key[] priorities;   // storing keys as priorities
    private int capacity, numOfNodes;

    public CustomMinPQ(int capacity) {
        numOfNodes = 0;
        this.capacity = capacity + 1;
        customPQ = new int[this.capacity];
        inversePQ = new int[this.capacity];
        Arrays.fill(inversePQ, -1);
        priorities = (Key[]) new Comparable[this.capacity];
    }

    // private helper methods
    private int comparePriority(int i, int j) {
        return priorities[customPQ[i]].compareTo(priorities[customPQ[j]]);
    }

    private void swap(int i, int j) {
        int temp = customPQ[i];
        customPQ[i] = customPQ[j];
        customPQ[j] = temp;
        inversePQ[customPQ[i]] = i;
        inversePQ[customPQ[j]] = j;
    }

    private void bottomUpHeapify(int index) {
        while (index > 1 && comparePriority(index / 2, index) > 0) {
            swap(index / 2, index);
            index /= 2;
        }
    }

    private void topDownHeapify(int index) {
        while (2 * index <= numOfNodes) {
            int childId = 2 * index;
            if (childId < numOfNodes && comparePriority(childId, childId + 1) > 0) childId++;
            if (comparePriority(index, childId) <= 0) break;
            swap(index, childId);
            index = childId;
        }
    }

    // public methods
    public void insert(int id, Key key) {
        numOfNodes++;
        inversePQ[id] = numOfNodes;
        customPQ[numOfNodes] = id;
        priorities[id] = key;
        bottomUpHeapify(numOfNodes);
    }

    public int deleteMin() {
        int minId = customPQ[1];
        swap(1, numOfNodes--);
        topDownHeapify(1);
        inversePQ[minId] = -1;
        priorities[minId] = null;
        customPQ[numOfNodes + 1] = -1;
        return minId;
    }

    public Key deleteMinKey() {
        Key key = priorities[customPQ[1]];
        deleteMin();
        return key;
    }

    // update with new priority
    public void decreaseKey(int id, Key key) {
        priorities[id] = key;
        bottomUpHeapify(inversePQ[id]);
    }

    public boolean contains(int v) {
        return inversePQ[v] != -1;
    }

    public boolean isEmpty() {
        return numOfNodes == 0;
    }
}