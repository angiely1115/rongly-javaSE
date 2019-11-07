package practice.algorithm.stack;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description: 后缀表达式计算器
 * @Date: 2019/10/23 21:10
 * @Version: 1.0
 * modified by:
 */
public class PostfixExpressionCal {
    /**
     * 对一个后缀表达式计算
     * @param postfixExpress
     * @return
     */
    public static Integer cal(List<String> postfixExpress) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(postfixExpress.size());
        for (String express : postfixExpress) {
            if (!CalculatorStack.isOper(express.charAt(0))) {
                arrayStack.pushStack(Integer.valueOf(express));
            } else {
                int num1 = arrayStack.popStack();
                int num2 = arrayStack.popStack();
                arrayStack.pushStack((int)CalculatorEnum.calculation(num1,num2,express.charAt(0)));
            }
        }

        return arrayStack.popStack();
    }

    /**
     * 将中缀表达式转后缀表达式
     * @param infixExpress
     * @return
     */
    public static List<String> infixexpressionToPostfix(String infixExpress) {
        int length = infixExpress.length();
        List<String> postfixs = Lists.newArrayListWithCapacity(length);
        ArrayStack<String> arrayStack1 = new ArrayStack<>(length);
        ArrayStack<String> arrayStack2 = new ArrayStack<>(length);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (char c : infixExpress.toCharArray()) {
            if (CalculatorStack.isOper(c) || '(' == c || ')' == c) {
                if (StringUtils.isNotBlank(sb.toString())) {
                    arrayStack2.pushStack(sb.toString());
                    sb = new StringBuilder();
                }
                if (arrayStack1.isEmpty()||'(' == arrayStack1.peek().charAt(0)) {
                    arrayStack1.pushStack(c + "");
                }else {
                    if(c==')'){
                        while (true) {
                            String oper =  arrayStack1.popStack();
                            if ("(".equals(oper)) {
                                break;
                            }
                            arrayStack2.pushStack(oper);
                        }

                    }
                    else  {
                        while (!arrayStack1.isEmpty()&&CalculatorStack.priority(c)<CalculatorStack.priority(arrayStack1.peek().charAt(0))) {
                            arrayStack2.pushStack(arrayStack1.popStack());
                        }
                        arrayStack1.pushStack(c + "");
                    }

                }
            } else {
                sb.append(c);
            }
            index++;
            if (index == length) {
                arrayStack2.pushStack(c+"");
            }
        }
        while (true) {
            if (arrayStack1.isEmpty()) {
                break;
            }
            arrayStack2.pushStack(arrayStack1.popStack());
        }
        while (true) {
            if (arrayStack2.isEmpty()) {
                break;
            }
            postfixs.add(arrayStack2.popStack());
        }
        return Lists.reverse(postfixs);
    }
    public static void main(String[] args) {
        System.out.println("(3+4)*5-6="+cal(Lists.newArrayList("3","4","+","5","*","6","-")));
        System.out.println(infixexpressionToPostfix("(30+4)*50-6+4"));
        System.out.println("递归使用----------");
    }





}
