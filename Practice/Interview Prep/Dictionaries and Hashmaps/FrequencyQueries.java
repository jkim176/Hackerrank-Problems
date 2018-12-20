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

public class FrequencyQueries {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        // <Integer, occurence count>
        Hashtable<Integer, Integer> table = new Hashtable<>();
        Iterator<List<Integer>> it = queries.iterator();
        List<Integer> ans = new ArrayList<>();

        while(it.hasNext()) {
            List<Integer> cur = it.next();
            int query = cur.get(0);
            int key = cur.get(1);
            if(query == 1) {
                if(table.containsKey(key)) {
                    table.replace(key, table.get(key) + 1);
                }
                else {
                    table.put(key, 1);
                }
            }
            else if(query == 2) {
                if(table.containsKey(key) && table.get(key) > 0) {
                    table.replace(key, table.get(key) - 1);
                }
            }
            else {  // query == 3
                if(table.containsValue(key))    // key is occurrences
                    ans.add(1);
                else
                    ans.add(0);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
