package cn.pys.config;

import cn.pys.job.TestJob;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Description
 * @Date 2021/1/4 17:18
 * @Created by pengys
 */
@Configuration
public class QuartzConfig {

    @Resource
    private DataSource dataSource;

    @Bean(name = "testTaskJob")
    public JobDetailFactoryBean TestJob() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName("testTaskJob");
        jobDetail.setGroup("testTaskJob");
        jobDetail.setJobClass(TestJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    @Bean(name = "TestTaskJobTrigger")
    public CronTriggerFactoryBean updateTaskJobTrigger(@Qualifier("testTaskJob") JobDetailFactoryBean testTaskJob) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(testTaskJob.getObject());
        trigger.setCronExpression("*/3 * * * * ?");
        trigger.setName("testTaskJobTrigger");
        return trigger;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(QuartzJobFactory myJobFactory, Trigger... triggers) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        //使job实例支持spring 容器管理
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setJobFactory(myJobFactory);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        // 延迟15s启动quartz
        // schedulerFactoryBean.setStartupDelay(15);
        schedulerFactoryBean.setTriggers(triggers);
        return schedulerFactoryBean;
    }

    /**
     * 设置quartz属性
     */
    private Properties quartzProperties() {
        Properties prop = new Properties();
        prop.put("quartz.scheduler.instanceName", "ServerScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
        prop.put("org.quartz.scheduler.jobFactory.class", "org.quartz.simpl.SimpleJobFactory");
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.isClustered", "true");
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "10");
        return prop;
    }
}
