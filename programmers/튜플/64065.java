import java.util.*;
import java.util.stream.Collectors;

// 프로그래머스 튜플
class Solution {
    public int[] solution(String s) {
        String[] strArray = s.substring(1, s.length() - 1).split("");

        List<String> tupleList = new ArrayList<>();

        String tuple = "";
        for (String str : strArray) {
            tuple += str;

            if (str.equals("}")) {
                if (tuple.charAt(0) == ',') {
                    tupleList.add(tuple.substring(1));

                } else {
                    tupleList.add(tuple);
                }

                tuple = "";
            }
        }

        tupleList = tupleList.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

        Map<String, Boolean> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (String str : tupleList) {
            String[] tempArray = str.substring(1, str.length() - 1).split(",");

            for (String temp : tempArray) {
                if (map.get(temp) == null) {
                    map.put(temp, true);

                    result.add(Integer.parseInt(temp));
                }
            }

        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
