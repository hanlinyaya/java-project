package com.workday.impl;

import com.workday.techtest.JobQueue;
import com.workday.techtest.entity.Job;
import com.workday.techtest.impl.JobQueueImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JobQueueImplTest {

    @Test
    public void testJobQueue() {
        // init queue
        JobQueue queue = new JobQueueImpl();

        // size of the queue before pushing job shall be 0
        long queueSizeForEmptyQueue = queue.size();
        Assert.assertEquals("Size of the queue before pushing any job should be 0: ", 0, queueSizeForEmptyQueue);

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

        // size of the queue after pushing job shall be 30
        long sizeAfterPushingJobs = queue.size();
        Assert.assertEquals("Size of the queue after pushing jobs should be 30: ", 30, sizeAfterPushingJobs);
        // number of the jobs in the queue shall be 30 as well
        List<Job> jobs = queue.getJobs();
        Assert.assertEquals("Number of the jobs should be 30: ", 30, jobs.size());

        // pop one job: remove the head of queue
        // the unique id for the first popped job shall be 1L
        Job poppedJob = queue.pop();
        Assert.assertEquals("The uniqueId for the first popped job should be 1: ", 1L, poppedJob.getUniqueId());

        // size of the queue after popping one job shall be 29
        long sizeAfterPoppingFirstJob = queue.size();
        Assert.assertEquals("Size of the queue after popping first job should be 29: ", 29, sizeAfterPoppingFirstJob);
    }
}
