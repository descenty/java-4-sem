import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class TestThreads {

    private static void TestSet(Set<Integer> set1, Set<Integer> set2) {
        Thread thread1 = new Thread(() -> {
            Random random = new Random();
            IntStream.range(0, 10).forEach(i -> {
                set1.add(i);
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
                set2.add(i);
                try {
                    Thread.sleep(random.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        Thread thread3 = new Thread(() -> {
            Random random = new Random();
            IntStream.range(0, 10).forEach(i -> {
                set1.forEach(System.out::print);
                System.out.println();
                set2.forEach(System.out::print);
                try {
                    Thread.sleep(random.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        thread1.start();
        thread2.start();
        thread3.start();
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
        Set<Integer> synchronizedSet1 = new SynchronizedSet<>();
        Set<Integer> synchronizedSet2 = new SynchronizedSet<>();
        TestSet(synchronizedSet1, synchronizedSet2);

        // Map<Integer, Integer> map = new HashMap<>();
        // TestMap(map);
        // Map<Integer, Integer> synchronizedMap = new SynchronizedMap<>();
        // TestMap(synchronizedMap);
    }
}
