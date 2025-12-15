import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memoizer {

    public static void main(String[] args) {

        // Fibonacci non memoized
        final Function<Integer, Long>[] fib = new Function[1];
        fib[0] = n -> {
            if (n <= 1) return (long) n;
            System.out.println("Calcul de fibonacci(" + n + ")");
            return fib[0].apply(n - 1) + fib[0].apply(n - 2);
        };

        // Fibonacci memoized
        final Function<Integer, Long>[] fibMemo = new Function[1];
        fibMemo[0] = memoize(n -> {
            if (n <= 1) return (long) n;
            System.out.println("Calcul de fibonacci(" + n + ")");
            return fibMemo[0].apply(n - 1) + fibMemo[0].apply(n - 2);
        });

        // Test non memoized
        System.out.println("Version non-memoized:");
        long start = System.currentTimeMillis();
        System.out.println("fibonacci(10) = " + fib[0].apply(10));
        System.out.println("Temps: " + (System.currentTimeMillis() - start) + " ms");

        // Test memoized
        System.out.println("\nVersion memoized:");
        start = System.currentTimeMillis();
        System.out.println("fibonacci(10) = " + fibMemo[0].apply(10));
        System.out.println("Temps: " + (System.currentTimeMillis() - start) + " ms");

        // Deuxième appel (cache)
        System.out.println("\nDeuxième appel memoized:");
        start = System.currentTimeMillis();
        System.out.println("fibonacci(10) = " + fibMemo[0].apply(10));
        System.out.println("Temps: " + (System.currentTimeMillis() - start) + " ms");
    }

    // Memoizer générique thread-safe
    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        Map<T, R> cache = new ConcurrentHashMap<>();
        return input -> cache.computeIfAbsent(input, function);
    }
}
