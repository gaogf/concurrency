package com.gaogf.concurrency.concurrency.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class ConcurrencyOfAtomicIntegerFieldUpdate {
    public static AtomicIntegerFieldUpdater<ConcurrencyOfAtomicIntegerFieldUpdate> update =
            AtomicIntegerFieldUpdater.newUpdater(ConcurrencyOfAtomicIntegerFieldUpdate.class,"count");
    @Getter
    public volatile int count = 100;

    public int getCount() {
        return count;
    }

    public static ConcurrencyOfAtomicIntegerFieldUpdate obj = new ConcurrencyOfAtomicIntegerFieldUpdate();

    public static void main(String[] args) {
        if(update.compareAndSet(obj,100,200)){
            System.out.println(obj.getCount());
        }
        if(update.compareAndSet(obj,100,200)){
            System.out.println(obj.getCount());
        }else {
            System.out.println(obj.getCount());
        }
    }

}
