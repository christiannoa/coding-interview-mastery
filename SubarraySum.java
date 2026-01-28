import java.util.HashMap;
import java.util.Map;

public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        prefixSumCount.put(0, 1); // Base case: one way to have sum 0

        int currentSum = 0; // running prefix sum
        int count = 0; // number of valid subarrays

        // iterate through the array
        for (int num : nums) {
            currentSum += num; // update the running sum

            int neededSum = currentSum - k;

            if (prefixSumCount.containsKey(neededSum)) {
                count += prefixSumCount.get(neededSum);
            }

            // record the current prefu sum in the map
            prefixSumCount.put(
                    currentSum,
                    prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }
        return count;

    }

    public static void main(String[] args) {
        SubarraySum solution = new SubarraySum();

        // Example 1
        int[] nums1 = { 1, 1, 1 };
        int k1 = 2;
        System.out.println(solution.subarraySum(nums1, k1));
        // Expected: 2 -> [1,1] at (0..1) and (1..2)

        // Example 2
        int[] nums2 = { 1, 2, 3 };
        int k2 = 3;
        System.out.println(solution.subarraySum(nums2, k2));
        // Expected: 2 -> [1,2] and [3]

        // Example 3 (includes negative numbers)
        int[] nums3 = { 1, -1, 0 };
        int k3 = 0;
        System.out.println(solution.subarraySum(nums3, k3));
        // Expected: 3 -> [1,-1], [0], [1,-1,0]

        // Example 4
        int[] nums4 = { 3, 4, 7, 2, -3, 1, 4, 2 };
        int k4 = 7;
        System.out.println(solution.subarraySum(nums4, k4));
        // Expected: 4 -> [3,4], [7], [7,2,-3,1], [1,4,2]

        // Example 5 (single element)
        int[] nums5 = { 5 };
        int k5 = 5;
        System.out.println(solution.subarraySum(nums5, k5));
        // Expected: 1
    }
}

/*
 * 
 * Time Complexity: O(n) - Single pass through array
 * Space Complexity: O(n) - HashMap can store up to n different prefix sums
 * 
 * The trick here is using prefix sums with a HashMap.
 * if I keep a running sum as I go through the array, and at some point my
 * running sum is, say, 10, and I'm looking for K equals 3, then I need to check
 * if I've ever seen a running sum of 7 before. Because if I have, that means
 * the subarray between that point and now sums to exactly 3.
 * So I use a HashMap to store how many times I've seen each prefix sum. For
 * each element, I check if (currentSum - K) exists in my map - if it does,
 * those are valid subarrays ending at the current position.
 * Important edge case: I initialize my HashMap with sum 0 appearing once, to
 * handle subarrays that start from index 0.
 * Example: nums = [1,1,1], k = 2
 */