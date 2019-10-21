package practice.algorithm.mylinked;

/**
 * @Author: lvrongzhuan
 * @Description: 英雄列表
 * @Date: 2019/10/18 22:04
 * @Version: 1.0
 * modified by:
 */
public class HeroLinkList<E> {

    private Node<E> first;
    private Node<E> last;


    /**
     * 单链表
     * @param e
     */
    public void add(E e) {
        Node<E> f = last;
        Node<E> eNode = new Node<>(e);
        last = eNode;
        if (f == null) {
            first = eNode;
        }else {
            f.next = eNode;
        }
    }

    /**
     * 最后一个数值会指向第一个
     * @param e
     */
    public void addCircle(E e) {
        Node<E> f = last;
        Node<E> eNode = new Node<>(e,first,null);
        last = eNode;
        if (f == null) {
            first = eNode;
        }else {
            f.next = eNode;
        }
    }

    /**
     * 单向列表打印
     */
    public void printList() {
        while (first!= null) {
            System.out.println(first);
            first = first.next;
        }
    }

    /**
     * 约瑟夫列表打印
     */

    public void YesefuPrint() {
        Node e  = first;
        while (true) {
            System.out.println(e.e);
            e  = e.next;
            if (e == first) {
                break;
            }
        }
    }

    /**
     * 约瑟夫出圈处理方式
     * @param k 从第几个人开始数数
     * @param m 数到几出圈
     * @param sum 总人数
     */
    public void 丢手绢出圈(int k,int m,int sum) {
        if (k > sum) {
            System.err.println("输入错误");
            return;
        }
        Node<E> tempNode = first;
        // 第一步将临时辅助节点移动到最后一个节点
        while (true) {
            if (tempNode.next == first) {
                break;
            }
            tempNode = tempNode.next;
        }
        // 将第一个节点和辅助节点移动开始数数节点-1次
        for (int i = 1; i < k; i++) {
            first = first.next;
            tempNode = tempNode.next;
        }
        while (true) {
            if (tempNode == first) {
                System.out.printf("未出圈元素%d\n", first.e);
                break;
            }
            // 数到几出圈
            for (int i = 1; i < m; i++) {
                first = first.next;
                tempNode = tempNode.next;
            }
            System.out.printf("元素%d出圈\n",first.e);
            first = first.next;
            tempNode.next = first;
        }


    }
    public static void main(String[] args) {
        HeroLinkList<Integer> integerHeroLinkList = new HeroLinkList<>();
        integerHeroLinkList.add(1);
        integerHeroLinkList.add(2);
        integerHeroLinkList.add(3);
        integerHeroLinkList.printList();
        // 约瑟夫环
        integerHeroLinkList = new HeroLinkList<>();
        integerHeroLinkList.addCircle(1);
        integerHeroLinkList.addCircle(2);
        integerHeroLinkList.addCircle(3);
        integerHeroLinkList.addCircle(4);
        integerHeroLinkList.addCircle(5);
        integerHeroLinkList.addCircle(6);
        integerHeroLinkList.YesefuPrint();

        integerHeroLinkList.丢手绢出圈(3,3,6);
    }



}
