package com.alaitp.race.condition.atomic.operations;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        BusinessLogic businessLogic1 = new BusinessLogic(metrics);
        BusinessLogic businessLogic2 = new BusinessLogic(metrics);
        MatrixPrinter matrixPrinter = new MatrixPrinter(metrics);

        businessLogic1.start();
        businessLogic2.start();
        matrixPrinter.start();
    }


    public static class MatrixPrinter extends Thread {
        private Metrics metrics;

        public MatrixPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double currentAvg = metrics.getAverage();

                System.out.println("current avg is " + currentAvg);
            }
        }
    }


    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {

                }
                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        }
    }


    public static class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
