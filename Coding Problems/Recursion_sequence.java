/*
  Print series like : 16, 11, 6, 1, -4, 1, 6, 11, 16 
  Observe that series keeps on decreasing. Once it hits a negative number, it starts increasing until it reaches the starting value.
  Problem statement asked for a recursive solution.
*/

// curr = current number, target = start value, step = step size, down indicates whether series is incrementing or decrementing.
static void solve (int curr, int target, int step, boolean down) {
    System.out.print(curr + " ");
    // if the current value has reached the target (while going up i.e series was incrementing) then stop and return
    if(curr >= target && !down) return;
    // if the current value was a negative number, series must start incrementing, hence we make down = false
    if(curr < 0) down = false;
    // the incrementing/decrementing nature of the series is governed by the down variable
    if(down) solve(curr-step, target, step, down);
    else solve(curr+step, target, step, down);
}

