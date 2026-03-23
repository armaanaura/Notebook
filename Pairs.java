import java.util.Scanner;

public class Pairs {
    // Task 1: All pairs of elements of arr

    static void AllPairs(int[]arr){
        System.out.println("All Pairs :");
        for (int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                System.out.println("(" + arr[i] + ", " + arr[j] + ")");
            }
        }

    }

    // task 2 : No same element pair
    static void UniquePairs(int[]arr){
        System.out.println("Unique Pairs :" );
        for (int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if (arr[i] != arr[j]){
                    System.out.println("(" + arr[i] + ", " + arr[j] + ")");
                }
                
            }
        }
    }
    //Take input
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        AllPairs(arr);
        UniquePairs(arr);

        sc.close();

        
    }

}

