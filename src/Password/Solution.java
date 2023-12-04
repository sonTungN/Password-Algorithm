package Password;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        helper(nums, 0);
        return res;
    }

    private void helper(int[] nums, int index) {
        // base case
        if (index == nums.length) {
            List<Integer> solution = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                solution.add(nums[i]);
            }
            res.add(solution);
            return;
        }

        // recursion
        for (int i = index; i < nums.length; i++) {
            // swap each number to current position
            swap(nums, index, i);
            helper(nums, index + 1);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 1, 3};
        List<List<Integer>> res = s.permute(nums);
        for(List<Integer> l : res){
            System.out.print("[ ");
            for(Integer i : l){
                System.out.print(i + " ");
            }
            System.out.println("]");
        }
    }
}