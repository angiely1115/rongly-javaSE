package practice.algorithm.sparse;

import lombok.Data;

/**
 * @Author: lvrongzhuan
 * @Description: 稀疏数组
 * @Date: 2019/10/14 20:52
 * @Version: 1.0
 * modified by:
 */
public class SparseArray {


    public static void main(String[] args) {
        int[][] chessArrays = new int[11][11];
        chessArrays[1][3] = 1;
        chessArrays[2][6] = 2;
        System.out.println("原始数组");
        // 稀疏数组行数
        int sparseRows = 0;
        for (int i = 0; i < chessArrays.length; i++) {
            for (int i1 = 0; i1 < chessArrays[i].length; i1++) {
                System.out.print(" "+chessArrays[i][i1]);
                if (chessArrays[i][i1]!=0) {
                    sparseRows++;
                }
            }
            System.out.println();
        }

        // 稀疏数组
        int[][] sparseArray = new int[sparseRows+1][3];
        sparseArray[0][0] = chessArrays.length;
        sparseArray[0][1] = chessArrays[0].length;
        sparseArray[0][2] = sparseRows;

        int count = 0;
        for (int i = 0; i < chessArrays.length; i++) {
            for (int i1 = 0; i1 < chessArrays[i].length; i1++) {
                if (chessArrays[i][i1] != 0) {
                    count ++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = i1;
                    sparseArray[count][2] = chessArrays[i][i1];
                }
            }
        }
        System.out.println("稀疏数组>>>>>>>>>>");
        for (int i = 0; i < sparseArray.length; i++) {
            for (int i1 : sparseArray[i]) {
                System.out.print(" "+i1);
            }
            System.out.println();
        }
        // 稀疏数组恢复到原始二维数组
        int[][] orgArrays = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            orgArrays[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println("稀疏数组--->原始二维数组");
        for (int[] orgArray : orgArrays) {
            for (int i : orgArray) {
                System.out.print(" "+i);
            }
            System.out.println();
        }
    }
    @Data
    public static class Checkerboard{
        /**
         * 黑子
         */
        private int black;
        /**
         * 白子
         */
        private int white;
    }
}
