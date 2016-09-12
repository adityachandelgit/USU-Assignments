package utilities;

import engineer.Engineer;
import tasks.Task;

import java.util.ArrayList;

/**
 * Created by Aditya on 12/11/2015.
 */
public class EngineerUtils {

    public static ArrayList<Task> getTasksAssignedToEngineer(Engineer e) {
        return e.getAssignedTasks();
    }
}
