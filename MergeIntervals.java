import java.util.*;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {

        // Step 1: sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // List to srore merged intervals
        List<int[]> merged = new ArrayList<>();

        // step 2: iterate through intervals and merge
        for (int[] interval : intervals) {
            // if merged is empty or current interval does not overlap with last merged
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {

                // there is an overlap, merge the current interval with the last merged interval
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        // conver merged list to array and return
        return merged.toArray(new int[merged.size()][]);

    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        // Test case 1: overlapping intervals
        int[][] intervals1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        System.out.println(Arrays.deepToString(solution.merge(intervals1)));
        // Expected: [[1, 6], [8, 10], [15, 18]]

        // Test case 2: fully contained intervals
        int[][] intervals2 = { { 1, 4 }, { 2, 3 } };
        System.out.println(Arrays.deepToString(solution.merge(intervals2)));
        // Expected: [[1, 4]]

        // Test case 3: no overlap
        int[][] intervals3 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        System.out.println(Arrays.deepToString(solution.merge(intervals3)));
        // Expected: [[1, 2], [3, 4], [5, 6]]

        // Test case 4: single interval
        int[][] intervals4 = { { 1, 5 } };
        System.out.println(Arrays.deepToString(solution.merge(intervals4)));
        // Expected: [[1, 5]]

        // Test case 5: unsorted input
        int[][] intervals5 = { { 8, 10 }, { 1, 3 }, { 2, 6 }, { 15, 18 } };
        System.out.println(Arrays.deepToString(solution.merge(intervals5)));
        // Expected: [[1, 6], [8, 10], [15, 18]]
    }
}
/*
 * Time Complexity: O(n log n) - Dominated by sorting
 * Space Complexity: O(n) - Output array (or O(log n) if not counting output,
 * for sorting stack)
 * 
 * Merge Intervals: Sort first, then greedy merge
 * 
 * For merging intervals, the key insight is to sort them first by start time.
 * Once they're sorted, I only need to worry about whether the current interval
 * overlaps with the last merged interval.
 * I'll keep a result list. For each interval, I check: does it overlap with the
 * last interval in my result? To overlap, the start of the current interval
 * must be less than or equal to the end of the last merged interval.
 * If they overlap, I merge them by extending the end of the last interval to be
 * the maximum of both ends. If they don't overlap, I just add the current
 * interval as a new separate interval.
 * Time complexity is O(n log n) because of the sorting - the merging itself is
 * just O(n). Space is O(n) for the output, or O(log n) if we don't count the
 * output, just the sorting space.
 */