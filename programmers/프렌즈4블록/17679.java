import java.util.*;

class Solution {
    public static final int[] X = {1, 0, 1, 0};
    public static final int[] Y = {0, 1, 1, 0};
    public static int count = 0;

    public int solution(int m, int n, String[] board) {
        String[][] array = new String[m][n];

        for (int i = 0; i < m; i++) {
            String[] strings = board[i].split("");
            for (int j = 0; j < n; j++) {
                array[i][j] = strings[j];
            }
        }

        deleteBlock(m, n, array);

        return count;
    }

    public String[][] deleteBlock(int m, int n, String[][] array) {
        boolean[][] booleans = new boolean[m][n];
        boolean isRecursive = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (array[i][j] == null) {
                    continue;
                }

                boolean check = true;
                for (int k = 0; k < X.length; k++) {
                    if (i + X[k] >= m || j + Y[k] >= n) {
                        check = false;
                        break;
                    }

                    if (!array[i][j].equals(array[i + X[k]][j + Y[k]])) {
                        check = false;
                    }
                }

                if (check) {
                    for (int k = 0; k < X.length; k++) {
                        if (!booleans[i + X[k]][j + Y[k]]) {
                            booleans[i + X[k]][j + Y[k]] = true;
                            count++;
                        }
                    }
                }

            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (booleans[i][j]) {
                    isRecursive = true;
                    array[i][j] = null;
                }
            }
        }

        return isRecursive ? deleteBlock(m, n, filledBlock(m, n, array)) : array;
    }

    public String[][] filledBlock(int m, int n, String[][] array) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (array[j][i] != null) {
                    stack.push(array[j][i]);
                }

                array[j][i] = null;
            }

            int index = m - 1;

            while (!stack.isEmpty()) {
                String str = stack.pop();

                if (array[index][i] == null) {
                    array[index--][i] = str;
                }
            }
        }

        return array;
    }
}
