package com.spring.batch.commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class BaseStepListener implements StepExecutionListener {

	private static final Logger LOG = LogManager.getLogger(BaseStepListener.class);
	
	public void beforeStep(StepExecution stepExecution) {
		LOG.info("Step [ {} ] is beginning execution", stepExecution.getStepName());
	}
	
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOG.info("\nJob [ Name: {}, ID: {} ] "
				+ "\nStep [ Name: {}, ID: {} ] "
				+ "\nRecords Read: {} "
				+ "\nRecords Written: {} ", 
				stepExecution.getJobExecution().getJobInstance().getJobName(), 
				stepExecution.getJobExecution().getJobInstance().getId(),
				stepExecution.getStepName(),
				stepExecution.getId(),
				stepExecution.getReadCount(),
				stepExecution.getCommitCount()
				);
		
		LOG.info("Step [ {} ] completed with the status {} ", stepExecution.getStepName(), stepExecution.getStatus());
		
		if (stepExecution.getReadCount() == 0) {
            return ExitStatus.COMPLETED;
        }
		
		return stepExecution.getExitStatus();
	}
}