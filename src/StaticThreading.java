//package com.shakti.handson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by autoopt/shrishakti.m.
 */
public class StaticThreading {
    static ExecutorService executor = Executors.newFixedThreadPool(100);
    static int sharedNumber = 0;
    static AtomicInteger initErrorRate = new AtomicInteger(0);
    static AtomicInteger incrErrorRate = new AtomicInteger(0);
    static int numberOfThreads = 5;

    public static void main(String[] args) throws InterruptedException {
        //---------------
        List<Callable<Integer>> numberThreads = new ArrayList<>();
        for (int i = 0; i<numberOfThreads; i++) {
            numberThreads.add(new Numbers());
        }

        executor.invokeAll(numberThreads);
        executor.shutdown();

        if (executor.awaitTermination(1, TimeUnit.HOURS)) {
            System.out.println(String.format("Final value of shared variable : %d", sharedNumber));
            System.out.println(String.format("Error rate : %d", initErrorRate.get()));
            System.out.println(String.format("Manipulation Error Rate : %d", incrErrorRate.get()));
        }
    }

    static class Numbers implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 1000; i++) {
                manipulateSharedVariable();
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return 0;
        }
    }

    static void manipulateSharedVariable() {
//         System.out.println(String.format("Initial Value of Shared Number:%d", sharedNumber));
        sharedNumber = 0;
        if (sharedNumber != 0) {
            initErrorRate.incrementAndGet();
        }
//         System.out.println(String.format("Should be zero:%d", sharedNumber));
        sharedNumber++;

        if (sharedNumber != 1) {
            incrErrorRate.incrementAndGet();
        }

//         System.out.println(String.format("Should be 1:%d", sharedNumber));
    }
}