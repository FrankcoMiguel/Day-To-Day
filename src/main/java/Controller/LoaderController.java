package Controller;

import javafx.concurrent.Task;


public abstract class LoaderController extends Task<Integer> {

    @Override
    protected Integer call() throws Exception {

        for (int i = 10; i <= 100; i++){

            updateProgress(i, 100);
            Thread.sleep(200);

        }

        return 100;
    }

    @Override
    protected void updateProgress(double workDone, double max) {
        long loading = Math.round(workDone);

        if (loading >= 13 && loading <= 24) {

            updateMessage("Loading: "  +(loading) + "%");

        } else if (loading == 45) {

            try {

                loadTasks();
                updateMessage("Getting your tasks");

            } catch (Exception e){

                System.out.println("Error getting tasks");

            }

        } else if ((loading >= 39 && loading < 45) || (loading > 45 && loading <= 70)) {

            updateMessage("Getting your tasks");

        }
        else if (loading >= 99){

            updateMessage("Welcome back!");

        } else {

            updateMessage("Loading: " + (loading) + "%");

        }

        super.updateProgress(workDone, max);
    }

    protected abstract void loadTasks();

}
