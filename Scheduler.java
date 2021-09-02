import java.io.*;
import java.util.HashMap;
import java.util.*;
/*
*Author : Anubhav Sharma
*/ 
public class Scheduler{

    Queue<String> taskList;

    Scheduler() throws Exception{
        taskList = new PriorityQueue<String>();
        start();
        while(true){
            try{
                run();
            }
            catch(Exception exception){
               break;
            }
            Thread.sleep(10000);
        }
        stop();
    }

    public static void main(String ar[]) throws Exception{
        System.out.println("Start : "+new Date());
        new Scheduler();
        System.out.println("End : "+new Date());
    }


    public void stop(){
        sendException();
    }
    public void start() throws Exception{
        loadTask();
    }

    public void loadTask() throws Exception{
        System.out.println("Loading task file . . .");
        File taskFile = new File("task_list.task");
        Scanner scanner = new Scanner(taskFile);
        while(scanner.hasNext()){
            taskList.add(scanner.nextLine());
        }

        System.out.println("Task file loaded. . .");

    }
    public String getCurrentTask() throws Exception{
        return taskList.remove();
    }
    public void run() throws Exception{
        String currentTaskString = getCurrentTask();
        String currentTimeTask[] = currentTaskString.split("\\|");
        System.out.println("Need to execute "+currentTimeTask[1]+" at "+currentTimeTask[0]);
        int timeOfExecution = Integer.parseInt(currentTimeTask[0]);
        long currentMiliTime = System.currentTimeMillis()%86400000;

        if(currentMiliTime<timeOfExecution){
            System.out.println("Cannot execute "+currentTimeTask[1]);
            System.out.printf("Execution time : %d \t Current Time : %d\n", timeOfExecution, currentMiliTime);
            taskList.add(currentTaskString);
            return;
        }
        System.out.println("Executing "+currentTimeTask[1]);

        ProcessBuilder processBuilder = new ProcessBuilder(
            "cmd.exe", "/c", currentTimeTask[1]+">>"+currentTimeTask[1]+".log 2>>"+currentTimeTask[1]+".error"
        );
        processBuilder.redirectErrorStream(true);
        processBuilder.start();
    }
    public void sendException(){
        System.out.println("Done my Job! Sending result . . . ");
    }
}
