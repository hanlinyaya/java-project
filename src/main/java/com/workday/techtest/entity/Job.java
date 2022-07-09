package com.workday.techtest.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Job extends Thread {

    private static final Logger LOGGER = Logger.getLogger(Job.class.getName());

    // Uniquely identifies the customer associated with this job
    private long customerId;
    // Uniquely identifies the job. 2 jobs with the same
    // uniqueId should behave identically when executed
    private long uniqueId;
    // Estimated time in milliseconds that the job will take to execute
    private int duration;

    public Job(long customerId, long uniqueId, int duration) {
        this.customerId = customerId;
        this.uniqueId = uniqueId;
        this.duration = duration;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Execute the job
    public void execute() {
        try {
            LOGGER.info("executing job: " + uniqueId + " with thread: " + Thread.currentThread().getName());
            // pretend the job is running for $duration ms
            Thread.currentThread().sleep(duration);
        } catch(InterruptedException exception) {
            LOGGER.log(Level.SEVERE, "InterruptedException occurs.", exception);
        }
    }

    // override run() in Runnable interface to prepare for multi-thread
    @Override
    public void run()
    {
        execute();
    }


}
