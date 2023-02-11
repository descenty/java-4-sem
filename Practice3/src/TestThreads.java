import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class TestThreads {
    public static void main(String[] args) throws InterruptedException {
        Set<Integer> set = new LinkedHashSet<>();

        SynchronizedSet<Integer> synchronizedSet = new SynchronizedSet<>(set);

        Thread thread1 = new Thread(() -> {
            Random random = new Random();
            IntStream.range(0, 10).forEach(i -> {
                // set.add(i);
                synchronizedSet.add(i);
                try {
                    Thread.sleep(random.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        Thread thread2 = new Thread(() -> {
            Random random = new Random();
            IntStream.range(0, 10).forEach(i -> {
                // set.forEach(System.out::print);
                synchronizedSet.forEach(System.out::print);
                try {
                    Thread.sleep(random.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        thread1.start();
        thread2.start();
    }
}
