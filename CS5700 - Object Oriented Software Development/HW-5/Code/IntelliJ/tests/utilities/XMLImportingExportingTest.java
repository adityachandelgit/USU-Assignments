package utilities;

import engineer.Engineer;
import org.junit.Assert;
import org.junit.Test;
import tasks.LeafTask;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Aditya on 12/11/2015.
 */
public class XMLImportingExportingTest {

    Engineer e1 = new Engineer(1, "Engineer1", 8);
    LeafTask t1 = new LeafTask.Builder(2, "Task1", "Description1").assignedEngineers(e1).estimatedTotalHours(20).build();

    @Test
    public void testExportToXML() throws Exception {
        XMLImportingExporting.exportToXML(t1, "./exported.xml");
        File f = new File("./exported.xml");
        Assert.assertTrue(f.exists());
    }

    @Test
    public void testImportFromXml() throws Exception {

    }
}