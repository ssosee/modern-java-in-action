package jangseaung.modernjavainaction.pratice.chapter1.java8;

import jangseaung.modernjavainaction.pratice.chapter1.Apple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

    public static final String GREEN = "green";

    public static void main(String[] args) {
        List<Apple> inventroy = new ArrayList<>();
        //자바8은 메서드를 전달할 수 있다.!!
        filterApples(inventroy, Apple::isHeavyApple);
        filterApples(inventroy, Apple::isGreenApple);

        /**
         * 하지만 isGreenApple, isHeavyApple과 같이 메서드를 정의하는것은 매우 귀찮다.!
         * 람다식을 사용해보자!
         *
         * 하지만 람다가 길어지면 메서드를 정의하고 참조를 위처럼 참조를 활용하는것이 바람직하다.!
         */
        filterApples(inventroy, (Apple a) -> GREEN.equals(a.getColor()));
        filterApples(inventroy, (Apple a) -> a.getWeight() > 150);
        filterApples(inventroy, (Apple a) -> a.getWeight() < 80); //확장 가능!
    }

    /**
     * 프레디케이트?
     * 수학에서 인수로 값을 받아 ture, false를 반환하는 함수를 지칭한다.
     * @param <T>
     */
//    public interface Predicate<T> {
//        boolean test(T t);
//    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : result) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
