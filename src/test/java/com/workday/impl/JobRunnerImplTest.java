package com.workday.impl;

import com.workday.techtest.JobQueue;
import com.workday.techtest.JobRunner;
import com.workday.techtest.entity.Job;
import com.workday.techtest.impl.JobQueueImpl;
import com.workday.techtest.impl.JobRunnerImpl;
import org.junit.Assert;
import org.junit.Test;

public class JobRunnerImplTest {

    // version
    static final String VERSION = "C087DE72-0385-4918-A5629547A7E6CC8B";

    @Test(expected = Test.None.class /* no exception expected */)
    public void testJobRunner() {
        // init queue
        JobQueue queue = new JobQueueImpl();

        // Adds 10 jobs to queue with customerId 1L, uniqueId from 1 to 10
        // assume all the estimated duration will be 1s
        for (int i=1; i<=10; i++) {
            Job job = new Job(1L, new Long(i), 1000);
            queue.push(job);
        }

        // Adds 10 jobs to queue with customerId 2L, uniqueId from 11 to 20
        // assume all the estimated duration will be 2s
        for (int i=11; i<=20; i++) {
            Job job = new Job(2L, new Long(i), 2000);
            queue.push(job);
        }

        // Adds 10 jobs to queue with customerId 3L, uniqueId from 21 to 30
        // assume all the estimated duration will be 3s
        for (int i=21; i<=30; i++) {
            Job job = new Job(3L, new Long(i), 3000);
            queue.push(job);
        }

        long queueSize = queue.size();
        JobRunner jobRunner = new JobRunnerImpl();
        jobRunner.runner(queue, 0);
    }

    @Test
    public void testVersion() {
        JobRunner jobRunner = new JobRunnerImpl();
        Assert.assertEquals("version shall be " + VERSION, VERSION, jobRunner.version());
    }
}
