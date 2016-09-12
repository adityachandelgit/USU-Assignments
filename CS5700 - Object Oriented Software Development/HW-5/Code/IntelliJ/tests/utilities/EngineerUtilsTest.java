package utilities;

import engineer.Engineer;
import org.junit.Assert;
import org.junit.Test;
import tasks.LeafTask;
import tasks.SequentialParentTask;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Aditya on 12/11/2015.
 */
public class EngineerUtilsTest {

    @Test
    public void testGetTasksAssignedToEngineer() throws Exception {

        Engineer engineer1 = new Engineer(1, "Engineer1", 7);
        Engineer engineer2 = new Engineer(2, "Engineer2", 10);
        LeafTask task1 = new LeafTask.Builder(1, "Label_task1", "Description_Task1").assignedEngineers(engineer1).estimatedTotalHours(25).build();
        LeafTask task2 = new LeafTask.Builder(2, "Label_task2", "Description_Task2").assignedEngineers(engineer2).estimatedTotalHours(50).build();

        SequentialParentTask sqTask1 = new SequentialParentTask.Builder(1, "Label_sqTask1", "Description_sqTask1").build();
        sqTask1.addTask(task1);
        sqTask1.addTask(task2);

        ArrayList<Task> engineer1Tasks = EngineerUtils.getTasksAssignedToEngineer(engineer1);
        ArrayList<Task> engineer2Tasks = EngineerUtils.getTasksAssignedToEngineer(engineer2);

        Assert.assertSame(task1, engineer1Tasks.get(0));
        Assert.assertSame(task2, engineer2Tasks.get(0));

    }
}