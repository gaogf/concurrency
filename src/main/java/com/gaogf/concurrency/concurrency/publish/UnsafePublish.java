package com.gaogf.concurrency.concurrency.publish;

import com.gaogf.concurrency.concurrency.annotations.UnThreadSafe;

import java.util.Arrays;

@UnThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish publish = new UnsafePublish();
        System.out.println(Arrays.toString(publish.getStates()));
        publish.getStates()[0] = "d";//外部对象对私有属性进行了修改，无法确保其他外部对象读取到的数据的正确性
        System.out.println(Arrays.toString(publish.getStates()));
    }
}
