import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by zyt on 16/4/11.
 */
public class Problem2 {
    
    private static Map<String, Double> converter; // store the converter rules;
    
    public static String compute(String line) {
        String[] parts = line.split(" ");
        double sum = convert(parts[0], parts[1]);
        for (int i = 2; i < parts.length; i++) {
            if (parts[i].equals("+")) {
                sum += convert(parts[++i], parts[++i]);
            } else if (parts[i].equals("-")) {
                sum -= convert(parts[++i], parts[++i]);
            }
        }
        return String.format("%.2f", sum) + " m";
    }

    private static double convert(String numStr, String unitStr) {
        double num = Double.parseDouble(numStr);
        // transfer the unit to singular;
        switch (unitStr) {
            case "miles":
                unitStr = "mile";
                break;
            case "yards":
                unitStr = "yard";
                break;
            case "inches":
                unitStr = "inch";
                break;
            case "feet":
                unitStr = "foot";
                break;
            case "faths":
                unitStr = "fath";
                break;
            case "furlongs":
                unitStr = "furlong";
                break;
            default:
                break;
        }
        // transfer the number to 'meter' unit;
        return num * converter.get(unitStr);
    }

    public static void readRules(String line) {
        String[] parts = line.split(" ");
        // the converter rule has fixed format;
        converter.put(parts[1], Double.parseDouble(parts[3]));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        converter = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            // if the line is empty, then read the next line;
            if (line.length() == 0 || line.equals("")) continue;
            // if the line is a rule line, then store it;
            if (line.contains("=")) {
                readRules(line);
                continue;
            }
            // compute the answer by the given rules;
            System.out.println(compute(line));
        }
        scanner.close();
    }
}
