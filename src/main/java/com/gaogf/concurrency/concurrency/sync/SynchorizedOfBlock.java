package com.gaogf.concurrency.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果SynchorizedOfBlock有子类继承，则子类的test2()方法没有synchronized修饰，
 * 若想子类同样具有同步策略，则需要加synchronized关键字
 */
public class SynchorizedOfBlock {
    public void test1(){
        synchronized(this){
            for(int i=0;i<10;i++){
                System.out.println(i);
            }
        }
    }
    public synchronized void test2(){
        for(int i=0;i<10;i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        SynchorizedOfBlock example1 = new SynchorizedOfBlock();
        SynchorizedOfBlock example2 = new SynchorizedOfBlock();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example2.test1();
        });
    }
}
