package cn.pys.config;

import cn.pys.job.QuartzJob1;
import cn.pys.job.QuartzJob2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail1(){
        return JobBuilder.newJob(QuartzJob1.class).storeDurably().withIdentity("job1","group1").build();
    }

    @Bean
    public Trigger trigger1(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .withRepeatCount(6);
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail1())
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail jobDetail2(){
        return JobBuilder.newJob(QuartzJob2.class).storeDurably()
                .withIdentity("job2","group1")
                .build();
    }

    @Bean
    public Trigger trigger2(){
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail2())
                .withSchedule(CronScheduleBuilder.cronSchedule("*/10 05-10 16 * * ?"))
                .build();
    }

    
}