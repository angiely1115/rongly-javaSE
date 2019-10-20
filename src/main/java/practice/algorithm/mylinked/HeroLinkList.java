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
        Node<E> eNode = new Node<>(e,first,null);
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
            System.out.println(e);
            e  = e.next;
            if (e == first) {
                break;
            }
        }
    }
    public static void main(String[] args) {
        HeroLinkList<Integer> integerHeroLinkList = new HeroLinkList<>();
        integerHeroLinkList.add(1);
        integerHeroLinkList.add(2);
        integerHeroLinkList.add(3);
//        integerHeroLinkList.printList();
        // 约瑟夫环
        integerHeroLinkList = new HeroLinkList<>();
        integerHeroLinkList.addCircle(3);
        integerHeroLinkList.addCircle(4);
        integerHeroLinkList.addCircle(5);
        integerHeroLinkList.YesefuPrint();
    }



}
