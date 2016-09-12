import engineer.Engineer;
import tasks.LeafTask;
import tasks.SequentialParentTask;
import tasks.Task;
import utilities.EngineerUtils;
import utilities.XMLImportingExporting;

/******************************************************************************************
 * DEMO
 * Main Classing for demonstrating the usage of WBS library
 * DEMO
 ******************************************************************************************/

public class Main {

    public static void main(String[] args) {

        Engineer engineer1 = new Engineer(1, "Engineer1", 7);
        Engineer engineer2 = new Engineer(2, "Engineer2", 10);
        LeafTask task1 = new LeafTask.Builder(1, "Label_task1", "Description_Task1").assignedEngineers(engineer1).estimatedTotalHours(25).build();
        LeafTask task2 = new LeafTask.Builder(2, "Label_task2", "Description_Task2").assignedEngineers(engineer2).estimatedTotalHours(50).build();

        SequentialParentTask sqTask1 = new SequentialParentTask.Builder(1, "Label_sqTask1", "Description_sqTask1").build();
        sqTask1.addTask(task1);
        sqTask1.addTask(task2);

        SequentialParentTask sqTask2 = new SequentialParentTask.Builder(2, "Label_sqTask2", "Description_sqTask2").build();
        sqTask2.addTask(sqTask1);

        System.out.println("Task ID & Label: " + sqTask2.getId() + " ~ " + sqTask2.getLabel());
        System.out.println("Original estimated hours: " + sqTask2.getOriginalEstimatedTotalHours());
        System.out.println("Percent completed: " + sqTask2.getPercentComplete());
        System.out.println("Days to completion: " + sqTask2.getEstimatedWorkDaysToCompletion());


        /******************************************************************************************
         * 6. The class	library	needs to provide a convenient way to get list of a Tasks to	which a given engineer has been	assigned.
         ******************************************************************************************/
        EngineerUtils.getTasksAssignedToEngineer(engineer1);
        XMLImportingExporting.exportToXML(sqTask1, "D:/exported.xml");

    }
}
