package com.gaogf.concurrency.concurrency.synccontainer;

import com.gaogf.concurrency.concurrency.annotations.ThreadSafe;
import com.gaogf.concurrency.concurrency.annotations.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class SyncContainerVector {

    public static final int clientTotal = 5000;

    public static final int threadTotal = 200;

    public static Vector<Integer> count = new Vector<>();

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
        System.out.println(count.size());
    }

    public static void add(){
        count.add(1);
    }
}
