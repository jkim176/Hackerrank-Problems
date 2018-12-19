import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// You are given an array and you need to find number of tripets of indices i, j, k such that the elements at those indices are in 
// geometric progression for a given common ratio r and i < j < k. 

public class CountTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        
        long count = 0;

        if(arr.size() < 3) 
            return count;
        else if(r == 1) {   
            Hashtable<Long, Long> ht = new Hashtable<>();

            for(long key: arr) {        // fill table: value, occurrences
                if(ht.containsKey(key))
                    ht.replace(key, ht.get(key) + 1);
                else
                    ht.put(key, 1L);
            }

            Set<Long> keys = ht.keySet();       // set of values in array

            for(long key: keys) {       // count triplet possibilities 
                if(ht.get(key) == 3)
                    count++;
                else if(ht.get(key) > 3) {
                    count += combination(ht.get(key), 3);
                }
            }
            return count;
        }
        else if(r == 0) {
            Hashtable<Long, Long> ht = new Hashtable<>();

            for(long key: arr) {
                if(ht.containsKey(key))
                    ht.replace(key, ht.get(key) + 1);
                else
                    ht.put(key, 1L);
            }

            Set<Long> keys = ht.keySet();

            if(ht.get(0L) < 2)
                return count;
            else {
                for(long key: keys) {
                    if(key == 0L && ht.get(key) > 2) {
                        count += combination(ht.get(key), 3);
                    }
                    else {
                        count += combination(ht.get(0L), 2) * ht.get(key);
                    }
                }
            }
            return count;
        }
        else if(r == -1) {
            Hashtable<Long, Long> ht = new Hashtable<>();

            for(long key: arr) {
                if(ht.containsKey(key))
                    ht.replace(key, ht.get(key) + 1);
                else
                    ht.put(key, 1L);
            }

            Set<Long> keys = ht.keySet();

            for(Long key: keys) {
                count += combination(ht.get(key), 2) * ht.get(key * -1);
            }
            return count;
        }
        else {
            Hashtable<Long, Long> ht = new Hashtable<>();

            for(long key: arr) {
                if(ht.containsKey(key))
                    ht.replace(key, ht.get(key) + 1);
                else
                    ht.put(key, 1L);
            }

            Set<Long> keys = ht.keySet();

            for(Long key: keys) {
                if(ht.containsKey(key * r) && ht.containsKey(key * r * r))
                    count += ht.get(key) * ht.get(key * r) * ht.get(key * r * r);
            }            
            return count;
        }
    }
    
    public static long combination(long n, long k) {
        long numerator = 1L;    // n!
        long denominator1 = 1L; // k!
        long denominator2 = 1L; // (n - k)!
        long stop;

        if(k >= (n - k)) {
            stop = k;
            
            while(n > stop) {
                numerator *= n;
                n--;
            }
            long count = n - k;
            while(count > 1) {
                denominator2 *= count;
                count--;
            }
            return numerator / denominator2;
        }
        else {
            stop = n - k;

            while(n > stop) {
                numerator *= n;
                n--;
            }
            long count = k;
            while(count > 1) {
                denominator1 *= count;
                count--;
            }
            return numerator / denominator1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
