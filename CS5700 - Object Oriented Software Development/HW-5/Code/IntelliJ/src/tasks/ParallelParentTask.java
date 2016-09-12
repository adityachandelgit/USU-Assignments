package tasks;

import engineer.Engineer;

import java.util.ArrayList;

/**
 * Created by Aditya on 12/9/2015.
 */
public class ParallelParentTask extends ParentTask {

    public ParallelParentTask(Builder builder) {
        super(builder);
    }


    public static class Builder extends Task.Builder<Builder> {
        private Engineer assignedEngineer;
        private double originalEstimatedTotalHours;
        public Builder(int id, String label, String description) {
            super(id, label, description);
        }
        public Builder assignedEngineers(Engineer assignedEngineer) {
            this.assignedEngineer = assignedEngineer;
            return this;
        }
        public Builder estimatedTotalHours(int estimatedTotalHours) {
            this.originalEstimatedTotalHours = estimatedTotalHours;
            return this;
        }
        public ParallelParentTask build() {
            return new ParallelParentTask(this);
        }
    }

    public void addTask(Task taskToAdd) {
        listTasks.add(taskToAdd);
    }

    @Override
    public int getOriginalEstimatedTotalHours() {
        int totalHours = 0;
        if(listTasks != null) {
            for(Task task : listTasks) {
                if(task.getClass().equals(LeafTask.class)) {
                    totalHours += task.getOriginalEstimatedTotalHours();
                } else if(task.getClass().equals(ParallelParentTask.class)) {
                    totalHours += task.getOriginalEstimatedTotalHours();
                }
            }
        }
        return totalHours;
    }

    @Override
    public double getPercentComplete() {
        double totalPercentComplete = 0;
        int totalTasks = 0;
        if(listTasks != null) {
            for(Task task : listTasks) {
                totalTasks++;
                totalPercentComplete += task.getPercentComplete();
            }
        }
        return totalPercentComplete / totalTasks;
    }

    @Override
    public int getEstimatedRemainingHours() {
        int totalEstimatedHours = 0;
        if(listTasks != null) {
            for(Task task : listTasks) {
                totalEstimatedHours += task.getEstimatedRemainingHours();
            }
        }
        return totalEstimatedHours;
    }

    @Override
    public int getEstimatedWorkDaysToCompletion() {
        int totalEstimatedDays = 0;
        if(listTasks != null) {
            for(Task task : listTasks) {
                totalEstimatedDays += task.getEstimatedWorkDaysToCompletion();
            }
        }
        return totalEstimatedDays;
    }

    @Override
    public ArrayList<Engineer> getAssignedEngineers() {
        ArrayList<Engineer> engineers = new ArrayList<>();
        if(listTasks != null) {
            for(Task task : listTasks) {
                engineers.addAll(task.getAssignedEngineers());
            }
        }
        return engineers;
    }
}
