package com.gaogf.concurrency.concurrency.publish;

import com.gaogf.concurrency.concurrency.annotations.Recommend;
import com.gaogf.concurrency.concurrency.annotations.ThreadSafe;
import com.gaogf.concurrency.concurrency.annotations.UnThreadSafe;

/**
 * 安全发布对象
 * 1.在静态初始化函数中初始化一个对象引用
 * 2.将对象的引用保存到volatile类型域或者AtomicReference对象中
 * 3.将对象的引用保存到某个正确构造对象的final类型域中
 * 4.将对象的引用保存到一个由锁保护的域中
 */
public class SafePublish {

}
@UnThreadSafe
/**
 * 懒汉模式
 * 单例实例在类第一次使用的时候进行创建
 */
class SingletonPublish{
    //私有构造函数
    private SingletonPublish(){}
    //单例对象
    private static SingletonPublish instance = null;
    //静态的工厂方法
    public static SingletonPublish getInstance(){
        if(instance == null){
            instance = new SingletonPublish();
        }
        return instance;
    }

}

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 */
@ThreadSafe
class SingletonPublish2{
    private SingletonPublish2(){}
    private static SingletonPublish2 instance = new SingletonPublish2();
    public static SingletonPublish2 getInstance(){
        return instance;
    }
}

@ThreadSafe
class SingletonPublish3{
    private SingletonPublish3(){}
    private static SingletonPublish3 instance = null;
    static {
        instance = new SingletonPublish3();
    }
    public static SingletonPublish3 getInstance(){
        return instance;
    }
}

@UnThreadSafe
class UnSafeSingletonPublish{
    //私有构造函数
    private UnSafeSingletonPublish(){}
    //单例对象
    private static UnSafeSingletonPublish instance = null;

    /**
     * 1.memory = allocate()分配对象的内存空间
     * 2.ctorInstance()初始化对象
     * 3.instance = memory 设置instance指向刚分配的内存
     * @return
     */
    //静态的工厂方法
    //JVM和cpu优化，发生了指令重排
    public static UnSafeSingletonPublish getInstance(){
        if(instance == null){
            synchronized (UnSafeSingletonPublish.class){
                if (instance == null){
                    instance = new UnSafeSingletonPublish();
                }
            }
        }
        return instance;
    }
}
@ThreadSafe
class SafeSingletonPublish{
    //私有构造函数
    private SafeSingletonPublish(){}
    //单例对象
    //volatile限制指令重排，使得类是线程安全的
    private volatile static SafeSingletonPublish instance = null;
    //静态的工厂方法
    //JVM和cpu优化，发生了指令重排
    public static SafeSingletonPublish getInstance(){
        if(instance == null){
            synchronized (SafeSingletonPublish.class){
                if (instance == null){
                    instance = new SafeSingletonPublish();
                }
            }
        }
        return instance;
    }
}

/**
 * 枚举类保证线程安全
 * 最安全的
 */
@ThreadSafe
@Recommend
class EnumSingletonPublish{
    private EnumSingletonPublish(){}
    public static EnumSingletonPublish getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton{
        INSTANCE;
        private EnumSingletonPublish singleton;
        Singleton(){
            singleton = new EnumSingletonPublish();
        }
        public EnumSingletonPublish getInstance(){
            return singleton;
        }
    }
}
