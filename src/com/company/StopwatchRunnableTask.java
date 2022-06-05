package com.company;

public class StopwatchRunnableTask implements Runnable {

    @Override
    public void run() {
        int counter = 0;
        while (true) {

            try {
                Thread.sleep(1000);
                counter++;
                System.out.println(counter);
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Time has passed: " + counter);
                return;
            }

        }
    }
}
