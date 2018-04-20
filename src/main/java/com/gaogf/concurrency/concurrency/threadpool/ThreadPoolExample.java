package com.gaogf.concurrency.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0;i < 10;i++){
            final  int num = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("{}",num);
                }
            });
        }
        exec.shutdown();
    }
}
