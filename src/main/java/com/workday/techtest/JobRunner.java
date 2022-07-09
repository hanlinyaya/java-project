package com.workday.techtest;

import com.workday.techtest.JobQueue;

public interface JobRunner {

    void runner(JobQueue jobQueue, long jobCount);

    String version();
}
