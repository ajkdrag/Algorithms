/*
  Problem statemet : https://www.hackerrank.com/challenges/candies/problem
  
  Method 1 :
    We first create a candies array to store the candies given to the children, and initialize it with '0's implying that
    the number of candies given to each child is 0 initially. Next we run a loop on the children and if the number of candies
    given to any child is 0, we give it the required number of candies and proceed. This "required number of candies" is computed
    using a recursive function.
    
    The function has base cases which are defined for out of bounds indices and for children whose ages are the smallest from among
    their adjacent children. If child 'i' is older than it's right child, it obtains the number of candies given to right child, 
    recursively and sets it's right variable as : right = getNumCandies(i+1) + 1, since 1 extra needs to be given.
    Similarly, left = getNumCandies(i-1) + 1, Now maxOf(left, right) shall be given to child 'i'
    In case of same ages, we don't have this constraint, so we keep left, right = 1 and suppose left child has same age, but
    right child is smaller, then we obtain the number of candies given to right recursively, and add 1 to it.
    Thus child 'i' gets maxOf(1, right), similarly if right is same, but left is smaller, we have maxOf(left, 1)
    And in case where both adjacent children have same ages as child 'i', child 'i' gets 1, i.e maxOf(1, 1)

    Finally, return the sum of all the candies given to all the children
*/


public class Solution {

    int[] candies;
    int[] ages;
    int n;
    
    // Complete the candies function below.
    long candies(int n, int[] ages) {  
        this.n = n;
        this.ages = ages;
        // we create a candies array 
        candies = new int[n];
        // sum will hold the total number of candies 
        long sum = 0;
        // run a loop through all the ages and accordingly fill up the candies array
        for (int i = 0; i < n; i++) {
            // candies[i] == 0 implies no candy has been given to child 'i' 
            if (candies[i] == 0){
                 candies[i] = getNumCandies(i);
            }
            sum += candies[i];   
        }
        return sum;
    }
    
    // recursive funtion for getting the number of candies for child 'i'
    int getNumCandies(int i) {
        // base condition for out of bounds case
        if(i < 0 || i > n - 1) return 0;
        
        // if child 'i' has non-zero candies, return this value (base condition)
        if (candies[i] != 0)
            return candies[i];
        
        // if child 'i' is the smallest among it's left and right children, it gets only 1 candy
        if ((i > 0 && ages[i] < ages[i - 1]) && (i < n - 1 && ages[i] < ages[i + 1])) {
            candies[i] = 1;
            return 1;
        }
        
        // left and right variables store the candies seen from left and right sides respectively
        // they are initialized to 1. This takes care of the cases of ages being equal
        int left = 1;
        int right = 1;
        
        // recursively get the number of candies of smaller adjacent child
        // give 1 more than the number of candies given to the smaller adjacent child
        if (i > 0 && ages[i] > ages[i - 1])
            left = getNumCandies(i - 1) + 1;  
        if (i < n - 1 && ages[i] > ages[i + 1]) {
            right = getNumCandies(i + 1) + 1; 
        }
        
        // in case the ages are equal, since we have initialized the left and right
        // variables as 1, the deciding factor only depends on having a smaller child
        // on either side, else the number of candies given is 1
        // eg : ...3 3 2... {i is at the second 3 say}
        // here since left side is also 3, we only give 'i' one more candy than the 
        // number of candies given to child aged 2. Thus left = 1, right is deciding factor
        // similarly for the case of ...2 3 3...here, right = 1, left is deciding factor
        // In case of equal ages, we don't have to check with the same aged child
        // we just want to minimize the number of candies given.
        // Note that in case of ...3 3 3..., since left and right both are equal
        // we only give it 1 candy, because it isn't greater than any of it's adjacent children
        
        // the max number obtained from left and right sides will be the required 
        // number of candies given to this child
        int val = left > right ? left : right;
        candies[i] = val;
        return val;
    }
}


/*
  Another variation to this problem exists where if a child has adjacent children of same ages, they will get equal number of candies
  For example : [3, 3, 2] 
  In previous version of the problem, the candies given were : {1, 2, 1]
  But in this version of the problem, the candies given will be : {2, 2, 1] 
  since the number of candies given to same aged adjacent children must be equal.
  
  The general algo remains the same as previously, only difference is that we have to recursively call for equal age case as well
  for eg : [1, 4, 4, 4, 2, 1]
  for age 1, candy given will be 1, for age 4, left = 2 {since it's older than the child on it's left}
  But, it's right child has same age, so it has to also check on the right by calling it recursively
  Now, problem is when the right child is called, it's age is same as it's either children, so it depends on both
  so it calls to both left and right. This will lead to a recursive loop. In order to avoid this, once the leftmost child aged 4
  has it's left = 2, it remembers this current value by giving itself 2, and then calling it's right.
  Now since right itself is equal to both it's adjacent children, it will call both of them recursively, but the call to it's left
  will return 2, thus avoiding the recursive loop. Now, since it got 2 from it's left it will also give this value to itself
  before calling it's right and so on.. eventually, we will either hit a right child whose age is greater in which case, recursive 
  calls to the right will end, or we will hit a right child whose age is smaller, in which case recursive calls to the right will go
  on until either reaching the end of the array or hitting a child which is smaller than both it's adjacent children.
  
  So, for this example, 
  
  # 1 will have 1 candy.
  
  # 4 will have left = 1 + 1 = 2, and right is equal, so 4 "saves" 2 by doing candies[i] = 2 and then calls it's right
  
  # next 4 has both it's left and right equal to itself, so it calls them recursively : 
        call to the left, returns 2, because the left 4 had "saved" it's own left
        since we got 2 from left. This 4 also "saves" 2 by doing candies[i] = 2 and then calls it's right
    
  # next 4 also has it's left equal so it again calls to the left and obtains 2 which it also "saves"
        it then calls to the right since right is lesser, (i.e call to the child , 2)
        
  # 2 now calls 1 to it's right since it's smaller
  
  # 1 is the last child and it's "smallest" among it's adjacent children, so it gets 1 candy, and returns 1 to it's caller
  
  # 2 now has right = (1 + 1) = 2, so it gets 2 candies and returns 2 to it's caller,
  
  # rightmost 4, now has right = (2 + 1) = 3 and it also had previously saved 2 which it obtained from left 
        thus max(2, 3) = 3, so child aged 4 now has 3 candies, and it also returns 3 to it's caller
  
  # the other 4's so on also receive this returned 3 in a chain, and update their candies[i] to max(2, 3) i.e 3
  
  Thus finally candies = [1, 3, 3, 3, 2, 1]

*/


// just the recursive function is modified a bit 
// NOTE : here, for simplifying, we assume the ages are appended with 0's at it's extremes
// eg : if ages was : [2, 2, 1] it now is [0, 2, 2, 1, 0], this spares us the hassle of bounds check
// we can also not choose to do this and check for bounds manually, like we did in previous algo, rest is similar

int getNumCandies(int m) {

    // base cases
		if (candies[m] != 0)
			return candies[m];
		if (ages[m] < ages[m - 1] && ages[m] < ages[m + 1]) {
			candies[m] = 1;
			return 1;
		}
    
		int left = 1;
		int right = 1;
    
		if (ages[m] > ages[m - 1])
			left = get(m - 1) + 1;
      
    // obtain from left if it's equal
		if (ages[m] == ages[m - 1])
			left = get(m - 1);
		if (ages[m] > ages[m + 1]) {
			right = get(m + 1) + 1;
		}
    
    // before calling to the right (if it's equal) make sure to "save" the value obtained from left 
    // to avoid the recusive loop we talked about in the algo
		if (ages[m] == ages[m + 1]) {
			candies[m] = left;
			right = get(m + 1);
		}

		candies[m] = Math.max(left, right);
		return candies[m];
	}
