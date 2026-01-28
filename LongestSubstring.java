import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {

        Set<Character> window = new HashSet<>();
        int left = 0; // left pointer
        int maxLength = 0; // max length of substring w/o repeasting chars

        // right pointer iterates through the string
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // if current char is already in the window, shrink from left
            while (window.contains(currentChar)) {
                window.remove(s.charAt(left)); // remove leftmost char
                left++;
            }

            // add current char to the window
            window.add(currentChar);

            // update max length if needed
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstring ls = new LongestSubstring();

        System.out.println(ls.lengthOfLongestSubstring("abcabcbb")); // 3 ("abc")
        System.out.println(ls.lengthOfLongestSubstring("bbbbb")); // 1 ("b")
        System.out.println(ls.lengthOfLongestSubstring("pwwkew")); // 3 ("wke")
        System.out.println(ls.lengthOfLongestSubstring("")); // 0
        System.out.println(ls.lengthOfLongestSubstring("au")); // 2
        System.out.println(ls.lengthOfLongestSubstring("dvdf")); // 3 ("vdf")
    }
}

/*
 * 
 * Time Complexity: O(n) - Each character visited at most twice (once by right,
 * once by left)
 * Space Complexity: O(min(n, m)) - m is charset size (at most 26 for lowercase
 * letters)
 * Why this works: Instead of checking every substring (O(n³)), we maintain a
 * valid window and slide it across the string.
 * 
 * Example: s = "abcabcbb"
 * 
 * Window grows: a, ab, abc (length 3)
 * Hits 'a' again, shrinks to bca, then cab, then abc
 * Answer: 3 ("abc")
 * 
 * 
 */