package practice.algorithm.mylinked;

public  class Node<E> {
        E e;
        Node<E> next;
        Node<E> pre;

        public Node(E e) {
            this.e = e;
        }

    public Node(E e, Node<E> next, Node<E> pre) {
        this.e = e;
        this.next = next;
    }
}