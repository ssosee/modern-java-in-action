package jangseaung.modernjavainaction.pratice.chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class FilteringApple {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple("GREEN", 100),
                new Apple("RED", 140),
                new Apple("BLUE", 120)
        );
        inventory.sort(new AppleComparator());

        /**
         * 익명 클래스 사용
         */
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        /**
         * 람다 표현식 사용
         */
        inventory.sort((Apple a1, Apple a2) ->
                a1.getWeight().compareTo(a2.getWeight())
        );
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        /**
         * Comparator 는 Comparable 키를 추출해서 Comparator 객체로 만드는
         * Function 함수를 인수로 받는 정적 메서드 comparing 을 포함한다.
         */
        inventory.sort(comparing(apple -> apple.getWeight()));

        /**
         * 메서드 참조 사용
         */
        inventory.sort(comparing(Apple::getWeight));

        /**
         * 역정렬(내림차순)
         * reversed() 라는 default 메서드 사용
         */
        inventory.sort(comparing(Apple::getWeight).reversed());

        /**
         * 만약 두 사과의 무게가 같다면?
         * 두번째 비교자로 정렬하면된다.!
         */
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor) //두 사과의 무게가 같으면 색으로 정렬
        );
    }
}
