package jdk.concurrent;

/**
 * ������⡿
 *      ����ֻ�� 10 ��Ǯ��һ�����Ժȶ���ƿ��?
 *          - 2 ��Ǯһƿ��
 *          - 4 ��ƿ��һƿ��
 *          - 2 ����ƿ��һƿ��
 *
 *      �𰸣�15 ƿ
 *
 *  ��ⷽʽ
 *      - 1. ���������ѭ���ⷨ��
 *      - 2. ����������ݹ�ⷨ��
 *
 * @author Suvan
 * @date 2018.01.26
 */
public class ComputeDrink {

    /**
     * ������
     */
    public static void main(String [] args) {
        int buyDrink = 5;

        //method 1
        int sumDrink = computeDrink1(buyDrink);
        System.out.println("��ѭ���ⷨ��10 ��Ǯ���Ժȣ�" + sumDrink + " ƿ�ƣ�");

        //method 2
        sumDrink = buyDrink + computeDrink2(buyDrink, buyDrink);
        System.out.println("���ݹ�ⷨ��10 ��Ǯ���Ժȣ�" + sumDrink + " ƿ�ƣ�");

    }

    /**
     * �������
     *      - ѭ���ⷨ
     *
     * @param buyDrink ����������ƿ��
     * @return int �����Ժȶ���ƿ��
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
     * �������
     *      - �ݹ�ⷨ
     *
     * @param x ƿ��
     * @param y �վ�ƿ
     * @return int �ȶ���ƿ��
     */
    private static int computeDrink2(int x, int y) {
        if (x < 4 && y < 2) {
            return 0;
        }

        int newDrink = x / 4 + y / 2;
        System.out.println("�˿�ƿ�� x = " + x + "���վ�ƿ y = " + y + ", ���û� " + newDrink);
        return newDrink + computeDrink2(x % 4 + newDrink, y % 2 + newDrink);
    }
}
