package jangseaung.modernjavainaction.pratice.chapter1;

import lombok.Getter;

import static jangseaung.modernjavainaction.pratice.chapter1.java8.FilteringApples.GREEN;

@Getter
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
