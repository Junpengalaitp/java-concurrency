package com.alaitp.synchronizer.countdownLatch;

import java.util.concurrent.CountDownLatch;

public class JoinCountDownLatch {

    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("child thread one over!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("child thread two over!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        threadOne.start();
        threadTwo.start();

        System.out.println("wait all children thread finish!");

        countDownLatch.await();

        System.out.println("all children thread finished!");
    }
}
