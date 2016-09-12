package engineer;

import tasks.Task;

import java.util.ArrayList;

/******************************************************************************************************
 * Created by Aditya on 12/9/2015.
 *
 * 2. The class	library	needs to include an	Engineer class whose instance can capture basic	information
 *    about	the	available engineers.
 *        2.1. An	Engineer must have the following properties
 *        2.1.1. Id
 *        2.1.2. Name
 *        2.1.3. Available hours per day
 *******************************************************************************************************/

public class Engineer {

    int id;
    String name;
    int availableHoursPerDay;

    ArrayList<Task> assignedTasks = new ArrayList<>();

    public Engineer(int id, String name, int availableHoursPerDay) {
        this.id = id;
        this.name = name;
        this.availableHoursPerDay = availableHoursPerDay;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailableHoursPerDay() {
        return availableHoursPerDay;
    }

    public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void assignTask(Task task) {
        if(task != null) {
            assignedTasks.add(task);
        }
    }
}
