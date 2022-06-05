package com.company;

import java.util.concurrent.Callable;

public class FibonacciCallableTask implements Callable<Integer> {
    private final int n;
    int x1 = 1;
    int x2 = 1;

    public FibonacciCallableTask(int n) {
        this.n = n;
    }

    @Override
    public Integer call() {

        int x3;
        System.out.print(x1 + " " + x2 + " ");
        for (int i = 3; i <= n; i++) {
            x3 = x1 + x2;
            System.out.print(x3 + " ");
            x1 = x2;
            x2 = x3;
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        System.out.println();
        return x2;
    }
}
