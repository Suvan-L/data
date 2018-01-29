package jdk.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * ������⡿
 *      ũ��һͷСĸţ��
 *      ÿ����ͷСĸţ��
 *      ĸţ�����ĸţ��
 *      ��ʮ���ж���ͷţ��
 *
 *      �𰸣�431 ͷ
 *
 * ��ⷽʽ��
 *      1. ����ţ����������̷�ʽ��⣩
 *      2. ����ţ�����ݹ鷽ʽ��⣩
 *
 * @author Suvan
 * @date 2018.01.29
 */
public class ComputeCow {

    /**
     * ������
     */
    public static void main(String [] args) {
        ComputeCow cc = new ComputeCow();

        int year = 20;

        //method 1
        cc.countCowTotals(year);

        //method 2
        System.out.println("�ݹ鷽ʽ��⣺" + cc.countCow(year));
    }

    /**
     * ����ţ����������̷�ʽ��⣩
     *      - ��ӡ������̣�����
     *      - ˼·
     *          1. ����ţ����
     *          2. ũ����ţͳһ�ü��Ϲ�����������ţ����
     *          3. ÿ��ѭ��
     *              - ����ũ��ţ����
     *                  - ÿͷţ���� + 1
     *                  - ͳ��������ţ�������� >= 5��, ����һͷСţ
     *              - ����ţѭ��
     *                  - �����µ�ţ���󣬷ŵ�ũ��ţ������
     *          4. �õ������ũ��ţ������Ԫ�س��ȣ�
     *
     * @param year �������
     */
    private void countCowTotals(int year) {
        //local inner class
        class Cow {
            int age;

            /**
             * ��ţ
             *
             * @return int ������� 5 ��ſ�������
             */
            int born () {
                return age >= 5 ? 1 : 0;
            }
        }

        //the first cow
        List<Cow> cowList = new ArrayList<>();
            cowList.add(new Cow());
            cowList.get(0).age = 4;

        //20 years
        for (int i = 1; i <= year; i++){
            System.out.print("�� " + i + " ��\t�������ţ���� = " + cowList.size() + "\t------>");

            //after a year ....
            //each age + 1 and count new born cow
            int newBornCow = 0;
            for (Cow cow: cowList) {
                cow.age += 1;
                newBornCow += cow.born();
            }

            //list add new born cow
            for (int j = 0; j < newBornCow; j++) {
                cowList.add(new Cow());
            }

            System.out.print("\t����ĩ������ţ = " + newBornCow + "�� \tţ���� = " + cowList.size() + "\n");
        }

        System.out.println("20 ���һ���� " + cowList.size() + " ͷţ��");
    }

    /**
     * ����ţ�����ݹ鷽ʽ��⣩
     *      - �ǳ���࣬һ�д���
     *      - ����쳲������������ֹ��� f(n) = f(n - 1) + f(n - 5)
     *          - ǰ 5 �궼��ÿ������ 1 ͷ���� 6 �꿪ʼ���� 1 ������Сţ��ʼ��������
     *          - ���        1  2  3  4  5  6  7   8  9   10  ...
     *          - ţ������ĩ�� 2  3  4  5  6  8  11  15 20  26  ...
     *
     * @param year ָ�����
     * @return int ָ����ݵ�ţ��
     */
    private int countCow(int year) {
        return year <= 5 ? year + 1 : countCow(year - 1) + countCow(year - 5);
    }
}
