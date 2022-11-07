package jangseaung.modernjavainaction.pratice.chapter9.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObserver(String tweet) {
        this.observers.forEach(o -> o.notify(tweet));
    }
}
