/*
    Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
    Note: The input string may contain letters other than the parentheses ( and ).
    Examples:
    "()())()" -> ["()()()", "(())()"]
    "(a)())()" -> ["(a)()()", "(a())()"]
    ")(" -> [""]
    
    Method 1 : 
    BFS:
    Graph definition:
    Vertex: a candidate string.
    Edge: two strings s1 and s2 have an edge if s1 equals s2 with one parenthesis deleted.
    Put string into a queue.
    For the current size of the queue, poll a string from the queue.
    Iterate through the string. For each character, remove it, and check if the parentheses are valid.
    If so, iterate over current level and return the result.
    If not, offer the new string to the queue.
*/

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> list = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        Set<String> visited = new HashSet<>();
        visited.add(s);
        
        boolean found = false;
        while (!found && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                if (isValid(str)) {
                    list.add(str);
                    found = true;
                    continue;
                }
                
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) != '(' && str.charAt(j) != ')') {
                        continue;
                    }
                    
                    String child = str.substring(0, j) + str.substring(j + 1);
                    if (!visited.contains(child)) {
                        queue.offer(child);
                        visited.add(child);
                    }
                }
            }
        }
        return list;
    }
    
    private boolean isValid(String s) {
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open == 0) {
                    return false;
                }
                open--;
            }
        }
        return open == 0;
    }
}

/*
    Method 2 : 
    There is another solution using DFS.
    Calculate the number of invalid parentheses of the original string. 
    Iterate through the string. Remove each character and DFS if the number of invalid parentheses decreases.
    This solution is based on the fact that if we're on the right path to the optimal string;
    the number of invalid parentheses must always decrease.
*/

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> list = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        removeInvalidParentheses(s, numberOfInvalid(s), list, visited);
        return list;
    }
    
    private void removeInvalidParentheses(String s, int invalid, List<String> list, Set<String> visited) {
        if (invalid == 0) {
            list.add(s);
            return;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                continue;
            }
            
            String child = s.substring(0, i) + s.substring(i + 1, s.length());
            if (!visited.contains(child)) {
                visited.add(child);
                int next = numberOfInvalid(child);
                
                if (next < invalid) {
                    removeInvalidParentheses(child, next, list, visited);
                }
            }
        }
    }
    
    private int numberOfInvalid(String s) {
        int open = 0;
        int close = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open == 0) {
                    close++;
                } else {
                    open--;
                }
            }
        }
        return open + close;
    }
}
