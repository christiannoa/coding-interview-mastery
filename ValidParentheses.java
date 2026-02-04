import java.util.*;

class ValidParentheses {
    public boolean isValid(String s) {
        // Create a stack to keep track of opening parentheses
        Stack<Character> stack = new Stack<>();

        // iterate through each character in the string
        for (char c : s.toCharArray()) {

            // if it's an opening bracket, push to stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }

            // if it's a closing bracket, check for matching opening bracket
            else {
                // stack must not be empty (we need an opening bracket to match)
                if (stack.isEmpty()) {
                    return false;
                }
                // pop (the first element) from the stack and check if it matches))
                char top = stack.pop();

                // check for matching pairs
                if (c == ')' && top != '(')
                    return false;
                if (c == '}' && top != '{')
                    return false;
                if (c == ']' && top != '[')
                    return false;
            }
        }

        // if stack is empty, all opening brackets had matching closing brackets
        return stack.isEmpty();
    }
}

/*
 * 
 * Time Complexity: O(n) - Visit each character once
 * Space Complexity: O(n) - Worst case, all opening brackets
 * 
 * // Example 1: "()"
 * // '(': push, stack=['(']
 * // ')': pop '(', matches! stack=[]
 * // stack.isEmpty()=true, return true
 * 
 * // Example 2: "()[]{}"
 * // '(': stack=['(']
 * // ')': pop, match, stack=[]
 * // '[': stack=['[']
 * // ']': pop, match, stack=[]
 * // '{': stack=['{']
 * // '}': pop, match, stack=[]
 * // Return true
 * 
 * // Example 3: "(]"
 * // '(': stack=['(']
 * // ']': pop '(', doesn't match ']', return false
 * 
 * pop() Returns the first element in the list. Similar to removeFirst()
 * 
 * push() Adds an item to the beginning of the list. Similar to addFirst()
 * 
 */