import java.util.*;

// 프로그래머스 괄호 변환
class Solution {
    public String solution(String input) {
        return recursiveV(input);
    }

    private String reverseU(String u) {
        String[] array = u.split("");

        String returnValue = "";
        if (isCorrectBrace(array)) {
            returnValue = u;

        } else {
            for (String str : array) {
                if (str.equals("(")) {
                    returnValue += ")";

                } else if (str.equals(")")){
                    returnValue += "(";
                }
            }
        }

        return returnValue;
    }

    private String recursiveV(String v) {
        if (v.isEmpty()) {
            return v;
        }

        String answer = "";
        String u = "";

        String[] inputArray = v.split("");

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < inputArray.length; i++) {
            String str = inputArray[i];
            map.merge(str, 1, Integer::sum);

            u += str;

            if (map.get("(") != null && map.get("(").equals(map.get(")"))) {
                String newV = v.substring(i + 1);

                // 왜 += 연산이 필요한가?
                if (isCorrectBrace(u.split(""))) {
                    answer += u + recursiveV(newV);

                } else {
                    String slicedU = u.substring(1, u.length() - 1);

                    answer += "(" + recursiveV(newV) + ")" + reverseU(slicedU);
                }

                break;
            }
        }

        return answer;
    }

    private boolean isCorrectBrace(String[] strings) {
        return strings[0].equals("(") && strings[strings.length - 1].equals(")");
    }
}
