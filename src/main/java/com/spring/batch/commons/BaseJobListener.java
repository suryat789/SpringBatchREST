package com.spring.batch.commons;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;

public class BaseJobListener implements JobExecutionListener {

	private static final Logger LOG = LogManager.getLogger(BaseJobListener.class);

	public void beforeJob(JobExecution jobExecution) {
		LOG.info("Job [" + jobExecution.getJobInstance().getJobName() + "] Job Id [" + jobExecution.getJobId() + "] Started @ " + jobExecution.getStartTime());
		
		final StringBuilder jobInfo = new StringBuilder();
		jobInfo.append("Job-Parameter: \n");
		JobParameters jp = jobExecution.getJobParameters();
		for (Iterator<Entry<String, JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter.hasNext();) {
			Entry<String, JobParameter> entry = iter.next();
			jobInfo.append("  " + entry.getKey() + "=" + entry.getValue() + "\n");
		}
		LOG.debug(jobInfo);
	}

	public void afterJob(JobExecution jobExecution) {
		final StringBuilder jobInfo = new StringBuilder();
		Date dStart = jobExecution.getStartTime();
		Date dEnd = jobExecution.getEndTime();
		long diff = dEnd.getTime()-dStart.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000);
		int totalReadCount = 0;
		int totalWriteCount = 0;
		
		jobInfo.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
		jobInfo.append("+++++++++++++ B A T C H   R E P O R T +++++++++++++++++ \n");
		jobInfo.append("Job [ Name: " + jobExecution.getJobInstance().getJobName() + " \t ID: " + jobExecution.getJobId() + " ] Ended @ " + jobExecution.getEndTime() + "\n");
		jobInfo.append("  Duration    : " + diffHours + "hrs :" + diffMinutes + "mins :" + diffSeconds + "s\n");
		jobInfo.append("  Exit-Code   : " + jobExecution.getExitStatus().getExitCode() + "\n");
		jobInfo.append("  Exit-Descr. : " + jobExecution.getExitStatus().getExitDescription() + "\n");
		jobInfo.append("  Status      : " + jobExecution.getStatus() + "\n");
		jobInfo.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

		for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
			
			totalReadCount = totalReadCount + stepExecution.getReadCount();
			totalWriteCount = totalWriteCount + stepExecution.getWriteCount();
					
			jobInfo.append("Step [ Name: " + stepExecution.getStepName() + " ID: " + stepExecution.getId() +" ]\n");
			jobInfo.append("WriteCount: " + stepExecution.getReadCount() + "\n");			
			jobInfo.append("WriteCount: " + stepExecution.getWriteCount() + "\n");
			jobInfo.append("Commits: " + stepExecution.getCommitCount() + "\n");
			jobInfo.append("Rollbacks: " + stepExecution.getRollbackCount() + "\n");
			jobInfo.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
		}
		
		jobInfo.append("* Total Records Read: " + totalReadCount);
		jobInfo.append("\n* Total Records Written: " + totalWriteCount);
		
		jobInfo.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
		
		LOG.info(jobInfo.toString());
	}

}