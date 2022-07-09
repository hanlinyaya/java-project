package com.workday.techtest.impl;

import com.workday.techtest.JobQueue;
import com.workday.techtest.entity.Job;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JobQueueImpl implements JobQueue {

    Queue<Job> jobQueue = new LinkedList<>();

    @Override
    public Job pop() {
        // synchronize the action of multiple threads and make sure that only one thread can access the resource at a given point in time
        synchronized(jobQueue) {
            return jobQueue.remove();
        }
    }

    @Override
    public void push(Job job) {
        jobQueue.add(job);
    }

    @Override
    public long size() {
        return jobQueue.size();
    }

    @Override
    public List<Job> getJobs() {
        List<Job> jobs = new ArrayList<>();
        for (Job job : jobQueue) {
            jobs.add(job);
        }
        return jobs;
    }
}
