package com.alaitp;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Internal Exception");
            }
        });

	    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread: " + t.getName() + " the error is " + e.getMessage());
            }
        });
	    thread.start();
    }
}
