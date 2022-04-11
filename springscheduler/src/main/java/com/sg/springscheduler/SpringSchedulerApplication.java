package com.sg.springscheduler;

import com.sg.springscheduler.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringSchedulerApplication implements CommandLineRunner {
//	private static Logger logger = LoggerFactory.getLogger(SpringSchedulerApplication.class);

	@Autowired
	SchedulerService schedulerService;

	public static void main(String[] args) {
	 SpringApplication.run(SpringSchedulerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
     //schedulerService.cronJobSch();
//	 schedulerService.importFile();
	}
}
