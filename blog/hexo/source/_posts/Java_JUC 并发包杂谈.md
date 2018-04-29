---
title: JUC 并发包杂谈
date: 2018-01-25 17:12:28
tags: Java
categories: Java
---

# 目录

1. volatile 关键字
2. 原子变量
3. 锁分段机制
4. 闭锁
5. 使用 Callable 接口
6. Lock 同步锁
7. 等待唤醒机制
8. 一道“线程按序交替执行”的编程题
9. 读写锁
10. 线程八锁的关键
11. 线程池
12. Fork/Join 框架

---


# 1. volatile 关键字
1. 相较于使用 synchronized ，是一种更为轻量级的同步策略
2. 保证可见性，不具备互斥性，不保证原子性
  - 可见性（某个线程修改，所有线程可见）
  - 互斥性（同一时刻，仅有一个线程可以访问）
  - 原子性（修改主存时，依然存在被抢占 CPU 资源的可能性）
3. 【可见性问题示例】共享数据默认存在主存中，多线程操作需对共享数据进行改变时
  - 先将共享数据读到分线程的自身的缓存中
  - 再修改
  - 然后再写回主存中
4. 使用 volatile 声明的类变量，多线程操作时，都在主存中进行
  - 性能会降低（相较于什么都不加），因为 JVM 的优化会进行重排序，但用 volatile 修饰的变量，不能进行重排序 
  - 但仍然比用锁的效率要高


# 2.  原子变量
1. JDK 1.5 后提供了 java.util.concurrent.atomic 包，包含大量常用的原子性类
  - 类变量默认已经使用 volitile 修饰，保证了内存可见性
  - 使用 CAS（Compare-And-Swap） 算法保证数据的原子性

2. CAS 算法
  - 起初该算法是硬件对于并发操作共享数据的支持，在 Java 中用于保证共享数据的原子性
  - 在原子变量类核心的方法 'compareAndSet(int expect, int update)'
  - 包含三个操作数：V-内存值，预估值-A，更新值-B
    - 【核心思想】仅当 V == A 时，才会进行 V = B，否则什么都不做
    - 每次调用函数时（函数需用 synchronized 修饰，保证线程独占），先传递进预估值（即为此时此刻的内存值），函数内需再次获取当前的内存值（判断是否出现变化），若无变化（确保前后一致），即进行能更新操作
  - 【应用示例】当多个线程同时对主存数据数据进行修改时，仅有一个线程会成功，其他线程都将失败
    - 【效率失败】CAS 算法比锁的效率高，因为当线程失败时，不会阻塞（不会放弃 CPU 的资源，然后立即再次尝试）
    - 【缺点】需要同时考虑成功与失败（什么时候开始，什么时候停止）的情况


# 3.  锁分段机制

1. JDK 5.0 再 java.util.concurrent 包中提供了多种并发容器类来改进同步容器的性能
  - 原本可使用例如 ：'Collections.sychronizedList()' 的方法，将集合类修改问线程安全的

2. HashMap 和 HashTable 的区别
  - HashTable 效率低，它是 线程安全的，但锁是锁整个表，当多个线程并发访问时，并行 -> 串行（一个线程访问完，另一个线程才可访问）
  - HashTable 存在复合操作的安全问题
    - 【例】操作同一个 HashTable，进行 ”若存在则添加“，”若存在则删除“ 的复合操作时，可能出现中途被剥夺 CPU 使用权的情况

3. ConcurrentHashMap 同步容器是新增的线程安全的哈希表，内部采用”锁分段“机制代替原有 Hashtable 的独占锁，进而提高性能
  - 并发级别（concurrentLevel) ，默认级别为 16 （16  个段-Segment）
  - 每个段默认包含一个独立表元素（哈希表默认长度 16）
  - 每个表的每个长度位置包含 1 个链表
    - 【并行】由于每个段都是独立的锁，多线程并发访问时，可分别访问，支持最高 16 个线程同时并行（不仅线程安全，同时效率高，也提供一些复合操作的函数）
    - 【jdk 1.8 变化】底层实现取消了分段锁机制，改为使用 CAS 算法重新实现（因为 CAS 不会阻塞，不需考虑上下文阻塞的问题，效率高）

4. 并发包下的同步容器类，同时被多线程迭代时，不会抛出 'ConcurrentModifyException' 异常
  - 多线程同时访问正在迭代的 java.uitl 容器类下会（即使被 'Collections.sychronizedList()' 包装），依旧会抛出 'CconcurrentModifyException' 异常，因为迭代的是同一个集合类
  - 并发包内同步容器类，在迭代时，会对原先的集合类进行一个复制，所以迭代的副本连变更
  - ​
5. ' CopyOnWriteArrayList'- 写入并复制（每次添加时，都会进行复制成一个全新的容器类），适合迭代多，添加修改操作少的场景


# 4.  闭锁
1. CountDownLath 是一个同步辅助类，使线程等待其余线程全部都完成运算后，再继续执行（先完成任务的线程会阻塞）
  - 初始化时，声明线程数量，同时线程的 'Thread.run()' 中加入'try-finally' 块（需用 sychronized 代码块加锁），并执行完任务后再 finally 加入 'CountDownLath.countDown()' （表示计数 -1）
  - 主函数代码中，使用 'CountDownLatch..await()' 执行等待，等所有线程完成后（最终计数 == 0），主线程再继续执行

2. 适用场景
  - 并行计算，统计最终计算总结果
  - 并行程序，性能测试时间


# 5. 使用 Callable 接口 
1. 特点（即是与 Runnable 的不同）
  - 具有返回值
  - 可以抛出异常
  - 使用泛型定义（既表示函数的返回值类型）
  - 需要 FutureTask 的支持（用于接收运算结果）
    - 是 Future 接口的实现类
    - 接收时需要声明泛型
    - FutureTask 可用于闭锁
    - 【代码示例】
```
      //1. 声明线程任务（实现了 Callable 接口）
      ThreadDemo td = new ThreadDemo();

      //2. 声明接收结果
      FutureTask<Integer> result = new FutureTask<>(td);

      //3. 启动线程，开始执行任务
      new Thread(result).start()

      //4. 获取结果（当分线程完成后，主线程的 get() 才会继续往下执行，类似于闭锁）
      try {
          Integer sum = result.get();
      } catch(InterruptedException ie | ExecutionException e) { }
```


# 6.  Lock 同步锁
1.  解决多线程安全问题的方式
  - 同步代码块 synchronized(锁对象) { } 
  - 同步方法 synchronized
  - 同步锁 Lock（JDK1.5 后出现）
    - 显式锁，需通过 lock() 方法上锁，必须通过 unlock() 方法释放锁（最好在 finally 中，保证必须执行，否则将可能会出现线程安全问题）
    - 提供更为灵活的同步处理方式
    - 可配合 Condition 对象进行线程通信


2. Lock 使用方法
  - 对象声明类变量 `private Lock lock = new ReentrantLock()` 
  - 对象类实现了 Runnable 接口，在 'run()' 函数内利用 'lock.lock()' 和 'lock.unlock()' 进行同步加锁处理


# 7. 等待唤醒机制
## a. 生产者和消费者案例
1. 产品类类【零售店】
  - 类变量 'product'（产品总数）
  - purchase()，进货函数（产品数限额为 10，不足则自增，超过则提示 ”产品已满“ ）
  - selling()，售货函数 （产品不足（> 0）时，货品足够则自减，不足（<=0）则提示 ”缺货“ ）

2. 生产者类【供应商】
  - 类变量 ”产品类对象“
  - 构造函数，获取产品类对象，注入类变量
  - 实现 Runnable 接口，'run()'  函数内，循环调用 '产品类对象.purchase()'，提供货物

3. 消费者【顾客】
  - 类变量 '产品类对象'
  - 构造函数，获取产品类对象，注入类变量
  - 实现 Runnable 接口，'run()' 函数内，循环调用 '产品类对象'selling()，购买货物

4. 多线程执行
  - 【可能产生的问题】
    - 生成者线程过快（不断的发送数据）而消费者已经不接收了，可能造成数据丢失的情况
    - 消费者线程过快（不断的接收数据），而生产者已经不发了，可能出现重复的数据 or 错误的数据
  - 【解决上述问题方案】使用等待唤醒机制
    - 在产品类中的进货函数 'purchase()' 
      - 若产品已满，则使生产者线程进入”等待机制“（使用  'Object.wait()' ）
      - 若成功生产产品，则通知消费者线程可以卖货了，消费者进入”唤醒机制“（使用 Object.notifyAll()）
    - 产品类的 selling() 函数同理
    - 【注意问题】
      - 'Object.wait()' 使线程等待后，再次唤醒，会从停止的部分，继续往下执行，不会从头开始（注意 if-else 的使用问题） 
      - 多生产者，多消费者时，存在“虚假唤醒情况”（多个消费者线程因“缺货”，导致进入“等待”机制，当新的货品被生产出来，则使用 Object.notifyAll() 唤醒时，多个消费者线程同时被唤醒，导致之后货品同时--， 货品总数出现误差）
    - 【解决方案】依据 API 中提供的解决方案， Object.wait() 应该总是放在 while 循环中（唤醒后，再依据条件判断一次）

## b.  Condition 接口进行线程通信
1. 描述了可能会与锁关联的条件变量（Condition 实例实质被绑定到一个锁上，需通过 Lock 实例的 lock.newCondition() 获取）

2. Condition 对象内的函数分别对应
  - Condition.await() - Object.wait()
  - Condition.signal() - Object.notify()
  - Condition.signal() - Object.signalAll()


# 8. 一道“线程按序交替执行”编程题
```
package jdk.concurrent.code;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 【编程题】
 *      开启 3 个线程，三个线程 ID 分别为 A, B, C，
 *      每个线程将自己 ID 在屏幕打印 10 遍，要求输出结果按顺序显示
 *      如： ABCABCABCABC.... 依次递归
 *
 * @author Suvan
 * @date 2018.01.25
 */
public class TestThreadABCAlternateDemo {

    /**
     *
     * @param args
     */
    public static void main(String [] args) {
       AlternateABC alt = new AlternateABC();

       String [] letterArray = {"A", "B", "C"};
       for (int i = 0; i < 3; i++) {
           new Thread(new Runnable() {
               @Override
               public void run () {
                   for (int i = 0; i < 20; i++) {
                       alt.loop();
                   }
               }
           }, letterArray[i]).start();
       }
    }
}
class AlternateABC {

    /**
     * 标识现在正在执行的打印序号（0-A, 1-B, 2-C）
     */
    private int sign = 0;

    private Lock lock = new ReentrantLock();
    private ArrayList<Condition> conditionList = new ArrayList<>(3);

    /**
     * 构造函数声明 3 个线程通讯实例
     *      - 可休眠唤醒 3 个线程进行工作
     */
    public AlternateABC() {
        for (int i = 0; i < 3; i++) {
            conditionList.add(lock.newCondition());
        }
    }

    /**
     * 循环打印
     *      - 同步加锁
     *      - 判断当前应该打印的字母标识（sign 变量，0-A，B-1,C-2）
     *          - 若无法对应，则休眠线程，使其进入等待状态
     *      - 打印线程名（满足 sign 指定的顺序）
     *      - 设置顺序 sign + 1，并唤醒下一个线程
     *      - 同步解锁
     */
    public void loop() {
        lock.lock();

        try {
            String name = Thread.currentThread().getName();
            int currentSign = "A".equals(name) ? 0 : "B".equals(name) ? 1 : 2;
            if (sign != currentSign) {
                conditionList.get(currentSign).await();
            }

            System.out.print(Thread.currentThread().getName());

            sign = sign == 2 ? 0 : ++sign;
            conditionList.get(sign).signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```


# 9.读写锁
1.  特性
  - java.util.concurrent.ReadWriteLock
  - 维护两个锁，读锁可以多个线程同时持有，写锁是独占锁
  - 写写//读写，需要“互斥”， 
  - 读读，不需要“互斥”

2.  使用
  - 变量声明：`ReadWriteLock lock = new ReentrantReadWriteLock()`
  - 加锁和解锁
    - 读锁：'lock.readLock().lock()' 和 'lock.readLock().unlock()'
    - 写锁：'lock.writeLock().lock()' 和 'lock.writeLock().unlock()'
    - 【注意】解锁应在 finally 块中



# 10. 线程八锁的关键
1. 非静态方法的锁默认为 this，静态方法的锁默认为 Class 实例

2. 某一时刻内，只能有一个线程持有锁（锁对象，其余任何线程都不能拿到该锁）

3. 锁对象是 this，被锁定后，其余线程都不能进入当前对象的其他 synchronized 修饰的方法（若普通方法则无关）


# 11. 线程池
1. 特点
  - 提供了一个线程队列，队列中保存着等待状态的线程
  - 避免了创建与销毁额外的开销，提高了响应的速度

2. 结构体系
```
java.util.concurrent.Executor: 负责线程的使用月调度的根接口
	|-- ExecutorService 子接口：线程池的主要接口
		|-- ThreadPoolExecutor 实现类
		|-- ScheduledExecutorService 子接口：负责线程的调度
			|-- ScheduledThreadPoolExecutor 实现类（继承了 ThreadPoolExecutor ，又实现了ScheduledExecutorService），既具备线程池功能，又可用进行线程的调度
```

3. 使用
  - 创建线程
    - 利用工厂方法创建线程池 (API 文档有说明，可用 ExecutorService 接收实例)
    - 无限线程池 `Executor.newCachedThreadPool()`（缓存线程池，自动更改数量）
    - 固定大小的线程池 `Executors.newFixedThreadPool(int)`
    - 单独的线程池 `Executors.newSingleThreadExecutor()`（只有一个线程）
    - 固定大小线程池，但可以进行延迟 or 定时执行任务 `ScheduledExecutorService.newScheduledThreadPool()`
  - 代码中使用步骤
    1. 创建线程池
    2. 创建任务（自定义，例：实现 Runnable 接口）
    3. 为线程分配任务 `pool.submit(myTask)`
    4. 关闭线程池 `pool.shutdown` 或 'pool.shutdownNow()'
    > 【注意】 如果是实现 Callable<> 接口，可用 Future 接收返回结果（可用集合接收多个线程结果，最后统一遍历）
  - 线程调度
    - 使用 `pool.schedule(RunableTask, 3, TimeUnit.SECONDS))`
    - 【注意】如果不关线程池，需要程序不会停（一直在等待接任务）


# 12. Fork/Join 框架
1. 特点
  - 1.7 后提供，实现了 Future 接口（面向接口编程的特点，功能扩展，Future 是在 1.5 的）
  - 【核心思想】在必要情况下，将一个大任务，进行拆分（fork）成若干个小任务（拆到不能再差），再将一个个小任务并行运算的结果进行汇总（join）
  - 利用双端队列，采用“工作窃取”模式（work-stealing），处理完问题的线程，主动去寻找其他线程队列中，为执行完的任务（从后面），窃取任务开始执行

2. 模拟实现
  - 继承 RecursiveTask 或 RecursiveAction
    - 【区别】RecursiveTask 内的方法有返回值（需声明泛型），RecursiveAction 没有返回值
  - 实现其中 compute() 抽象方法
  - 思路
    1. 设置临界值
    2. 小于临界值，直接进行计算，返回计算结果
    3. 若到大于临界值，进行拆分（fork），以递归形式，左右拆分
    4. '.fork()' 拆分完压入线程队列
    5. '.join()' 运算结果合并

3. 什么情况下用这个个框架比较合适？
  - 串行时间太长，需并行运算提高效率
  - 考虑实际情况，设计临界值，考虑高运算情况（例：运算的值过大时，可进行优化）
  - 好的算法会充分利用 CPU 资源