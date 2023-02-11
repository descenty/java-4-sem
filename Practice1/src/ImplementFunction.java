import java.util.function.BiFunction;
import java.util.function.Function;

public class ImplementFunction {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> gcdFunction = (a, b) -> {
            while (a != 0) {
                int temp = a;
                a = b % a;
                b = temp;
            }
            return Math.abs(b);
        };
        Function<int[], Integer> nGCDFunction = (values) -> {
            int result = values[0];
            for (int i = 1; i < values.length; i++)
                result = gcdFunction.apply(result, values[i]);
            return result;
        };
        int[] values = { 16, -24, 40 };
        System.out.println(nGCDFunction.apply(values));
    }
}
