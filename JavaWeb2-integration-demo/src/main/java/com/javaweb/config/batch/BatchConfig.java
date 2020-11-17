package com.javaweb.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing//开启批处理的支持（一般可以写在启动类上面的）
@Configuration
public class BatchConfig {
	
	@Autowired
    private JobBuilderFactory jobBuilderFactory;
	
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    //数据读取
    @Autowired
    private ItemReaderService itemReaderService;
    
    //数据处理
    @Autowired
    private ItemProcessorService itemProcessorService;
    
    //数据写入
    @Autowired
    private ItemWriterService itemWriterService;

    //数据监听
    @Autowired
    private JobListener jobListener;
    
    /** ---------------------------------------------------------- 分割线  ---------------------------------------------------------- */
    
    //监听
    @Bean
    public JobExecutionListener listener() {
        return jobListener;
    }
	
	//步骤1
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<String,String>chunk(1).reader(itemReaderService).processor(itemProcessorService).writer(itemWriterService).build();
    }
    
    //步骤2
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").<String,String>chunk(1).reader(itemReaderService).processor(itemProcessorService).writer(itemWriterService).build();
    }
    
    //配置一个job
    @Bean(name="myJob")
    public Job myJob() {
        return jobBuilderFactory.get("myJob").start(step1()).next(step2()).listener(jobListener).build();
    }
    
}
