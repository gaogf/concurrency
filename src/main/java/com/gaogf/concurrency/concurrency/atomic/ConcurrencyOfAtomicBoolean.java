package com.gaogf.concurrency.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicBoolean一般用于保证只执行一次的语句
 */
@Slf4j
public class ConcurrencyOfAtomicBoolean {

    public static AtomicBoolean aBoolean = new AtomicBoolean(false);

    public static int threadCount = 5000;
    public static int clientCount = 200;
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        final Semaphore semaphore = new Semaphore(clientCount);
        final CountDownLatch downLatch = new CountDownLatch(threadCount);
        for(int i=0; i<threadCount; i++){
            exec.execute(() -> {
                try{
                    semaphore.acquire();
                    aBoolean.getAndSet(true);
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }

            });
        }
        downLatch.countDown();
        exec.shutdown();
        System.out.println(aBoolean.get());
    }
}
