package tasks;

import engineer.Engineer;

import java.util.ArrayList;

/**
 * Created by Aditya on 12/9/2015.
 */
public class Task {

    int id;
    String label;
    String description;
    int originalEstimatedTotalHours;
    int revisedEstimatedTotalHours;
    double percentComplete;
    int estimatedRemainingHours;
    int estimatedWorkDaysToCompletion;

    ArrayList<Engineer> assignedEngineers = new ArrayList<>();

    protected Task(Builder builder) {
        this.id = builder.id;
        this.label = builder.label;
        this.description = builder.description;
        this.assignedEngineers.add(builder.assignedEngineer);
        this.originalEstimatedTotalHours = builder.originalEstimatedTotalHours;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public int getOriginalEstimatedTotalHours() {
        return originalEstimatedTotalHours;
    }

    public int getRevisedEstimatedTotalHours() {
        return revisedEstimatedTotalHours;
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    public int getEstimatedRemainingHours() {
        return estimatedRemainingHours;
    }

    public int getEstimatedWorkDaysToCompletion() {
        return estimatedWorkDaysToCompletion;
    }

    public ArrayList<Engineer> getAssignedEngineers() {
        return assignedEngineers;
    }

    private void addEngineer(Engineer engineer) {
        assignedEngineers.add(engineer);
    }


    public static class Builder<T extends Builder> {
        private final int id;
        private final String label;
        private final String description;
        private Engineer assignedEngineer;
        private int originalEstimatedTotalHours;

        public Builder(int id, String label, String description) {
            this.id = id;
            this.label = label;
            this.description = description;
        }

        public T assignedEngineers(Engineer assignedEngineer) {
            this.assignedEngineer = assignedEngineer;
            return (T) this;
        }

        public T estimatedTotalHours(int estimatedTotalHours) {
            this.originalEstimatedTotalHours = estimatedTotalHours;
            return (T) this;
        }

        public Task build() {
            return new Task(this);
        }

    }

    private void removeEngineer(int engineerId) {
        for(Engineer engineer : assignedEngineers) {
            if(engineer.getId() == engineerId) {
                assignedEngineers.remove(engineer);
                break;
            }
        }
    }
}
