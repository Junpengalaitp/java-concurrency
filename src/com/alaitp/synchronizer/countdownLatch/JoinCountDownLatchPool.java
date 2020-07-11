package com.alaitp.synchronizer.countdownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JoinCountDownLatchPool {
    private static int threadNum = 10000;
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(threadNum);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNum; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("child thread " + finalI + " over!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            executorService.submit(thread);
        }

        System.out.println("wait all children thread finish!");

        countDownLatch.await();

        System.out.println("all children thread finished!");
    }
}
