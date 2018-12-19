import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/*
You are given an unordered array consisting of consecutive integers, without duplicates.  You are allowed to swap any two elements. 
You need to find the minimum number of swaps required to sort the array in ascending order.
*/

public class MinimumSwaps {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {

        int counter = 0;
        int temp = 0;
        for(int i = 0; i < arr.length; i++) {

            if(arr[i] != i + 1) {
                temp = arr[i];

                for(int j = i + 1; j < arr.length; j++) {

                    if(arr[j] == i + 1) {
                        arr[i] = i + 1;
                        arr[j] = temp;
                        break;
                    }
                }
                counter++;
            }
        }
        return counter;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
