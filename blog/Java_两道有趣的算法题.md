---
title: 两道有趣的算法题
date: 2018-01-29 21:48:32
tags: Java
categories: Java
---

# 两道算法题

​    最近发现两道算法题很有灵性, 附上完整源码以及解题思路（运行贴图与代码注释） ^_^，分享学习, 共勉!


1. 喝酒问题
```
身上只有 10 块钱，一共可以喝多少瓶酒?
- 2 块钱一瓶酒
- 4 个瓶盖一瓶酒
- 2 个空瓶换一瓶酒
```

2. 母牛生产问题
```
农场一头小母牛，
每年生头小母牛，
母牛五岁产母牛，
二十年有多少头牛？
```




## 喝酒问题

### 计算过程
![喝酒问题解题过程](/images/喝酒问题解题过程.png "喝酒问题解题过程")


### 源码
```
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


```


## 母牛生产问题

### 计算过程
![母牛生产解题过程](/images/母牛生产解题过程.png "母牛生产解题过程")

### 递归思路
![递归一行代码解决问题](/images/递归一行代码解决问题.png "递归一行代码解决问题")

### 完整源码
```
package jdk.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 【编程题】
 *      农场一头小母牛，
 *      每年生头小母牛，
 *      母牛五岁产母牛，
 *      二十年有多少头牛？
 *
 *      答案：431 头
 *
 * 求解方式：
 *      1. 计算牛数（面向过程方式求解）
 *      2. 计算牛数（递归方式求解）
 *
 * @author Suvan
 * @date 2018.01.29
 */
public class ComputeCow {

    /**
     * 主函数
     */
    public static void main(String [] args) {
        ComputeCow cc = new ComputeCow();

        int year = 20;

        //method 1
        cc.countCowTotals(year);

        //method 2
        System.out.println("递归方式求解：" + cc.countCow(year));
    }

    /**
     * 计算牛数（面向过程方式求解）
     *      - 打印处理过程，分析
     *      - 思路
     *          1. 构建牛对象
     *          2. 农场的牛统一用集合管理（管理所有牛对象）
     *          3. 每年循环
     *              - 遍历农场牛集合
     *                  - 每头牛年龄 + 1
     *                  - 统计新生的牛（若年龄 >= 5）, 则生一头小牛
     *              - 新生牛循环
     *                  - 构建新的牛对象，放到农场牛集合中
     *          4. 得到结果（农场牛集合内元素长度）
     *
     * @param year 输入年份
     */
    private void countCowTotals(int year) {
        //local inner class
        class Cow {
            int age;

            /**
             * 生牛
             *
             * @return int 年龄大于 5 岁才可以生育
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
            System.out.print("第 " + i + " 年\t【年初】牛总数 = " + cowList.size() + "\t------>");

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

            System.out.print("\t【年末】新生牛 = " + newBornCow + "， \t牛总数 = " + cowList.size() + "\n");
        }

        System.out.println("20 年后一共有 " + cowList.size() + " 头牛！");
    }

    /**
     * 计算牛数（递归方式求解）
     *      - 非常简洁，一行代码
     *      - 类似斐波那契数，发现规律 f(n) = f(n - 1) + f(n - 5)
     *          - 前 5 年都是每年增加 1 头，第 6 年开始，第 1 年生的小牛开始可以生育
     *          - 年份        1  2  3  4  5  6  7   8  9   10  ...
     *          - 牛数（年末） 2  3  4  5  6  8  11  15 20  26  ...
     *
     * @param year 指定年份
     * @return int 指定年份的牛数
     */
    private int countCow(int year) {
        return year <= 5 ? year + 1 : countCow(year - 1) + countCow(year - 5);
    }
}


```