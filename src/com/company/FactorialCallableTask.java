package com.company;

import java.util.concurrent.Callable;

public class FactorialCallableTask implements Callable<Long> {
    private final int n;

    public FactorialCallableTask(int n) {
        this.n = n;
    }

    @Override
    public Long call()  {
        long result = 1;

        for (int i = 1; i <= n; i++) {
            result = result * i;
            System.out.println(result);
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }

        return result;
    }
}
