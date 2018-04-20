package com.gaogf.concurrency.concurrency.atomic;

import com.gaogf.concurrency.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class ConccunrrencyExampleLongAdder {

    public static final int clientTotal = 5000;

    public static final int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws  Exception{
        ExecutorService exec = Executors.newFixedThreadPool(threadTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0; i < clientTotal; i++){
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        System.out.println(count);
    }

    public static void add(){
        count.increment();
    }
}
