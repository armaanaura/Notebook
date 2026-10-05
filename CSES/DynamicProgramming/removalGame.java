import java.util.*;

public class removalGame {

    public static long tab(int[] nums){
        long[][]dp = new int[nums.length][nums.length];

    }
    
    public static long memo(int[]nums, int left, int right, long[][]dp){
        if(left==right)return nums[right];
        if(left>right)return 0;
        
        // I take left

        long takeLeft = nums[left] + Math.min(
            memo(nums, left + 2, right, dp),
            memo(nums, left + 1, right - 1, dp)
        );
        // I take right
        long takeRight = nums[right] + Math.min(
            memo(nums, left + 1, right - 1, dp),
            memo(nums, left, right - 2, dp)
        );
        return dp[left][right] = Math.max(takeLeft, takeRight);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[]nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        long[][]dp = new long[nums.length][nums.length];
        long answer = memo(nums,0, nums.length-1,dp);
        System.out.println(answer);
    }
}
