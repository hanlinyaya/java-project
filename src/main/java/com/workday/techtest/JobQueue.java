package com.workday.techtest;

import com.workday.techtest.entity.Job;

import java.util.List;

public interface JobQueue {
    // Remove a job from the queue. If the queue has been drained,
    // this call will block until a new job becomes available
    Job pop();

    // push the job into the job queue
    void push(Job job);

    // track the size of the queue
    long size();

    // iterate jobs in the queue
    List<Job> getJobs();
}