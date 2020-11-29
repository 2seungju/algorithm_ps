import java.util.*;

class Solution {
    public int solution(String input) {
        int inputLength = input.length();

        int min = inputLength;
        for (int i = 1; i <= inputLength; i++) {
            Queue<String> queue = new LinkedList<>();

            for (int j = i; j <= inputLength; j+=i) {
                String slicedStr = input.substring(j - i, j);

                queue.offer(slicedStr);

                if (inputLength < j + i) {
                    queue.offer(input.substring(j));
                }
            }

            String previousStr = queue.poll();

            StringBuilder resultBuilder = new StringBuilder();

            int count = 1;

            while (!queue.isEmpty()) {
                String nextStr = queue.poll();

                if (previousStr.equals(nextStr)) {
                    count += 1;

                    continue;
                }

                resultBuilder.append(makeCompression(count, previousStr));

                if (queue.isEmpty()) {
                    resultBuilder.append(nextStr);
                }

                previousStr = nextStr;

                count = 1;
            }

            min = Math.min(min, resultBuilder.toString().length());
        }

        return min;
    }

    private String makeCompression(int count, String str) {
        return count == 1 ? str : count + str;
    }
}
