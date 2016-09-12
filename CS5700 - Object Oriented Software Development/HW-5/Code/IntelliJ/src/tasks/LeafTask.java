package tasks;

import engineer.Engineer;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aditya on 12/9/2015.
 */
public class LeafTask extends Task {

    Date savedDate;

    public LeafTask(Builder builder) {
        super(builder);
        super.originalEstimatedTotalHours = builder.originalEstimatedTotalHours;
        super.assignedEngineers.add(builder.assignedEngineer);
        builder.assignedEngineer.assignTask(this);
        savedDate = new Date();
    }

    public static class Builder extends Task.Builder<Builder> {

        private Engineer assignedEngineer;
        private int originalEstimatedTotalHours;

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

        public LeafTask build() {

            return new LeafTask(this);
        }

    }


    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getLabel() {
        return super.getLabel();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public int getOriginalEstimatedTotalHours() {
        return super.getOriginalEstimatedTotalHours();
    }

    @Override
    public ArrayList<Engineer> getAssignedEngineers() {
        return super.getAssignedEngineers();
    }

    @Override
    public int getRevisedEstimatedTotalHours() {
        return super.getRevisedEstimatedTotalHours();
    }

    @Override
    public double getPercentComplete() {
        return ((getOriginalEstimatedTotalHours() - getEstimatedRemainingHours()) / 100);
    }

    public int getEstimatedRemainingHours() {
        /*try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
            System.out.println("Sleeping for 1 second to simulate time.");
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }*/
        int numEngineers = 0;
        for(Engineer e : getAssignedEngineers()) {
            numEngineers++;
        }

        Date currentDate = new Date();
        long miliSecWorkRemaining = TimeUnit.HOURS.toMillis((long)getOriginalEstimatedTotalHours()) - (((currentDate.getTime() - savedDate.getTime()) * numEngineers));

        return (int) TimeUnit.MILLISECONDS.toHours(miliSecWorkRemaining);
    }

    @Override
    public int getEstimatedWorkDaysToCompletion() {
        return getEstimatedRemainingHours() / 24;
    }


}
