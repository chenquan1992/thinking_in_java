package src.concurrency;

//: concurrency/LiftOff.java

import java.util.logging.Level;
import java.util.logging.Logger;

// Demonstration of the Runnable interface.

public class LiftOff implements Runnable {
  protected int countDown = 10; // Default
  private static int taskCount = 0;
  private final int id = taskCount++;
  public LiftOff() {}
  public LiftOff(int countDown) {
    this.countDown = countDown;
  }
  public String status() {
    return "#" + id + "线程名字"+Thread.currentThread().getName()+"(" +
      (countDown > 0 ? countDown : "Liftoff!") + "), ";
  }
  public void run() {
    while(true) {
        try {
            System.out.println("开始线线程");
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return;//interrupt中断的意思，就算这条线程在睡觉也会被中段，然后报错，这个时候可以catch到，在catch中可以直接return跳出循环
        }
      System.out.println(status());
      Thread.yield();
    }
  }
} ///:~
