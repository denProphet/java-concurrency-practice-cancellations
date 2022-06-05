package com.company;

import java.io.*;

public class IORunnableTask implements Runnable {
    final String filename = "С:\\Videos\\Test\\origin.mp4";

    @Override
    public void run() {
        long start = System.nanoTime();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
             BufferedOutputStream out = new BufferedOutputStream(
                     new FileOutputStream("С:\\Videos\\Res\\test4.mp4"))) {
            byte[] chunk = new byte[4096];
            while (in.available() > 0) {
                int readCount = in.read(chunk);
                if (readCount <= 0) {
                    break;
                }
                out.write(chunk, 0, readCount);
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                long end = System.nanoTime();
                long time = (end - start) / 1000000000;
                System.out.println("time has passed: " + time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
