import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class TestThreads {

    private static void TestSet(Set<Integer> set) {
        Thread thread1 = new Thread(() -> {
            Random random = new Random();
            IntStream.range(0, 10).forEach(i -> {
                set.add(i);
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
                set.forEach(System.out::print);
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

    private static void TestMap(Map<Integer, Integer> map) {
        Thread thread1 = new Thread(() -> {
            Random random = new Random();
            IntStream.range(0, 10).forEach(i -> {
                map.put(i, i);
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
                map.forEach((key, value) -> System.out.print(key + ":" + value + " "));
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

    public static void main(String[] args) throws InterruptedException {
        // Set<Integer> set = new HashSet<>();
        // TestSet(set);
        // Set<Integer> synchronizedSet = new SynchronizedSet<>();
        // TestSet(synchronizedSet);
        
        // Map<Integer, Integer> map = new HashMap<>();
        // TestMap(map);
        Map<Integer, Integer> synchronizedMap = new SynchronizedMap<>();
        TestMap(synchronizedMap);
    }
}
