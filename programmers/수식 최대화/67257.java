import java.util.*;

class Solution {
    public static final char[][] OPERATOR = {
            {'*', '+', '-'},
            {'*', '-', '+'},
            {'+', '*', '-'},
            {'+', '-', '*'},
            {'-', '+', '*'},
            {'-', '*', '+'}
    };

    public long solution(String input) {
        long answer = 0;

        char[] inputArray = input.toCharArray();

        StringBuilder number = new StringBuilder();

        List<Character> operators = new LinkedList<>();
        List<Long> numbers = new LinkedList<>();

        for (char c : inputArray) {
            if (c == '*' || c == '+' || c == '-') {
                numbers.add(Long.parseLong(number.toString()));
                operators.add(c);

                number = new StringBuilder();

            } else {
                number.append(c);
            }
        }
        numbers.add(Long.parseLong(number.toString()));

        for (char[] chars : OPERATOR) {
            List<Character> tempOperators = (LinkedList) ((LinkedList) operators).clone();
            List<Long> tempNumbers = (LinkedList) ((LinkedList) numbers).clone();

            for (char c : chars) {

                while (tempOperators.contains(c)) {
                    int index = tempOperators.indexOf(c);

                    tempNumbers.add(index, calculator(tempNumbers.get(index), tempNumbers.get(index + 1), c));

                    tempNumbers.remove(index + 1);
                    tempNumbers.remove(index + 1);
                    tempOperators.remove(index);
                }
            }

            answer = Math.max(answer, Math.abs(tempNumbers.get(0)));
        }

        return answer;
    }

    private long calculator(long number1, long number2, char operator) {
        long result = 0;
        switch (operator) {
            case '+':
                result += number1 + number2;
                break;

            case '-':
                result += number1 - number2;
                break;

            case '*':
                result += number1 * number2;
                break;
        }

        return result;
    }
}
