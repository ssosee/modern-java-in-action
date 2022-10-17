package jangseaung.modernjavainaction.pratice.chapter2.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Quiz2_1 {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple("GREEN", 100),
                new Apple("RED", 140),
                new Apple("BLUE", 120)
        );
        AppleFilter appleFilter = new AppleFilter();
        //appleFilter.prettyPrintApple(apples, new AppleFormatterImpl());
        /**
         * AppleFormatterImpl 메서드의 동작을 직접 파라미터화!
         * 익명 클래스를 사용하면
         * 즉석에서 필요한 구현을 만들어서 사용 가능하다.
         */
        appleFilter.prettyPrintApple(apples, new AppleFormatter() {
            @Override
            public String accept(Apple apple) {
                return apple.getColor();
            }
        });
        /**
         * 람다식 사용
         */
        appleFilter.prettyPrintApple(apples, (Apple apple) -> String.valueOf(apple.getWeight()));

        List<Apple> redApples = filter(apples, (Apple a) -> "RED".equals(a.getColor()));
        List<Apple> ApplesUpper100 = filter(apples, (Apple a) -> a.getWeight() > 100);
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    /**
     * 리스트 형식으로 추상화
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
