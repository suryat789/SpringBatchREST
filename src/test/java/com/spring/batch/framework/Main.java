package com.spring.batch.framework;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		String[] str = {"META-INF/spring/context-config.xml"};
		final JobParametersBuilder paramsBuilder = new JobParametersBuilder();
		
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(str);
			Job job = (Job) ctx.getBean("sampleJob");
			JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");

			//paramsBuilder.addLong("time", 1451457469007L);
			
			JobExecution execution = jobLauncher.run(job, paramsBuilder.toJobParameters());
			
			//JobExecution execution = jobLauncher.run(job, new JobParameters());
			
			System.out.println("Execution Status: "+ execution.getStatus() + "," +  execution.getExitStatus());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//CommandLineJobRunner.main(new String[] { "META-INF/spring/context-config.xml", "sampleJob" });
	}
}
