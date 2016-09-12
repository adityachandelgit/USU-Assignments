package engineer;

import org.junit.Assert;
import org.junit.Test;
import tasks.LeafTask;
import tasks.Task;

import java.util.ArrayList;

/**
 * Created by Aditya on 12/11/2015.
 */
public class EngineerTest {

    Engineer e1 = new Engineer(1, "Engineer1", 8);
    LeafTask t1 = new LeafTask.Builder(2, "Task1", "Description1").assignedEngineers(e1).estimatedTotalHours(20).build();

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals(e1.getId(), 1);
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals(e1.getName(), "Engineer1");
    }

    @Test
    public void testGetAvailableHoursPerDay() throws Exception {
        Assert.assertEquals(e1.getAvailableHoursPerDay(), 8);
    }

    @Test
    public void testGetAssignedTasks() throws Exception {
        Assert.assertSame(e1.getAssignedTasks().get(0), t1);
    }

    @Test
    public void testAssignTask() throws Exception {
        LeafTask t2 = new LeafTask.Builder(3, "Task2", "Description2").assignedEngineers(e1).estimatedTotalHours(50).build();
        ArrayList<Task> tasks = e1.getAssignedTasks();
        Assert.assertSame(tasks.get(0), t1);
        Assert.assertSame(tasks.get(1), t2);
    }
}