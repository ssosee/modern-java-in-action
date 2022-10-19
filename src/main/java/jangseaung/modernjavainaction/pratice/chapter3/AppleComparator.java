package jangseaung.modernjavainaction.pratice.chapter3;

import jangseaung.modernjavainaction.pratice.chapter3.Apple;

import java.util.Comparator;

public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple apple1, Apple apple2) {
        return apple1.getWeight().compareTo(apple2.getWeight());
    }
}
