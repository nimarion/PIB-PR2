package de.nmarion.htw.ueb17;

import java.util.function.Predicate;

@FunctionalInterface
public interface ConditionateMyFunction extends MyFunction {

    default MyFunction conditionateInput(Predicate<Integer> predicate) {
        return x -> (predicate.test(x) ? this.apply(x) : 0);
    }

    default MyFunction conditionateOutput(Predicate<Integer> predicate) {
        return x -> (predicate.test(this.apply(x)) ? this.apply(x) : 0);
    }

}
