import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAnagrams {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        
        // table contains <unique anagram substrings, occurences>
        Hashtable<String, Integer> finalTable = new Hashtable<>();
        // temp table for adding substrings of one particular length to final table
        Hashtable<String, Integer> temp = new Hashtable<>();
        int substringLength = 1;
        int anagramCount = 0;
        
        while(substringLength < s.length()) {
            // tempAlphabet: stores all unique alphabet htables of substrings of a particular substringlength 
            Hashtable<String, Hashtable<Character, Integer>> tempAlphabet = new Hashtable<>();
            for(int i = 0; i < s.length() - substringLength + 1; i++) {
                
                if(substringLength == 1) {
                    
                    if(temp.containsKey(Character.toString(s.charAt(i)))) {
                        temp.replace(Character.toString(s.charAt(i)), temp.get(Character.toString(s.charAt(i))) + 1);
                    }
                    else {
                        temp.put(Character.toString(s.charAt(i)), 1);
                    }
                }
                else {  // substring length > 1
                    String sub = s.substring(i, i + substringLength);
                    Hashtable<Character, Integer> subAlphabet = new Hashtable<>();
                    for(int j = 0; j < sub.length(); j++) {
                        if(subAlphabet.containsKey(sub.charAt(j))) {
                            subAlphabet.replace(sub.charAt(j), subAlphabet.get(sub.charAt(j)) + 1);
                        }
                        else {
                            subAlphabet.put(sub.charAt(j), 1);
                        }
                    }
                    if(tempAlphabet.isEmpty()) {
                        tempAlphabet.put(sub, subAlphabet);
                        temp.put(sub, 1);
                    }
                    else {  // check if anagram
                        boolean found = false;
                        for(String key: tempAlphabet.keySet()) {
                            if(subAlphabet.equals(tempAlphabet.get(key))) {
                                temp.replace(key, temp.get(key) + 1);
                                found = true;
                                break;
                            }
                        }
                        if(!found) {
                            temp.put(sub, 1);
                        }
                    }
                }
            }
            finalTable.putAll(temp);
            temp.clear();
            tempAlphabet.clear();
            substringLength++;
        }
        for(String key: finalTable.keySet()) {
            if(finalTable.get(key) > 1) {
                int n = finalTable.get(key);
                anagramCount += (n * n - n)/ 2;
            }
        }
        return anagramCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
