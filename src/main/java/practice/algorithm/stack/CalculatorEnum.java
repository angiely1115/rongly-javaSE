package practice.algorithm.stack;

/**
 * @Author: lvrongzhuan
 * @Description: 枚举计算器
 * @Date: 2019/10/22 21:43
 * @Version: 1.0
 * modified by:
 */
public enum CalculatorEnum {
    /**
     * 加法
     */
    ADD('+'){
        @Override
        double exce(double num1, double num2, char opr) {
            return num1+num2;
        }
    },
    SUB('-'){
        @Override
        double exce(double num1, double num2, char opr) {
            return num2-num1;
        }
    },
    MULTIPLY('*'){
        @Override
        double exce(double num1, double num2, char opr) {
            return num1*num2;
        }
    },
    DIVIDE('/'){
        @Override
        double exce(double num1, double num2, char opr) {
            return num1/num2;
        }
    },

    ;
    private char opr;

    CalculatorEnum(char opr) {
        this.opr = opr;
    }


    abstract double exce(double num1,double num2,char opr);

    /**
     * 计算
     * @param num1
     * @param num2
     * @param opr
     * @return
     */
    public static double calculation(double num1,double num2,char opr) {
        for (CalculatorEnum value : CalculatorEnum.values()) {
            if (value.opr==opr) {
                return value.exce(num1,num2,opr);
            }
        }
        return 0;
    }
}
