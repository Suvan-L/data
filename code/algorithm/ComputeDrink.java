package jdk;

/**
 * 编程题：
 *     身上只有 10 块钱，一共可以喝多少瓶酒?
 *          - 2 块钱一瓶酒
 *          - 4 个瓶盖一瓶酒
 *          - 2 个空瓶换一瓶酒
 *
 * @author Suvan
 */
public class ComputeDrink {

    public static void main(String [] args) {
        int buyDrink = 5;
        int sumDrink = buyDrink + computeDrink(buyDrink, buyDrink);
        System.out.println("10 块钱可以喝：" + sumDrink + " 瓶酒！");
    }

    /**
     * 计算喝酒
     *      - 递归解法
     *
     * @param x 瓶盖
     * @param y 空酒瓶
     * @return int 喝多少瓶酒
     */
    private static int computeDrink(int x, int y) {
        if (x < 4 && y < 2) {
            return 0;
        }

        int newDrink = x / 4 + y / 2;
        System.out.println("此刻瓶盖 x = " + x + "，空酒瓶 y = " + y + ", 已置换 " + newDrink);
        return newDrink + computeDrink(x % 4 + newDrink, y % 2 + newDrink);
    }

    /**
     * 计算喝酒方法 2
     *      - 循环解法
     *
     * @param buyDrink 购买多少瓶酒
     */
    private void computeDrinkMethod2(int buyDrink) {
        int sumDrink = buyDrink, cap = buyDrink, bottle = buyDrink;
        int newDrink = 0;

        while (cap >= 4 || bottle >= 2) {
            sumDrink += cap / 4 + bottle / 2;
            newDrink = cap / 4 + bottle / 2;

            cap = cap % 4 + newDrink;
            bottle = bottle % 2 + newDrink;
        }

        System.out.println("10 块钱可以喝：" + sumDrink + " 瓶酒！");
    }
}
