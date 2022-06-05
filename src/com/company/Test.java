package com.company;

import java.util.concurrent.*;

public class Test {

    /**
     * Задание 1:
     * Реализовать подсчет факториала в отдельном классе с поддержкой отмены.
     * Подробно выводить каждый шаг программы.
     */
    static class Factorial {
        public static void main(String[] args) {
            FactorialCallableTask factorialTask = new FactorialCallableTask(12);
            ExecutorService executor = Executors.newCachedThreadPool();
            Future<Long> factorialResultFuture = executor.submit(factorialTask);
            try {
                Long factorialResult = factorialResultFuture.get(1, TimeUnit.MICROSECONDS);
                System.out.println("Factorial result is " + factorialResult);
            } catch (InterruptedException e) {
                factorialResultFuture.cancel(true);
                System.out.println("factorialTask has been interrupted");
            } catch (TimeoutException e) {
                factorialResultFuture.cancel(true);
                System.out.println("factorialTask has been timed out and cancelled");
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Задание 2:
     * Реализовать подсчет чисел Фибоначчи с сохранением последней пары чисел в полях класса задачи
     * (отдельный класс для задачи; поддержка отмены).
     * Подробно выводить каждый шаг программы.
     */
    static class Fibonacci {
        public static void main(String[] args) {
            FibonacciCallableTask fibonacciTask = new FibonacciCallableTask(45);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Integer> fibonacciLastFuture = executor.submit(fibonacciTask);
            try {
                Integer fibonacciLastResult = fibonacciLastFuture.get(1, TimeUnit.MILLISECONDS);
                System.out.println("Last number is " + fibonacciLastResult);
            } catch (InterruptedException e) {
                fibonacciLastFuture.cancel(true);
                System.out.println("fibonacciTask has been interrupted");
            } catch (TimeoutException e) {
                fibonacciLastFuture.cancel(true);
                System.out.println("fibonacciTask has been timed out and cancelled");
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Задание 3:
     * Реализовать секундомер (отдельный класс для задачи; поддержка отмены).
     * Подробно выводить каждый шаг программы.
     */
    static class Stopwatch {
        public static void main(String[] args) {
            StopwatchRunnableTask stopwatchTask = new StopwatchRunnableTask();
            ExecutorService executor = Executors.newCachedThreadPool();
            Future<?> result = executor.submit(stopwatchTask);
            try {
                Object o = result.get(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                result.cancel(true);
                System.out.println("stopwatchTask has been interrupted");
            } catch (TimeoutException e) {
                result.cancel(true);
                System.out.println("stopwatchTask has been timed out and cancelled");
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Задание 4:
     * Реализовать копирование файловых потоков
     * (отдельный класс для задачи; поддержка отмены).
     * Подробно выводить каждый шаг программы.
     */
    static class IO {
        public static void main(String[] args) {
            IORunnableTask ioTask = new IORunnableTask();
            ExecutorService executor = Executors.newCachedThreadPool();
            Future<?> result = executor.submit(ioTask);
            try {
                Object o = result.get(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                result.cancel(true);
                System.out.println("ioTask has been interrupted");
            } catch (TimeoutException e) {
                result.cancel(true);
                System.out.println("ioTask has been timed out and cancelled");
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Тест awaitTermination(), shutdownNow()
     */

    static class FactorialAwaitTerminationTest {
        public static void main(String[] args) {
            FactorialCallableTask factorialTask = new FactorialCallableTask(12);
            ExecutorService executor = Executors.newCachedThreadPool();
            Future<Long> factorialResultFuture = executor.submit(factorialTask);
            shutdownAndAwaitTermination(executor);
        }

        private static void shutdownAndAwaitTermination(ExecutorService pool) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(6, TimeUnit.MICROSECONDS)) {
                    pool.shutdownNow();
                    System.out.println("Pool has been terminated");
                    if (!pool.awaitTermination(20, TimeUnit.SECONDS)) {
                        System.err.println("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }


}
