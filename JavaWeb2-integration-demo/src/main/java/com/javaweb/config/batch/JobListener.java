package com.javaweb.config.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Service;

@Service("jobListener")
public class JobListener implements JobExecutionListener {
	
	@Override
	public void beforeJob(JobExecution arg0) {
		System.out.println("job before");
	}

	@Override
	public void afterJob(JobExecution arg0) {
		System.out.println("job after");
	}

}
