Apache Bench (AB): Apache 附带的工具，测试网站性能
命令：ab -n 1000 -c 50 http://localhost://8080/test
命令解释：每次并发50，共有1000次访问路径http://localhost://8080/test
JMeter: Apache组织开发的压力测试工具
PosTMan: Http 请求模拟工具
代码(模拟并发)：Semaphore,CountDownLatch等

线程安全的类
StringBuilder 线程不安全
StringBuffer 线程安全

SimpleDateFormat 线程不安全
joda-time 线程安全