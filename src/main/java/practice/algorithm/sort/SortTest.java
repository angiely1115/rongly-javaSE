package practice.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @Author: lvrongzhuan
 * @Description: 排序算法测试
 * @Date: 2019/2/25 10:28
 * @Version: 1.0
 * modified by:
 */
public class SortTest {
    private int[] ints = {1,3,5,2,8,6,10,9,99,32,11,26,88};

    /**
     * 选择排序
     */
    public int[] selectSort(boolean asce) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                int a = ints[i];
                int b = ints[j];
                if (asce && a > b) {
                    ints[i] = b;
                    ints[j] = a;
                } else if (!asce && a < b) {
                    ints[i] = a;
                    ints[j] = b;
                }
            }
        }
        return ints;
    }

    /**
     * 冒泡排序
     */
    public int[] bubbleSort(boolean asce) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length-i-1; j++) {
                int a = ints[j];
                int b = ints[j+1];
                if (asce && a > b) {
                    ints[j] = b;
                    ints[j+1] = a;
                } else if (!asce && a < b) {
                    ints[j] = a;
                    ints[j+1] = b;
                }
            }
        }
        return ints;
    }

    /**
     * 测试冒泡排序
     */
    @Test
    public void testBubbleSort(){
        int[] ints1 = this.bubbleSort(false);
        for (int i = 0; i < ints1.length; i++) {
            System.out.println(ints1[i]);
        }
    }

    public int binarySearch(int[] ints, int value,int low,int high) {
        if (ints==null||ints.length==0) {
            return -1;
        }
        if (low > high) {
            return -1;
        }
        int binary = low+(high-low) /2;

        if (ints[binary] == value) {
            return binary;
        }
        if (ints[binary] > value) {
            return binarySearch(ints, value, low,binary-1);
        }
        if (ints[binary] < value) {
            return binarySearch(ints, value, binary+1,high);
        }
        return -1;
    }

    /**
     * 二分法查找，查找之前要先排序
     */
    @Test
    public void testBinarySearch() {
        int[]  ints1 = this.bubbleSort(true);
        int index = this.binarySearch(ints1,0,0,ints1.length-1);
        System.out.println("目标值的位置:"+index);
    }

    @Test
    public void testABAA() {
        String str = "ABAAABBBAAAAABBBB";
        int length = str.length();
        char a = 'A';
        char b = 'B';
        int acount = 0;
        int bcount = 0;
        int index = 0;
        int[] arraya = new int[length+1];
        int[] arrayb = new int[length+1];
        char temp ;
        for (int i = 0; i < length; i++) {
            temp = str.charAt(i);
            if (a==temp) {
                acount++;
                arraya[i+1]=a;
            }
            if (b==temp) {
                bcount++;
                arrayb[i+1]=b;
            }
            /*if (acount == bcount) {
                index = i;
            }*/
        }
        int res = a>b?a:b;

        String s = str.substring(0,index+1);
        System.out.println("字符串:s"+s+"字符串长度:"+s.length());
    }


}
