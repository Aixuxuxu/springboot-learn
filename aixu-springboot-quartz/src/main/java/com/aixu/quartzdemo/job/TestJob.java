package com.aixu.quartzdemo.job;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Map;

@Slf4j
public class TestJob extends QuartzJobBean {

    @Resource
    QuartzService quartzService;

    @Value("${schedule.cron.withJob1}")
    String withJob1;

    @Value("${schedule.cron.withJob2}")
    String withJob2;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        quartzService.addJob(Job1.class,"1","1",withJob1,null);
        quartzService.addJob(Job2.class,"2","1",withJob2,null);

    }
}

@Slf4j
class Job1 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("任务1执行");

    }
}

@Slf4j
class Job2 extends QuartzJobBean {

    @Resource
    QuartzService quartzService;

    @Override

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Map<String, Object>> maps = quartzService.queryAllJob();
        log.info(maps.toString());

    }
}
