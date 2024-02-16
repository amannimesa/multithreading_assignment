package com.nimesa.careers.multithreading_assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Processor {

    private final Queue<TaskRequest> queue = new LinkedBlockingQueue<>();

    Processor(TaskRequest taskRequest) {
        queue.offer(taskRequest);
    }

    Processor(List<TaskRequest> taskRequest) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (TaskRequest request : taskRequest) {
            queue.offer(request);

        }
    }

    public List<TaskResponse> execute() throws InterruptedException {

        /**
         *Sequential execution
         */
        List<TaskResponse> taskResponses = new ArrayList<>();
        while (!queue.isEmpty()) {
            TaskRequest request = queue.poll();
            Task task = new Task(request);
            TaskResponse run = task.run();
            taskResponses.add(run);
        }
        return taskResponses;
    }
}
