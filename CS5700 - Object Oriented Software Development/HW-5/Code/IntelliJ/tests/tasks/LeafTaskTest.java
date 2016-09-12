package tasks;

import engineer.Engineer;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by Aditya on 12/11/2015.
 */
public class LeafTaskTest {

    private static final double DELTA = 1e-15;
    Engineer e = new Engineer(0, "engineer1", 10);
    LeafTask leafTask = new LeafTask.Builder(1, "leaf", "leaf_description").assignedEngineers(e).estimatedTotalHours(100).build();


    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals(leafTask.getId(), 1);
    }

    @Test
    public void testGetLabel() throws Exception {
        Assert.assertEquals(leafTask.getLabel(), "leaf");
    }

    @Test
    public void testGetDescription() throws Exception {
        Assert.assertEquals(leafTask.getDescription(), "leaf_description");
    }

    @Test
    public void testGetOriginalEstimatedTotalHours() throws Exception {
        Assert.assertEquals(leafTask.getOriginalEstimatedTotalHours(), 100);
    }

    @Test
    public void testGetAssignedEngineers() throws Exception {
        Assert.assertEquals(leafTask.getAssignedEngineers().get(1), e);
    }

    @Test
    public void testGetRevisedEstimatedTotalHours() throws Exception {
        Assert.assertThat(leafTask.getEstimatedRemainingHours(), anyOf(is(100), is(99)));
    }

    @Test
    public void testGetPercentComplete() throws Exception {
        Assert.assertEquals(leafTask.getPercentComplete(), 0.0, DELTA);
    }

    @Test
    public void testGetEstimatedRemainingHours() throws Exception {
        Assert.assertThat(leafTask.getEstimatedRemainingHours(), anyOf(is(100), is(99)));
    }

    @Test
    public void testGetEstimatedWorkDaysToCompletion() throws Exception {
        Assert.assertEquals(leafTask.getEstimatedWorkDaysToCompletion(), 4);
    }
}