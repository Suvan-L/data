package jdk.lang;

/**
 * 递增求和
 *      【编程题】 1 ~ 100 求和？
 *
 *  主要四种思路
 *      1. 循环求和
 *      2. 递归求和
 *      3. 二进制求和
 *      4. 等差数列公式公式求和
 *
 * @author Suvan
 * @date 2018.02.06
 */
public class IncreaseSummation {

    /**
     * 主函数
     */
    public static void main(String [] arge) {
        System.out.println("循环求和：" + sum(100));
        System.out.println("递归求和：" + sumByRecursive(100));
        System.out.println("二进制求和：" + sumByBinary(100));
        System.out.println("等差数列公式求和：" + sumByFormula(100));
    }

    /**
     * 求和
     *      - 指定目标数，执行 1 ~ aimNumber 求和
     *      - 三种循环方式
     *          1. for 循环
     *          2. while 循环
     *          3. do-while 循环
     *          4. 前后指针 while 循环（空间增加，循环次数减半）
     *
     * @param aimNumber 目标数字
     * @return int 求和结果
     */
    private static int sum (int aimNumber) {
        int sumResult = 0;

        //foreach method 1
        for (int i = 1; i <= aimNumber; i++) {
            sumResult += i;
        }
        //System.out.println("foreach method 1: sumResult = " + sumResult);

        //foreach method 2
        sumResult = 0;
        int pointNumber = 1;
        while (pointNumber <= aimNumber) {
            sumResult += pointNumber;
            pointNumber ++;
        }
        //System.out.println("foreach method 2: sumResult = " + sumResult);

        //foreach method 3
        sumResult = 0;
        pointNumber = 1;
        do {
            sumResult += pointNumber;
        } while (++ pointNumber <= aimNumber);
        //System.out.println("foreach method 3: sumResult = " + sumResult);

        //foreach method 4
        sumResult = 0;
        int frontPoint = 0, backPoint = 101;
        while ( ++ frontPoint <= -- backPoint) {
            sumResult += (frontPoint + backPoint);
        }
        //System.out.println("foreach method 4: sumResult = " + sumResult);

        return sumResult;
    }

    /**
     * 递归求和
     *      - 指定目标数，执行 1 ~ aimNumber 求和
     *      - 如果不使用 if，可考虑使用 try-catch 块
     *
     * @param aimNumber 目标数字
     * @return int 求和结果
     */
    private static int sumByRecursive (int aimNumber) {
        if (aimNumber == 1) {
            return 1;
        }

        //if you do not use if
        //try {
        //    int [] array = new int[aimNumber - 2];
        //} catch (NegativeArraySizeException ne) {
        //   return 1;
        //}

        return aimNumber + sumByRecursive(-- aimNumber);
    }

    /**
     * 二进制求和
     *      - 指定目标数，执行 1 ~ aimNumber 求和
     *      - 实现位运算加法器，提升效率
     *
     * @param aimNumber 目标数字
     * @return int 求和结果
     */
    private static int sumByBinary(int aimNumber) {
        int sumResult = 0;
        for (int i = 1 ; i <= aimNumber; i ++) {
            //use binary addition
            sumResult = add(sumResult, i);
        }

        return sumResult;
    }

    /**
     * 加法器
     *      - 位运算模拟实现
     *      - 二进制加法口诀：同 1 进 1，有 1 为 1, 同 0 为 0
     *      - 思路
     *          1. x ^ y 取得“得位”
     *          2. (x & y) << 取"进位"
     *          3. 递归计算，当“进位” == 0 时，得位为最终求值结果
     *
     * @param x 加数1
     * @param y 加数2
     * @return int 两数相加结果
     */
    private static int add(int x, int y) {
        if (y == 0) {
            return x;
        }

        int sum = x ^ y;
        int into = (x & y) << 1;
        return add(sum, into);
    }

    /**
     * 等差数列公式求和
     *      - 指定目标数，执行 1 ~ aimNumber 求和
     *      - 等差数列求和公式: (n + 1) * n / 2
     *
     * @param aimNumber 目标数字
     * @return int 求和结果
     */
    private static int sumByFormula(int aimNumber) {
        //return (aimNumber + 1) * aimNumber / 2;
        return (aimNumber ++ * aimNumber) >> 1;
    }
}
