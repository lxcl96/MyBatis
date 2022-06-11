package com.ly.log4j.log;

import org.apache.log4j.Logger;

/**
 * @Author : Ly
 * @Date : 2022/6/11
 * @Description : log4j配置文件使用
 */
public class MyLog {
    private static final Logger logger = Logger.getLogger(MyLog.class);


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new ThreadTest().start();
            logger.debug("this is a debug level!");
            logger.info("this is a info level!");
            logger.warn("this is a warn level!");
            logger.error("this is a error level!");
        }
    }
}

class ThreadTest extends Thread {
    private static final Logger logger = Logger.getLogger(ThreadTest.class);
    @Override
    public void run() {
        logger.debug("this is a debug level!");
        super.run();
    }
}