package jdk.lang;

/**
 * �������
 *      ������⡿ 1 ~ 100 ��ͣ�
 *
 *  ��Ҫ����˼·
 *      1. ѭ�����
 *      2. �ݹ����
 *      3. ���������
 *      4. �Ȳ����й�ʽ��ʽ���
 *
 * @author Suvan
 * @date 2018.02.06
 */
public class IncreaseSummation {

    /**
     * ������
     */
    public static void main(String [] arge) {
        System.out.println("ѭ����ͣ�" + sum(100));
        System.out.println("�ݹ���ͣ�" + sumByRecursive(100));
        System.out.println("��������ͣ�" + sumByBinary(100));
        System.out.println("�Ȳ����й�ʽ��ͣ�" + sumByFormula(100));
    }

    /**
     * ���
     *      - ָ��Ŀ������ִ�� 1 ~ aimNumber ���
     *      - ����ѭ����ʽ
     *          1. for ѭ��
     *          2. while ѭ��
     *          3. do-while ѭ��
     *          4. ǰ��ָ�� while ѭ�����ռ����ӣ�ѭ���������룩
     *
     * @param aimNumber Ŀ������
     * @return int ��ͽ��
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
     * �ݹ����
     *      - ָ��Ŀ������ִ�� 1 ~ aimNumber ���
     *      - �����ʹ�� if���ɿ���ʹ�� try-catch ��
     *
     * @param aimNumber Ŀ������
     * @return int ��ͽ��
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
     * ���������
     *      - ָ��Ŀ������ִ�� 1 ~ aimNumber ���
     *      - ʵ��λ����ӷ���������Ч��
     *
     * @param aimNumber Ŀ������
     * @return int ��ͽ��
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
     * �ӷ���
     *      - λ����ģ��ʵ��
     *      - �����Ƽӷ��ھ���ͬ 1 �� 1���� 1 Ϊ 1, ͬ 0 Ϊ 0
     *      - ˼·
     *          1. x ^ y ȡ�á���λ��
     *          2. (x & y) << ȡ"��λ"
     *          3. �ݹ���㣬������λ�� == 0 ʱ����λΪ������ֵ���
     *
     * @param x ����1
     * @param y ����2
     * @return int ������ӽ��
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
     * �Ȳ����й�ʽ���
     *      - ָ��Ŀ������ִ�� 1 ~ aimNumber ���
     *      - �Ȳ�������͹�ʽ: (n + 1) * n / 2
     *
     * @param aimNumber Ŀ������
     * @return int ��ͽ��
     */
    private static int sumByFormula(int aimNumber) {
        //return (aimNumber + 1) * aimNumber / 2;
        return (aimNumber ++ * aimNumber) >> 1;
    }
}
