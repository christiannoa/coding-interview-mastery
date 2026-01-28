import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // HaspMap stores value and its index mapping
        Map<Integer, Integer> map = new HashMap<>();

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {

            // what we need to find to reach the target, the complement
            int complement = target - nums[i];

            // check if we have seen the complement before, if yes return the indices
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i); // store the number and it's index
        }

        throw new IllegalArgumentException("No two sum solution");

        // or we can just return no solution found
        // return new int[] {};
    }
}

/*
 * 
 * Time Complexity: O(n) - We visit each element once
 * Space Complexity: O(n) - HashMap stores up to n elements
 * Why this works: Instead of checking every pair (O(n²)), we use a HashMap to
 * instantly
 * check if the complement exists (O(1) lookup).
 * 
 * Example: nums = [2,7,11,15], target = 9
 * 
 * i=0: need 7, seen={}, add 2
 * i=1: need 2, found! return [0,1]
 * 
 */