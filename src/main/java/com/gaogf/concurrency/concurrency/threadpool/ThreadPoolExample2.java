package com.gaogf.concurrency.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(3);
        for(int i = 0;i < 10;i++){
            final  int num = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("{}",num);
                }
            });
            exec.schedule(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}",num);
                }
            },1000,TimeUnit.MILLISECONDS);
        }
        exec.shutdown();
    }
}
