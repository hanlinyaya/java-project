package com.workday.techtest.impl;

import com.workday.techtest.JobQueue;
import com.workday.techtest.JobRunner;
import com.workday.techtest.entity.Job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobRunnerImpl implements JobRunner {

    private static final Logger LOGGER = Logger.getLogger(JobRunnerImpl.class.getName());

    // maximum number of threads in thread pool
    static final int MAX_THREADS = 10;
    // timeout for threads execution
    static final int TIMEOUT = 5;
    // version
    static final String VERSION = "C087DE72-0385-4918-A5629547A7E6CC8B";

    public JobRunnerImpl() {}

    @Override
    public void runner(JobQueue jobQueue, long jobCount) {
        // creates a thread pool with MAX_THREADS number of threads
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);

        long jobCountInQueue = jobQueue.size();
        LOGGER.info("*** job size in the queue: " + jobCountInQueue);

        while (jobQueue.size()>0) {
            // each job will init a thread
            Runnable runner = jobQueue.pop();
            // utilize thread pool to manage the thread resource
            pool.execute(runner);
        }

        // shut down thread pool
        pool.shutdown();

        //Waiting for existing threads to complete their execution, or call pool.shutdownNow() after given time
        try {
            if (!pool.awaitTermination(TIMEOUT, TimeUnit.MINUTES)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException exception) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
            LOGGER.log(Level.SEVERE, "InterruptedException occurs.", exception);
        }

    }

    @Override
    public String version() {
        return VERSION;
    }

}
