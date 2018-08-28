/*
    Given a string, remove invalid parentheses.
    Note that this algo is for braces, we can extend it to work with all types of parentheses as well.
    e.g:    if Str = "Let's ((Go!Yay ()"
            outupt will be : "Let's Go!Yay ()"
    
    In order to achieve this, we use a stack "brackets" and an array "allow", the algo works as follows :
    The "brackets" stack is empty initially, and the "allow" array is filled with 0's initially.
    First we scan the given string left to right, if the character scanned if not a bracket
    we fill it's corresponding index in the array "allow" with '1', implying that it will be "allowed" to be in the output
    If we encounter an opening parentheses, we simply push it's index into the "brackets" stack.
    If we encounter a closing parentheses, we pop from the stack (if it's not empty),
    the index popped is the index of the opening parentheses corresponding to this closing parentheses.
    Thus, we allow both the index of the popped opening parentheses and the current index of the closing parentheses
    to be in the output, by filling their corresponding indices in the "allow" array with 1's.
    This way only the matching parentheses and characters which are not parentheses will have their "allow" values as 1
    We can finally scan through the "allow" array, and wherever the value is 1, we output the corresponding character.
*/

import java.util.Stack;
public class Main {
	public static void main(String[] args) {
		String input = "Let's )(Go!Yay ()";
		int n = input.length();

		// initialize the "allow" array and the "brackets" stack
		short[] allow = new short[n]; // we use short to save space, since only 1's and 0's will be stored
		Stack < Integer > brackets = new Stack < >();
		for (int i = 0; i < n; i++) {
			char temp = input.charAt(i);

			// "allow" characters that are not parentheses
			if (temp != '(' && temp != ')') allow[i] = 1;
			// push the index of the opening parentheses in the stack
			else if (temp == '(') {
				brackets.push(i);
			}
			// if for the current closing parentheses, we have a matching opening parentheses index
			// we "allow" these matched parentheses
			else if (temp == ')' && !brackets.isEmpty()) {
				allow[brackets.pop()] = 1;
				allow[i] = 1;
			}

		}

		// print only the allowed characters
		for (int i = 0; i < n; i++) {
			if (allow[i] == 1) System.out.print(input.charAt(i));

		}

	}
}
