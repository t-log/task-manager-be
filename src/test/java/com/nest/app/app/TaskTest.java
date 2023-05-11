package com.nest.app.app;

import com.nest.app.entity.Patient;
import com.nest.app.entity.Tasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TaskTest {

    private Tasks tasksTest;

    @BeforeEach
    public void setUp(){
        LocalDateTime now = LocalDateTime.now();
        tasksTest = new Tasks(1,"description","comment","completed","low"
       ,now,100,false);
    }
    @Test
    public void testTasksEntity(){
        Assertions.assertEquals(1,tasksTest.getId());
        Assertions.assertEquals("description",tasksTest.getDescription());
        Assertions.assertEquals("comment",tasksTest.getComment());
        Assertions.assertEquals("completed",tasksTest.getTaskStatus());
        Assertions.assertEquals("low",tasksTest.getPriority());
        Assertions.assertEquals(false,tasksTest.isIdDeleted());
    }
}
