package com.javaweb.config.batch;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBatchTestController {
	
	@Autowired
    private JobLauncher jobLauncher;
	
	@Resource(name="myJob")
	private Job job;
	
	//获取验证唯一值
	@GetMapping("/springBatchTest")
	public String springBatchTest() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job,jobParameters);
		return "success";
	}

}
