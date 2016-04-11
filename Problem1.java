import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zyt on 16/4/10.
 */
public class Problem1 {

    public static String topoSort(List<Integer>[] graph) {
        StringBuilder sequence = new StringBuilder(); // the result sequence;
        int[] degree = new int[graph.length]; // the in-degree of every vertex;
        // count the in-degree for every vertex;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                int index = graph[i].get(j);
                degree[index]++;
            }
        }
        // do the topological sort, that is, find the 0 in-degree vertex every time
        // and then relax its edges to other vertex until every vertex is found;
        int count = 0;
        while (count < graph.length) {
            for (int i = 0; i < degree.length; i++) {
                // find a vertex whose in-degree is 0;
                if (degree[i] == 0) {
                    sequence.append((i + 1) + " ");
                    for (int j = 0; j < graph[i].size(); j++) {
                        int index = graph[i].get(j);
                        degree[index]--;
                    }
                    degree[i] = -1;
                    count++;
                    break;
                }
                // if there is no vertex suitable, then return error;
                if (i == degree.length - 1)
                    return "error";
            }
        }
        // return the formatted result sequence;
        return sequence.deleteCharAt(sequence.length() - 1).toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // read the input data;
            int n = scanner.nextInt();
            int m = scanner.nextInt(); // add an additional input m;
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int a = scanner.nextInt() - 1;
                int b = scanner.nextInt() - 1;
                graph[b].add(a);
            }
            // get the result sequence by topological sort;
            System.out.println(topoSort(graph));
        }
        scanner.close();
    }
}
