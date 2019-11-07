package practice.algorithm.stack;

import com.google.common.base.Strings;

/**
 * @Author: lvrongzhuan
 * @Description: 通过栈实现一个简单的计算器表达式（中缀表达式）
 * @Date: 2019/10/22 21:42
 * @Version: 1.0
 * modified by:
 */
public class CalculatorStack {
    public static void main(String[] args) {
        String expression = "50*9-9*532";
        StringBuilder tempCh = new StringBuilder();
        ArrayStack<Integer> numberArrayStack = new ArrayStack<>(10);
        ArrayStack<String> operStack = new ArrayStack<>(10);
        int length = expression.toCharArray().length;
        int index = 0;
        for (char c : expression.toCharArray()) {
            index++;
            if (!isOper(c)) {
                tempCh.append(c);
            } else {
                if (!Strings.isNullOrEmpty(tempCh.toString())) {
                    numberArrayStack.pushStack(Integer.valueOf(tempCh.toString()));
                }
                tempCh = new StringBuilder();
                // 第一个符号直接入符号栈
                if (operStack.isEmpty()) {
                    operStack.pushStack(c + "");
                } else {
                    if (priority(c)<=priority(operStack.peek().charAt(0))) {
                       Integer num1 =  numberArrayStack.popStack();
                       Integer num2 = numberArrayStack.popStack();
                       numberArrayStack.pushStack((int) CalculatorEnum.calculation(num1,num2,operStack.popStack().charAt(0)));
                       operStack.pushStack(c + "");
                    }else{
                        operStack.pushStack(c + "");
                    }
                }

            }
            if (index == length) {
                numberArrayStack.pushStack(Integer.valueOf(tempCh.toString()));
            }
        }
        while (true){
            if (operStack.isEmpty()) {
                break;
            }
           Integer num1 =  numberArrayStack.popStack();
           Integer num2 = numberArrayStack.popStack();
           numberArrayStack.pushStack((int) CalculatorEnum.calculation(num1,num2,operStack.popStack().charAt(0)));
        }
        System.out.println("数字栈");
        numberArrayStack.showStack();
        System.out.println("操作符栈");
        operStack.showStack();
        System.out.printf("50*9-9*532=%s",numberArrayStack.peek()+"");
    }

    /**
     * 判断是不是炒作符号
     * @param ch
     * @return
     */
    public static boolean isOper(char ch) {
        return ch=='+'||ch=='-'||ch=='*'||ch=='/';
    }
    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public static int priority(char oper) {
        if(oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }else {
            return -1;
        }
    }
}
