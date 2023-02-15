package com.bhavya.businesslogic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class creates threads for each CNC machine
 *
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */

public class ProcessSensorData implements Job {
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            Future f1 = executorService.submit(new FileRead("C:\\Users\\jmanj\\Downloads\\sensor1\\sensor-fault-detection.csv"));
            Future f2 = executorService.submit(new FileRead("C:\\Users\\jmanj\\Downloads\\sensor2\\sensor-fault-detection.csv"));
            Future f3 = executorService.submit(new FileRead("C:\\Users\\jmanj\\Downloads\\sensor3\\sensor-fault-detection.csv"));

            System.out.println("After submitting");
            String status1 = (String) f1.get();
            String status2 = (String) f2.get();
            String status3 = (String) f3.get();

            executorService.shutdown();

            System.out.println("Status of Job 1 : " + status1);
            System.out.println("Status of Job 2 : " + status2);
            System.out.println("Status of Job 3 : " + status3);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

}

