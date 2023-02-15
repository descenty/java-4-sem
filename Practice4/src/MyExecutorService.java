import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyExecutorService implements ExecutorService {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Thread> workerThreads;
    private boolean isShutdown = false;

    public MyExecutorService(int nThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workerThreads = new ArrayList<>();

        for (int i = 0; i < nThreads; i++) {
            Thread workerThread = new Thread();
            workerThreads.add(workerThread);
            workerThread.start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        try {
            taskQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        isShutdown = true;
        for (Thread workerThread : workerThreads) {
            workerThread.interrupt();
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        isShutdown = true;
        for (Thread workerThread : workerThreads) {
            workerThread.interrupt();
        }
        return new ArrayList<>(taskQueue);
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    @Override
    public boolean isTerminated() {
        return isShutdown && taskQueue.isEmpty();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long timeoutInMs = unit.toMillis(timeout);
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeoutInMs) {
            if (isTerminated()) {
                return true;
            }
            Thread.sleep(100);
        }
        return false;
    }

    @Override
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

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        FutureTask<T> futureTask = new FutureTask<>(task, result);
        try {
            taskQueue.put(futureTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return futureTask;
    }

    @Override
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
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> task : tasks) {
            FutureTask<T> futureTask = new FutureTask<>(task);
            submit(futureTask);
            futures.add(futureTask);
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                // Exception occurred, ignore it and move on
            }
        }
        return futures;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> task : tasks) {
            FutureTask<T> futureTask = new FutureTask<>(task);
            submit(futureTask);
            futures.add(futureTask);
        }
        long timeoutInMs = unit.toMillis(timeout);
        long startTime = System.currentTimeMillis();
        for (Future<?> future : futures) {
            try {
                future.get(timeoutInMs - (System.currentTimeMillis() - startTime), TimeUnit.MILLISECONDS);
            } catch (ExecutionException e) {
                // Exception occurred, ignore it and move on
            } catch (TimeoutException e) {
                // Timeout occurred, ignore it and move on
            }
        }
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> task : tasks) {
            FutureTask<T> futureTask = new FutureTask<>(task);
            submit(futureTask);
            futures.add(futureTask);
        }
        for (Future<?> future : futures) {
            try {
                return future.get();
            } catch (ExecutionException e) {
                // Exception occurred, ignore it and move on
            }
        }
        throw new ExecutionException("No task completed successfully", null);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> task : tasks) {
            FutureTask<T> futureTask = new FutureTask<>(task);
            submit(futureTask);
            futures.add(futureTask);
        }
        long timeoutInMs = unit.toMillis(timeout);
        long startTime = System.currentTimeMillis();
        for (Future<?> future : futures) {
            try {
                return future.get(timeoutInMs - (System.currentTimeMillis() - startTime), TimeUnit.MILLISECONDS);
            } catch (ExecutionException e) {
                // Exception occurred, ignore it and move on
            } catch (TimeoutException e) {
                // Timeout occurred, ignore it and move on
            }
        }
        throw new ExecutionException("No task completed successfully", null);
    }
}
