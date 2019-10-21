package practice.algorithm.stack;

import practice.algorithm.empty.PersonEmpty;

/**
 * @Author: lvrongzhuan
 * @Description: 使用数组模拟栈
 * @Date: 2019/10/21 21:13
 * @Version: 1.0
 * modified by:
 */
public class ArrayStack<T> {
    // 定义栈大小
    private int maxSize;
    private Object[] arr;
    // 栈顶
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new Object[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 压栈
     *
     * @param t
     */
    public void pushStack(T t) {
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        arr[top] = t;
    }

    /**
     * 出栈
     */
    public <T> T popStack() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return null;
        }
        T t = (T) arr[top];
        top--;
        return t;
    }

    public void showStack() {
        for (int i = this.arr.length - 1; i >= 0; i--) {
            System.out.println("arr["+i+"]"+arr[i]);
        }
    }

    public static void main(String[] args) {
        ArrayStack<PersonEmpty> emptyArrayStack = new ArrayStack<>(6);
        emptyArrayStack.popStack();
        emptyArrayStack.pushStack(new PersonEmpty("1"));
        emptyArrayStack.pushStack(new PersonEmpty("2"));
        emptyArrayStack.pushStack(new PersonEmpty("3"));
        emptyArrayStack.pushStack(new PersonEmpty("4"));
        emptyArrayStack.pushStack(new PersonEmpty("5"));
        emptyArrayStack.pushStack(new PersonEmpty("6"));
        emptyArrayStack.pushStack(new PersonEmpty("7"));
        emptyArrayStack.showStack();
        emptyArrayStack.popStack();
        emptyArrayStack.popStack();
        emptyArrayStack.showStack();
    }
}
