/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 带时间自动循环， Executors.newScheduledThreadPool(5);//: concurrency/GreenhouseScheduler.java
 * @author Administrator
 */


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> scheduleAtFixedRate = scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds");
                System.out.println("1 seconds");
            }
        }, 0, 3, TimeUnit.SECONDS);

        ScheduledFuture<?> scheduleAtFixedRate2 = scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 2 seconds");
                System.out.println("2 seconds");
            }
        }, 0, 3, TimeUnit.SECONDS);
        try {
            Thread.sleep(2000);
            scheduleAtFixedRate.cancel(true);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadPoolExecutorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
}
