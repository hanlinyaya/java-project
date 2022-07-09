package com.workday.techtest.entity;

import java.util.List;

public class Report {

    private String reportTitle;
    // list of job shown on the report
    private List<Job> jobs;

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
