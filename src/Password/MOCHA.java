package Password;

import java.util.List;
import java.util.Vector;

public class MOCHA {
    static Vector<Vector<Integer>> ans = new Vector<>();

    public static void perm(Vector<Integer> nums, int i){
        if(i == nums.size()){
            ans.add(new Vector<>(nums)); // Create a new vector for each permutation
            System.out.println("RETURN");
            return;
        }
        for(int j = i; j < nums.size(); j++){
            System.out.print("Swap value " + i + " and " + j + " is: " + nums.get(i) + " " + nums.get(j) + ": ");
            int tmp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, tmp);
            for(Integer in : nums){
                System.out.print(in + " ");
            }
            System.out.println();
            perm(nums, i + 1);

            System.out.print("Swap value " + i + " and " + j + " is: " + nums.get(i) + " " + nums.get(j) + ": ");
            tmp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, tmp);

            for(Integer in : nums){
                System.out.print(in + " ");
            }
            System.out.println();
//            System.out.println("Before 2: " + "num[i] = " + nums.get(i) + " num[j] = " + nums.get(j));
        }
    }

    public static Vector<Vector<Integer>> permuteUnique(Vector<Integer> nums){
        perm(nums, 0);
        return ans;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3};
        Vector<Integer> nums = new Vector<>(List.of(arr));
        Vector<Vector<Integer>> ans = permuteUnique(nums);

        for(Vector<Integer> v : ans){
            System.out.print("[ ");
            for(Integer i : v){
                System.out.print(i + " ");
            }
            System.out.println("]");
        }
    }
}
