package src.concurrency;

//: concurrency/CachedThreadPool.java
import java.util.concurrent.*;
/**
 *  exec.shutdown():当线程池调用该方法时,线程池的状态则立刻变成SHUTDOWN状态,以后不能再往线程池中添加任何任务，否则将会抛出RejectedExecutionException异常。
 * 但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出。 与它相似的还有一个shutdownNow()，
 * 它通过调用Thread.interrupt来实现线程的立即退出。
 * @author Administrator
 */
public class CachedThreadPool {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < 5; i++){
      exec.execute(new LiftOff());
    }
//    exec.shutdown();//interrupt中断的意思，就算这条线程在睡觉也会被中段，然后报错，这个时候可以catch到，在catch中可以直接return跳出循环
//  exec.shutdownNow();
  }
} /* Output: (Sample)
#0(9), #0(8), #1(9), #2(9), #3(9), #4(9), #0(7), #1(8), #2(8), #3(8), #4(8), #0(6), #1(7), #2(7), #3(7), #4(7), #0(5), #1(6), #2(6), #3(6), #4(6), #0(4), #1(5), #2(5), #3(5), #4(5), #0(3), #1(4), #2(4), #3(4), #4(4), #0(2), #1(3), #2(3), #3(3), #4(3), #0(1), #1(2), #2(2), #3(2), #4(2), #0(Liftoff!), #1(1), #2(1), #3(1), #4(1), #1(Liftoff!), #2(Liftoff!), #3(Liftoff!), #4(Liftoff!),
*///:~
