package src.concurrency;

//: concurrency/ExceptionThread.java
// {ThrowsException}
import java.util.concurrent.*;

public class ExceptionThread implements Runnable {
  public void run() {
//          try {
    throw new RuntimeException();
//     } catch(RuntimeException ue) {
//      // This statement will NOT execute!
//      System.out.println("抓住了");
//      return;
//    }
  }
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.execute(new ExceptionThread());
  }
} ///:~
