// 2519.
// time - O(n logk)
// space - O(k) for heap and O(n) for boolean[]

/*
  You are given a 0-indexed integer array nums and a positive integer k.
  We call an index i k-big if the following conditions are satisfied:  
  There exist at least k different indices idx1 such that idx1 < i and nums[idx1] < nums[i].
  There exist at least k different indices idx2 such that idx2 > i and nums[idx2] < nums[i].
  Return the number of k-big indices.
*/

class Solution {
    public int kBigIndices(int[] nums, int k) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return 0;
        }

        //ith index is true if there are atleast k elements smaller than nums[i] in left
        boolean[] isValid = new boolean[nums.length]; 
        //max heap at any point has current set of k largest elements
        PriorityQueue<Integer> support = new PriorityQueue<Integer>((Integer a, Integer b) -> b - a);

        for(int i = 0; i < nums.length; i++)
        {
            //check if there are k smaller elements to left of i
            if(support.size() == k && support.peek() < nums[i])
            {
                isValid[i] = true; 
            }

            support.offer(nums[i]);
            //pop the largest element if size becomes larger than k as it doesn't affect the future elements 
            if(support.size() > k)
            {
                support.poll(); 
            }
        }

        support.clear();
        int result = 0;

        for(int i = nums.length - 1; i >= 0; i--)
        {
            //check if there are k smaller elements to right of i
            //also check if the same holds in left by looking at isValid[i]
            if(support.size() == k && nums[i] > support.peek() && isValid[i])
            {
                result++; //i is a valid index
            }
            support.offer(nums[i]);
            //pop the largest element if size becomes larger than k as it doesn't affect the future elements 
            if(support.size() > k)
            {
                support.poll(); 
            }
        }

        return result;
    }
}
