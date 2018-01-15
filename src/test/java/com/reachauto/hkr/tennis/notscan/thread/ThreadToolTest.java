package com.reachauto.hkr.tennis.notscan.thread;

import org.junit.Test;

public class ThreadToolTest {
    @Test
    public void newExecutor() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("xxxxxxxxxxxxxxxxx");
            }
        });

        for (int i = 0; i < 100; i++) {
            ThreadPoolTool.newExecutor(5).execute(thread);
        }

    }

    @Test
    public void newExecutor1() throws Exception {

    }

    @Test
    public void newSingleExecutor() throws Exception {
        ThreadPoolTool.newSingleExecutor().execute(() -> {
            System.out.println("newSingleExecutor");
        });
        System.out.println(123);
    }

    @Test
    public void newExecutor2() throws Exception {
    }

    @Test
    public void newExecutorByBlockingCoefficient() throws Exception {
    }

    @Test
    public void execute() throws Exception {
    }

    @Test
    public void excAsync() throws Exception {
    }

    @Test
    public void execAsync() throws Exception {
    }

    @Test
    public void execAsync1() throws Exception {
    }

    @Test
    public void newCompletionService() throws Exception {
    }

    @Test
    public void newCompletionService1() throws Exception {
    }

    @Test
    public void newCountDownLatch() throws Exception {
    }

    @Test
    public void newThread() throws Exception {
    }

    @Test
    public void sleep() throws Exception {
    }

    @Test
    public void sleep1() throws Exception {
    }

    @Test
    public void safeSleep() throws Exception {
    }

    @Test
    public void getStackTrace() throws Exception {
    }

    @Test
    public void getStackTraceElement() throws Exception {
    }

    @Test
    public void createThreadLocal() throws Exception {
    }

    @Test
    public void interupt() throws Exception {
    }

    @Test
    public void waitForDie() throws Exception {
    }

    @Test
    public void getThreads() throws Exception {
    }

    @Test
    public void getThreads1() throws Exception {
    }

    @Test
    public void getMainThread() throws Exception {
    }

    @Test
    public void currentThreadGroup() throws Exception {
    }

    @Test
    public void newNamedThreadFactory() throws Exception {
    }

    @Test
    public void newNamedThreadFactory1() throws Exception {
    }

    @Test
    public void newNamedThreadFactory2() throws Exception {
    }

}