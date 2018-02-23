package src.concurrency;//: concurrency/Joining.java
// Understanding join().
import java.util.logging.Level;
import java.util.logging.Logger;
import static net.mindview.util.Print.*;

class Sleeper extends Thread {
  private int duration;
  public Sleeper(String name, int sleepTime) {
    super(name);
    duration = sleepTime;
    start();
  }
  public void run() {
    try {
    print(getName() + " 睡觉了");
      sleep(duration);
    } catch(InterruptedException e) {
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sleeper.class.getName()).log(Level.SEVERE, null, ex);
        }
      print(getName() + " was interrupted. " +
        "isInterrupted(): " + isInterrupted());
      return;
    }
    print(getName() + " has awakened");
  }
}

class Joiner extends Thread {
  private Sleeper sleeper;
  public Joiner(String name, Sleeper sleeper) {
    super(name);
    this.sleeper = sleeper;
    start();
  }
  public void run() {
   try {
    print(getName() + "来了");
      sleeper.join();//调用其他线程加入这个线程的运行中，当这个其他线程没有走完，这个线程也就不会走下去，join（）还能带时间参数，时间一到，这个线程也可以运行
    } catch(InterruptedException e) {
      print("Interrupted");
    }
    print(getName() + " join completed");
  }
}

public class Joining {
  public static void main(String[] args) {
    Sleeper sleepy = new Sleeper("Sleepy", 10000),grumpy = new Sleeper("Grumpy", 10000);
    Joiner dopey = new Joiner("Dopey", sleepy),doc = new Joiner("Doc", grumpy);
    grumpy.interrupt();//interrupt中断的意思，就算这条线程在睡觉也会被中段，然后报错，这个时候可以catch到，在catch中可以直接return跳出循环
  }
} /* Output:
Grumpy was interrupted. isInterrupted(): false
Doc join completed
Sleepy has awakened
Dopey join completed
*///:~
