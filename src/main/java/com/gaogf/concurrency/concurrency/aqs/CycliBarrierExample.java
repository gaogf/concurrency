package com.gaogf.concurrency.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CycliBarrierExample {
    private static int threadCount = 20;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        Thread.sleep(1000);
        for(int i = 0;i<10;i++){
            try {
                race(i);
                log.info("llllll");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void race(int threadnum) throws Exception{
        Thread.sleep(1000);
        System.out.println(threadnum);
        cyclicBarrier.await();
        System.out.println(threadnum);
    }
}
