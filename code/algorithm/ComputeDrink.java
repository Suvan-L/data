package jdk.concurrent;

/**
 * 【编程题】
 *      身上只有 10 块钱，一共可以喝多少瓶酒?
 *          - 2 块钱一瓶酒
 *          - 4 个瓶盖一瓶酒
 *          - 2 个空瓶换一瓶酒
 *
 *      答案：15 瓶
 *
 *  求解方式
 *      - 1. 计算酒数（循环解法）
 *      - 2. 计算酒数（递归解法）
 *
 * @author Suvan
 * @date 2018.01.26
 */
public class ComputeDrink {

    /**
     * 主函数
     */
    public static void main(String [] args) {
        int buyDrink = 5;

        //method 1
        int sumDrink = computeDrink1(buyDrink);
        System.out.println("【循环解法】10 块钱可以喝：" + sumDrink + " 瓶酒！");

        //method 2
        sumDrink = buyDrink + computeDrink2(buyDrink, buyDrink);
        System.out.println("【递归解法】10 块钱可以喝：" + sumDrink + " 瓶酒！");

    }

    /**
     * 计算酒数
     *      - 循环解法
     *
     * @param buyDrink 最初购买多少瓶酒
     * @return int 最后可以喝多少瓶酒
     */
    private static int computeDrink1(int buyDrink) {
        int sumDrink = buyDrink, cap = buyDrink, bottle = buyDrink;
        int newDrink = 0;

        while (cap >= 4 || bottle >= 2) {
            sumDrink += cap / 4 + bottle / 2;
            newDrink = cap / 4 + bottle / 2;

            cap = cap % 4 + newDrink;
            bottle = bottle % 2 + newDrink;
        }
        return sumDrink;
    }

    /**
     * 计算酒数
     *      - 递归解法
     *
     * @param x 瓶盖
     * @param y 空酒瓶
     * @return int 喝多少瓶酒
     */
    private static int computeDrink2(int x, int y) {
        if (x < 4 && y < 2) {
            return 0;
        }

        int newDrink = x / 4 + y / 2;
        System.out.println("此刻瓶盖 x = " + x + "，空酒瓶 y = " + y + ", 已置换 " + newDrink);
        return newDrink + computeDrink2(x % 4 + newDrink, y % 2 + newDrink);
    }
}
