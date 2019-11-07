package practice.algorithm.recursion;

/**
 * @Author: lvrongzhuan
 * @Description: 递归
 * @Date: 2019/10/27 20:14
 * @Version: 1.0
 * modified by:
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(6);
        System.out.println(factorial(3));
        int[][] ints = maze();
        ints[1][1] = 1;
        ints[2][1] = 1;
        print(ints);
        goMaze(ints,2,2);
        System.out.println("走迷宫后地图");
        print(ints);
    }

    /**
     * 走迷宫
     * @param mazeMap
     * @param i
     * @param j
     */
    public static boolean goMaze(int[][] mazeMap,int i,int j){
        System.out.println("i:"+i+"j:"+j);
        if (mazeMap[4][5]==2){
            System.out.println("恭喜您，走出迷宫");
            return true;
        }

        // 走迷宫策略 上->左->下->右
        else if(mazeMap[i][j]==0){
            mazeMap[i][j] = 2;
            if (goMaze(mazeMap,i-1,j)){
                return true;
            }else if(goMaze(mazeMap,i,j-1)){
                return true;
            }else if (goMaze(mazeMap,i+1,j)){
                return true;
            }else if (goMaze(mazeMap,i,j+1)){
                return true;
            }else {
                mazeMap[i][j]=3;
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 打印
     * @param ints
     */
    public static void print(int[][] ints) {
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }


    public static void test(int n){
        System.out.println(n);
        if (n>2) {
            test(n-1);
        }

    }

    public static int factorial(int n) {
        if (n==1) {
            return 1;
        }
        return factorial(n-1)*n;
    }

    /**
     * 定义的一个迷宫，二维数组 1表示强，0表示通路
     * @return
     */
    public static int[][] maze(){

        int[][] mazesArray = new int[6][7];
        // 初始化墙 上下
        for(int i=0;i<7;i++){
            mazesArray[0][i] = 1;
            mazesArray[5][i] = 1;
        }
        // 左右
        for(int i=1;i<5;i++){
            mazesArray[i][0] = 1;
            mazesArray[i][6] = 1;
        }
        return mazesArray;
    }
}
