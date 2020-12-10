import java.util.*;
import java.util.stream.Collectors;

class Solution {
    // a ~ z -> 97 ~ 122
    public int solution(String str1, String str2) {
        List<String> list1 = stringSet(str1.toLowerCase());
        List<String> list2 = stringSet(str2.toLowerCase());

        list1 = list1.stream().filter(Solution::isAlphabetSet).collect(Collectors.toList());
        list2 = list2.stream().filter(Solution::isAlphabetSet).collect(Collectors.toList());

        boolean[] check1 = new boolean[list1.size()];
        boolean[] check2 = new boolean[list2.size()];



        int count = 0;

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (list1.get(i).equals(list2.get(j))) {
                    if (isAlphabetSet(list1.get(i)) && check1[i] == false && check2[j] == false){
                        check1[i] = true;
                        check2[j] = true;
                        count++;
                    }
                }
            }
        }

        int sum = list1.size() + list2.size() - count;

        float result = 0;
        if (sum != 0) {
            result = (float) count / sum * 65536;
        } else {
            result = 65536;
        }

        return (int) result;
    }

    private static boolean isAlphabetSet(String str) {
        for (char c : str.toCharArray()) {
            if ((int) c < 97 || (int) c > 122) {
                return false;
            }
        }

        return true;
    }

    private List<String> stringSet(String str) {
        String[] strings = str.split("");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length - 1; i++) {
            list.add(strings[i] + strings[i+ 1]);
        }

        return list;
    }
}
