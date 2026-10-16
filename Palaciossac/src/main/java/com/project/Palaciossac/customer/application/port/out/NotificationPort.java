package com.project.Palaciossac.customer.application.port.out;

import org.springframework.scheduling.config.Task;

public interface NotificationPort {
    void notifyTaskCreated(Task task);
}
