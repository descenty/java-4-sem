import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MyExecutorService implements ExecutorService {
    class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!isShutdown) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private final BlockingQueue<Runnable> taskQueue;
    private final List<Worker> workerThreads;
    private boolean isShutdown = false;

    public MyExecutorService(int nThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workerThreads = new ArrayList<>();

        for (int i = 0; i < nThreads; i++) {
            Worker workerThread = new Worker("Worker-" + (i + 1));
            workerThreads.add(workerThread);
            workerThread.start();
        }
    }

    public void shutdown() {
        isShutdown = true;
        for (Thread workerThread : workerThreads) {
            workerThread.interrupt();
        }
    }

    public boolean isShutdown() {
        return isShutdown;
    }

    public boolean isTerminated() {
        return isShutdown && taskQueue.isEmpty();
    }

    public <T> Future<T> submit(Callable<T> task) {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        FutureTask<T> futureTask = new FutureTask<>(task);
        try {
            taskQueue.put(futureTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return futureTask;
    }

    public Future<?> submit(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        FutureTask<?> futureTask = new FutureTask<>(task, null);
        try {
            taskQueue.put(futureTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return futureTask;
    }

    @Override
    public void execute(Runnable command) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Runnable> shutdownNow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        // TODO Auto-generated method stub
        return null;
    }
}