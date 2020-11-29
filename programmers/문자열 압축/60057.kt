import java.util.*

// 프로그래머스 문자열 압축
class Solution {
    fun solution(input: String): Int{
        val inputLength = input.length;

        var min = inputLength;
        for (i in 1..inputLength) {
            val queue: Queue<String> = LinkedList();

            for (j in i..inputLength step i) {
                val slicedStr: String = input.substring(j - i, j);

                queue.offer(slicedStr);

                if (inputLength < j + i) {
                    queue.offer(input.substring(j));
                }
            }

            var result = "";
            var previousStr = queue.poll();

            var count = 1;

            while (!queue.isEmpty()) {
                val nextStr = queue.poll();

                if (previousStr == nextStr) {
                    count += 1;

                    continue;
                }

                result += makeCompression(count, previousStr);

                if (queue.isEmpty()) {
                    result += nextStr;
                }

                previousStr = nextStr;

                count = 1;
            }

            min = min.coerceAtMost(result.length);
        }

        return min;
    }

    private fun makeCompression(count: Int, str: String): String {
        return if (count == 1) {
            str;

        } else {
            count.toString() + str;
        }
    }
}