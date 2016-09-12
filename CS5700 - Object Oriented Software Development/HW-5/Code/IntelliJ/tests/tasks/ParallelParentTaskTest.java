package tasks;

import engineer.Engineer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Aditya on 12/11/2015.
 */
public class ParallelParentTaskTest {

    private static final double DELTA = 1e-15;
    Engineer engineer1 = new Engineer(1, "Engineer1", 7);
    Engineer engineer2 = new Engineer(2, "Engineer2", 10);
    LeafTask task1 = new LeafTask.Builder(1, "Label_task1", "Description_Task1").assignedEngineers(engineer1).estimatedTotalHours(25).build();
    LeafTask task2 = new LeafTask.Builder(2, "Label_task2", "Description_Task2").assignedEngineers(engineer2).estimatedTotalHours(50).build();
    ParallelParentTask parallelParentTask = new ParallelParentTask.Builder(1, "Label_sqTask1", "Description_sqTask1").build();

    @Test
    public void testAddTask() throws Exception {
        parallelParentTask.addTask(task1);
        parallelParentTask.addTask(task2);
        ArrayList<Task> tasks = parallelParentTask.getListTasks();
        Assert.assertSame(task1, tasks.get(0));
        Assert.assertSame(task2, tasks.get(1));
        Assert.assertEquals(tasks.size(), 2);
    }

    @Test
    public void testGetOriginalEstimatedTotalHours() throws Exception {
        parallelParentTask.addTask(task1);
        parallelParentTask.addTask(task2);
        ParallelParentTask sqTask2 = new ParallelParentTask.Builder(2, "Label_sqTask2", "Description_sqTask2").build();
        sqTask2.addTask(parallelParentTask);
        Assert.assertEquals(75, sqTask2.getOriginalEstimatedTotalHours());
    }

    @Test
    public void testGetPercentComplete() throws Exception {
        parallelParentTask.addTask(task1);
        parallelParentTask.addTask(task2);
        ParallelParentTask sqTask2 = new ParallelParentTask.Builder(2, "Label_sqTask2", "Description_sqTask2").build();
        sqTask2.addTask(parallelParentTask);
        Assert.assertEquals(0.0, sqTask2.getPercentComplete(), DELTA);
    }


    @Test
    public void testGetEstimatedWorkDaysToCompletion() throws Exception {
        parallelParentTask.addTask(task1);
        parallelParentTask.addTask(task2);
        ParallelParentTask sqTask2 = new ParallelParentTask.Builder(2, "Label_sqTask2", "Description_sqTask2").build();
        sqTask2.addTask(parallelParentTask);
        Assert.assertEquals(3, sqTask2.getEstimatedWorkDaysToCompletion());
    }

    @Test
    public void testGetAssignedEngineers() throws Exception {
        parallelParentTask.addTask(task1);
        parallelParentTask.addTask(task2);
        ParallelParentTask sqTask2 = new ParallelParentTask.Builder(2, "Label_sqTask2", "Description_sqTask2").build();
        sqTask2.addTask(parallelParentTask);
        Assert.assertEquals(sqTask2.getAssignedEngineers().size(), 4);
    }
}