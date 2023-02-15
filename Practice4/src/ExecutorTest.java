
public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        MyExecutorService executorService = new MyExecutorService(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ": Task started");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": Task completed");
            });
        }
    }
}
