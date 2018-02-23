package src.concurrency;

//: concurrency/CaptureUncaughtException.java
import java.util.concurrent.*;

class ExceptionThread2 implements Runnable {
  public void run() {
    Thread t = Thread.currentThread();
    System.out.println("线程在跑run() by " + t);
    System.out.println("再跑eh = " + t.getUncaughtExceptionHandler());
    throw new RuntimeException();
  }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
  @Override//当有异常的时候就会自动调用这个方法，补抓异常
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println("caught 异常" + e);
  }

    @Override
    public String toString() {
        return "抓住不能抓住的异常的类";
    }
  
}

class HandlerThreadFactory implements ThreadFactory {
  @Override
  public Thread newThread(Runnable r) {
    System.out.println(this + " creating new Thread");
    Thread t = new Thread(r);
    System.out.println("created " + t);
    t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());//设置不能抓住的异常控制
    System.out.println( "新建eh = " + t.getUncaughtExceptionHandler());
    return t;
  }

    @Override
    public String toString() {
        return "继承线程工厂的类";
    }
  
}

public class CaptureUncaughtException {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
//      for (int i = 0; i < 10; i++) {
        exec.execute(new ExceptionThread2());//这是新建线程时候就会去调用一次HandlerThreadFactory的newThread创建线程
//      }
    exec.shutdown();
  }
} /* Output: (90% match)
HandlerThreadFactory@de6ced creating new Thread
created Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@1fb8ee3
run() by Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@1fb8ee3
caught java.lang.RuntimeException
*///:~
