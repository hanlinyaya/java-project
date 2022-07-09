package com.workday.techtest.impl;

import com.workday.techtest.JobQueue;
import com.workday.techtest.JobReporter;
import com.workday.techtest.entity.Report;

public class JobReporterImpl implements JobReporter {

    @Override
    public Report reportRunner(JobQueue jobQueue, long jobCount) {
        Report report = new Report();
        report.setReportTitle("Job Report");
        report.setJobs(jobQueue.getJobs());

        return report;
    }
}
