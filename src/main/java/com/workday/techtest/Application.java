package com.workday.techtest;

import com.workday.techtest.entity.Job;
import com.workday.techtest.impl.JobQueueImpl;
import com.workday.techtest.impl.JobRunnerImpl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    // Maximum number of threads in thread pool
    static final int MAX_THREADS = 3;

    public static void main(String args[]) {
        /*
        System.out.println("starting thread 1...");
        Task task1 = new Task("thread a");
        task1.start();

        System.out.println("starting thread 2...");
        Task task2 = new Task("thread b");
        task2.start();

        System.out.println("starting thread 11...");
        TaskRunner taskRunner1 = new TaskRunner("thread 11");
        Thread t1 = new Thread(taskRunner1);
        t1.start();

        System.out.println("starting thread 22...");
        TaskRunner taskRunner2 = new TaskRunner("thread 22");
        Thread t2 = new Thread(taskRunner2);
        t2.start(); */

        // test the job queue
        JobQueue queue = new JobQueueImpl();

        // size of the queue before pushing job
        long size1 = queue.size();
        System.out.println("Size of queue before pushing job: " + size1);

        // Adds 10 job to queue with customerId 1L
        for (int i=1; i<=10; i++) {
            Job job = new Job(1L, new Long(i), 1000);
            queue.push(job);
        }

        // Adds 10 job to queue with customerId 2L
        for (int i=11; i<=20; i++) {
            Job job = new Job(2L, new Long(i), 2000);
            queue.push(job);
        }

        // Adds 10 job to queue with customerId 3L
        for (int i=21; i<=30; i++) {
            Job job = new Job(3L, new Long(i), 3000);
            queue.push(job);
        }

        // size of the queue after pushing job
        long size2 = queue.size();
        System.out.println("Size of queue after pushing job: " + size2);

        // To remove the head of queue.
        Job poppedJob = queue.pop();
        System.out.println("pop job id: " + poppedJob.getUniqueId());

        // size of the queue after popping job
        long size3 = queue.size();
        System.out.println("Size of queue after popping job: " + size3);

        /*
        // loop the remaining 29 jobs and execute it one by one in a single thread
        while (queue.size()>0) {
            Job newJob = queue.pop();
            newJob.execute();
        }

        // size of the queue after executing 29 jobs in the queue
        int size4 = queue.size();
        System.out.println("Size of queue after popping job: " + size4); */

        /*
        // creates five tasks
        Runnable r1 = new JobRunnerImpl("task 1");
        Runnable r2 = new JobRunnerImpl("task 2");
        Runnable r3 = new JobRunnerImpl("task 3");
        Runnable r4 = new JobRunnerImpl("task 4");
        Runnable r5 = new JobRunnerImpl("task 5");

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // pool shutdown ( Step 4)
        pool.shutdown(); */

        JobRunner jobRunner = new JobRunnerImpl();
        jobRunner.runner(queue, 0);

        Integer a=1;
        Integer b=2;
        Integer sum=0;
        sum(a, b, sum);
        System.out.println("sum is: " + sum);

    }

    static void sum(Integer a, Integer b, Integer sum) {
        sum = a+b;
        System.out.println("sum in sum(): " + sum);
    }

}
