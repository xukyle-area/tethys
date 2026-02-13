import java.util.PriorityQueue;

public class MedianFinder {

    // min_1 --- maxHeap --- max_1, min_2 --- minHeap --- max_2
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // if the total count is Odd, make the middle one at the top of the maxHeap
    public void addNum(int num) {
        // decide to pull to which heap
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            // if < max_1, put into maxHeap
            maxHeap.offer(num);
        } else {
            // if > min_2, put into minHeap
            minHeap.offer(num);
        }
        // if count of minHeap is more than maxHeap's, move the min one of minHeap to the maxHeap
        // move the min_2 to max_1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * every time, we can get max_1 and min_2, and get the average of them
     * @return
     */
    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}
