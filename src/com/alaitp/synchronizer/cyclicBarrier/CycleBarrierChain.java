package com.alaitp.synchronizer.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CycleBarrierChain {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    /**
     * all threads will execute the same step before going into the next
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " step one");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step two");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step three");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " step one");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step two");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " step three");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
