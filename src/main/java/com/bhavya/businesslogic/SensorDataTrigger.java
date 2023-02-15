package com.bhavya.businesslogic;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * In this class we shedule the jobs using quartz scheduler.
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   20223-02-15
 */

public class SensorDataTrigger {
    public static void main( String[] args ) throws Exception
    {
        SensorDataTrigger sensorDataTrigger = new SensorDataTrigger();
        sensorDataTrigger.triggerSensorDataCollection();
    }

    public static void triggerSensorDataCollection() throws Exception {

	   System.out.println("Beginning of the method *********");
        JobDetail job = JobBuilder.newJob(ProcessSensorData.class)
                .withIdentity("ProcessSensorData", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("ProcessSensorDataTrigger", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
                .build();

        //schedule it
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
