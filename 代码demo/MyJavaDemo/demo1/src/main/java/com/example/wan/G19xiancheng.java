package com.example.wan;

import static java.lang.Thread.sleep;

public class G19xiancheng {
    public static void main(String[] arsg) {
        MyThread myThread = new MyThread();

        myThread.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("接口第二种启动方式");
                }
            }
        });



        thread.start();
        Runnable t2 = () -> System.out. println ("我是使用Lambda表达式: 简洁、灵活");
        Thread thread2 = new Thread(t2);
        thread2.start();

    }
    static class MyThread extends Thread {
        public void run() {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("接口第一种启动方式11111111111111111");
            }
        }
    }
}

