// 프로그래머스 괄호 변환
class Solution {
    fun solution(p: String): String {
        return recursiveV(p);
    }

    private fun reverseU(u: String): String {
        if (u.isBlank()) {
            return u;
        }

        val charArray = u.toCharArray();

        var returnValue = "";

        if (isCorrectBrace(charArray)) {
            returnValue = u;
        } else {
            for (char in charArray) {
                returnValue = when (char) {
                    '(' -> returnValue.plus(')')
                    ')' -> returnValue.plus('(')
                    else -> returnValue.plus(char);
                }
            }
        }

        return returnValue;
    }

    private fun recursiveV(v: String): String {
        var answer = "";

        val inputArray: CharArray = v.toCharArray();

        if (v.isBlank()) {
            return v;
        }

        val map: MutableMap<String, Int> = HashMap();
        var u = "";

        for (i in inputArray.indices) {
            val str: String = inputArray[i].toString();

            if (map[str] == null) {
                map[str] = 1;

            } else {
                map[str] = map[str]!! + 1;
            }

            u = u.plus(str);

            if (map["("] != null && map["("] == map[")"]) {
                val newV = v.substring(i + 1);

                answer = if (isCorrectBrace(u.toCharArray())) {
                    answer.plus(u + recursiveV(newV));
                } else {
                    val customU: String = u.substring(1, u.length - 1);
                    answer.plus("(${recursiveV(newV)})${reverseU(customU)}")
                }

                break;
            }
        }

        return answer;
    }

    private fun isCorrectBrace(charArray: CharArray): Boolean {
        return charArray[0] == '(' && charArray[charArray.size - 1] == ')';
    }
}