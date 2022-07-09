package com.workday.techtest;

import com.workday.techtest.entity.Report;

public interface JobReporter {

    Report reportRunner(JobQueue jobQueue, long jobCount);

}
