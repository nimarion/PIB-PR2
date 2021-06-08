package de.nmarion.htw.ueb17;

import java.util.function.Predicate;

public class Main {

    private static final MyFunction factorial = x -> (x < 1) ? 1 : x * Main.factorial.apply(--x);

    private static final MyFunction fibonacci = x -> (x < 1) ? 0
            : Main.fibonacci.apply(--x) + Main.fibonacci.apply(x - 2);

    public static void main(String... args) {
        final int minRange = 1;
        final int maxRange = 10;

        // i
        applyAndPrint(minRange, maxRange, x -> x * x);
        applyAndPrint(minRange, maxRange, new MyFunction() {
            @Override
            public int apply(int i) {
                return i * i;
            }
        });

        // ii
        applyAndPrint(minRange, maxRange, factorial);
        applyAndPrint(minRange, maxRange, new MyFunction() {
            @Override
            public int apply(int i) {
                return (i < 1) ? 1 : i * apply(i - 1);
            }
        });

        // iii
        applyAndPrint(minRange, maxRange, x -> (int) (Math.pow(x, x + 1)));
        applyAndPrint(minRange, maxRange, new MyFunction() {
            @Override
            public int apply(int i) {
                return (int) Math.pow(i, i + 1);
            }
        });

        // iiii
        applyAndPrint(minRange, maxRange, fibonacci);
        applyAndPrint(minRange, maxRange, new MyFunction() {
            @Override
            public int apply(int i) {
                return (i < 1) ? 0 : apply(--i) + apply(i - 2);
            }
        });

        // e
        applyAndPrint(minRange, maxRange, x -> {
            ConditionateMyFunction function = i -> i * i;
            return function.conditionateInput(even).apply(x);
        });
        
        applyAndPrint(minRange, maxRange, x -> {
            ConditionateMyFunction function = i -> factorial.apply(i);
            return function.conditionateOutput(odd()).apply(x);
        });
    }


    //d
    private static Predicate<Integer> odd() {
        return new Predicate<Integer>() {
            @Override
            public boolean test(Integer t) {
                return t % 2 == 1;
            }
        };
    }

    //d
    private static Predicate<Integer> even = x -> x % 2 == 0;


    //a
    private static void applyAndPrint(int i, int j, MyFunction myFunction) {
        if (i < 0 || j < 0) {
            throw new IllegalArgumentException();
        }
        for (int index = i + 1; index < j; index++) {
            System.out.println(myFunction.apply(index));
        }
    }

}
