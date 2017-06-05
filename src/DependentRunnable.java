import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by Dorin Luca on 24.05.2017.
 */
public class DependentRunnable implements Runnable {
    private final Semaphore semaphore = new Semaphore(0);
    private final String title;
    private final int releaseNumber;
    private final Set<DependentRunnable> dependencies = new HashSet<>();

    public DependentRunnable(String title, int releaseNumber){
        this.title = title;
        this.releaseNumber = releaseNumber;
    }

    public void setDependency(DependentRunnable dependentRunnable) {
        synchronized (dependencies) {
            dependencies.add(dependentRunnable);
        }
    }

    private void waitToFinish() {
        try{
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        waitDependenciesToFinish();
        try {
            long randomTimeToWait = getRandomTimeToWait();
            Thread.sleep(randomTimeToWait);
            System.out.printf("Execution finished for %s in %d millis\n",title,randomTimeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(releaseNumber);
        }
    }

    private void waitDependenciesToFinish(){
        synchronized (dependencies){
            dependencies.forEach(DependentRunnable::waitToFinish);
        }
    }

    private long getRandomTimeToWait() {
        return (long) (Math.random() * 4000);
    }
}
