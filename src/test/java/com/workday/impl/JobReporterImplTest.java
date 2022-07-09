package com.workday.impl;

import com.workday.techtest.JobQueue;
import com.workday.techtest.JobReporter;
import com.workday.techtest.entity.Job;
import com.workday.techtest.entity.Report;
import com.workday.techtest.impl.JobQueueImpl;
import com.workday.techtest.impl.JobReporterImpl;
import org.junit.Assert;
import org.junit.Test;

public class JobReporterImplTest {

    @Test
    public void testReportRunner() {

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

        JobReporter jobReporter = new JobReporterImpl();
        Report report = jobReporter.reportRunner(queue, 0);

        Assert.assertEquals("jobs in report shall be 20: ", 20, report.getJobs().size());
    }


}
