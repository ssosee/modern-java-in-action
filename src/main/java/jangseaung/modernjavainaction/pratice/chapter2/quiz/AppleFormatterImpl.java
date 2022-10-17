package jangseaung.modernjavainaction.pratice.chapter2.quiz;

public class AppleFormatterImpl implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "색="+apple.getColor()+", 무게="+apple.getWeight();
    }
}
