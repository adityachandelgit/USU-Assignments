package tasks;

import java.util.ArrayList;

/**
 * Created by Aditya on 12/9/2015.
 */
public class ParentTask extends Task {

    ArrayList<Task> listTasks = new ArrayList<>();

    public ParentTask(Builder builder) {
        super(builder);
    }

    public ArrayList<Task> getListTasks() {
        return listTasks;
    }
}
