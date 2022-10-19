package jangseaung.modernjavainaction.chapter3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionTest {
    public interface Function<T, R> {
        R apply(T t);
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T t : list) {
            result.add(f.apply(t));
        }

        return result;
    }

    @Test
    void functionTest() {
        List<Integer> I = map(Arrays.asList("lambdas", "in", "action"), (String s) -> {
            System.out.println(s.length());
            return s.length();
        });
    }
}
