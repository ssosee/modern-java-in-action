package jangseaung.modernjavainaction.pratice.chapter2.quiz;

import java.util.List;

public class AppleFilter {
    public void prettyPrintApple(List<Apple> apples, AppleFormatter appleFormatter) {
        for(Apple apple : apples) {
            System.out.println(appleFormatter.accept(apple));
        }
    }
}
