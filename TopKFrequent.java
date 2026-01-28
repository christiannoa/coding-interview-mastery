import java.util.*;

public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {

        // step 1: count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            // adjust k if it exceeds unique elements, prevents polling more than exists
            k = Math.min(k, freqMap.size());
        }

        // step 2: min-heap to keep top k frequent elements, minheap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue());

        // step 3: add entries to the heap
        for (

        Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry); // add entry to the heap

            // if heap size exceeds k, remove the least frequent element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // step 4: extract the top K frquent elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequent solution = new TopKFrequent();

        // Test case 1: basic example
        int[] nums1 = { 1, 1, 1, 2, 2, 3 };
        int k1 = 2;
        System.out.println(Arrays.toString(solution.topKFrequent(nums1, k1)));
        // Expected: [1, 2] (order may vary)

        // Test case 2: single element
        int[] nums2 = { 5 };
        int k2 = 1;
        System.out.println(Arrays.toString(solution.topKFrequent(nums2, k2)));
        // Expected: [5]

        // Test case 3: negative numbers
        int[] nums3 = { -1, -1, -2, -2, -2, -3 };
        int k3 = 2;
        System.out.println(Arrays.toString(solution.topKFrequent(nums3, k3)));
        // Expected: [-2, -1]

        // Test case 4: k larger than unique count
        int[] nums4 = { 4, 4, 6 };
        int k4 = 5;
        System.out.println(Arrays.toString(solution.topKFrequent(nums4, k4)));
        // Expected: [4, 6]

        // Test case 5: all elements same frequency
        int[] nums5 = { 7, 8, 9 };
        int k5 = 2;
        System.out.println(Arrays.toString(solution.topKFrequent(nums5, k5)));
        // Expected: any 2 of [7, 8, 9]
    }
}

/*
 * Time Complexity: O(n log k) - n for counting, n * log k for heap operations
 * Space Complexity: O(n) - HashMap stores unique elements, heap stores at most
 * k
 * 
 * Heap: Min heap of size K keeps K largest; Max heap keeps K smallest
 * Min Heap for Max K: Threshold filter - keep best K, evict worst of best
 * 
 * I only care about the top K, not about sorting everything. A min heap of size
 * K acts like a 'threshold filter'.
 * 
 * I need to find the K most frequent elements. My approach uses a frequency map
 * and a min heap.
 * Then here's the clever part: I use a min heap of size K. A min heap keeps the
 * smallest element at the top, so if I maintain a heap of exactly K elements,
 * it'll automatically keep the K largest frequencies and kick out the smallest
 * ones.
 * As I iterate through my frequency map, I add each element to the heap.
 * Whenever the heap size exceeds K, I remove the top element - which is the one
 * with the smallest frequency. At the end, my heap contains exactly the K most
 * frequent elements.
 * Time complexity is O(n log k) - n for counting frequencies, and for each
 * unique element, I do a log k heap operation. Space is O(n) for the frequency
 * map.
 */