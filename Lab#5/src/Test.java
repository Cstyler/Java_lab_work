/**
 * Created by khan on 17.03.16. Lab5
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        HashMap<Integer, String> a = new HashMap<>();
        a.put(1, "Java");
        a.put(3, "Java");
        a.put(12, "Generics");
        a.put(2016, "Interface");
        a.put(2021, "Class");
        System.out.println(inverse(a));
    }

    private static Map<String, Integer> inverse(HashMap<Integer, String> a) {
        Map<String, List<Integer>> map = a.keySet().stream()
                .collect(Collectors.groupingBy(a::get));
        List<String> newValues = map.keySet().stream()
                .filter(x -> map.get(x).size() == 1)
                .collect(Collectors.toList());
        List<Integer> newKeys = a.keySet().stream()
                .filter(x -> newValues.contains(a.get(x)))
                .collect(Collectors.toList());
        a.keySet().removeIf(x -> !newKeys.contains(x));
        return a.keySet().stream()
                .collect(Collectors.toMap(a::get, Function.identity()));
    }
}