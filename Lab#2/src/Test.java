
/**
 * Created by khan on 11.03.16. Lab#2
 */

import java.util.Arrays;
import java.util.Scanner;

class Test {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(), m = scn.nextInt();
        boolean[][] matrix = new boolean[n][n];
        for (boolean[] a :
                matrix) {
            Arrays.fill(a, false);
        }
        for (int k = 0; k < m; k++) {
            int i = scn.nextInt(), j = scn.nextInt();
            matrix[i][j] = true;
            matrix[j][i] = true;
        }
        Graph g = new Graph(matrix);
        for (Integer i :
                g) {
            System.out.printf("%c ", (char) ('a' + i));
        }
        System.out.println();
    }
}

//10
//        10
//        0 1
//        0 2
//        0 3
//        1 2
//        2 4
//        2 5
//        5 6
//        6 3
//        3 7
//        8 9