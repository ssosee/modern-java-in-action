package jangseaung.modernjavainaction.pratice.chapter2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static jangseaung.modernjavainaction.pratice.chapter1.java8.FilteringApples.GREEN;

@Getter
@AllArgsConstructor
public class Apple {
    private String color;
    private int weight;

    public static boolean isGreenApple(Apple apple) {
        return GREEN.equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}
