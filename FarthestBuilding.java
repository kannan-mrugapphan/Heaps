// 1642.
// time - O(nlogn) - use bricks every time
// space - O(n)
// ladders can be used to climb any height difference, so ladders must be used to only for large differences

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        //edge
        if(heights == null || heights.length == 0)
        {
            return 0;
        }
        
        //to store places where bricks where used to match difference
        PriorityQueue<Integer> bricksUsed = new PriorityQueue<>((Integer a, Integer b) -> b - a); 
        int current = 0; //initially at 0th building
        while(current < heights.length - 1) //as long as theer are buildings
        {
            if(heights[current] >= heights[current + 1])
            {
                //no bricks or ladders needed
                current++;
            }
            else
            {
                int diff = heights[current + 1] - heights[current]; 
                if(bricks >= diff)
                {
                    //use bricks and store the diff in max heap for a potential replacement with ladder later
                    bricks -= diff; //use bricks
                    bricksUsed.offer(diff); //store diff in heap
                    current++; //go to next building
                }
                else if(ladders > 0)
                {
                    //check if there is a place previously where a ladder can be used instead of bricks
                    if(bricksUsed.size() > 0)
                    {
                        int prevLargeDiff = bricksUsed.peek();
                        if(diff < prevLargeDiff) //if current diff is smaller than prev large diff, use a ladder there
                        {
                            bricksUsed.poll(); //remove prev diff
                            bricks += prevLargeDiff; //regain those bricks;
                            bricksUsed.offer(diff); //use bricks here
                            bricks -= diff; 
                        }
                    }
                    ladders--; //use ladder at current or at prev larger diff
                    current++; //go to next
                }
                else
                {
                    break;
                }
            }
        }
        
        return current;
    }
}
